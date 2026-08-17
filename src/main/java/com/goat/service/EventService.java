package com.goat.service;

import com.goat.model.Career;
import com.goat.model.CareerEventLog;
import com.goat.model.Event;
import com.goat.model.EventOption;
import com.goat.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EventService {

    private final EventRepository eventRepository; // trabajar con los eventos
    private final CareerRepository careerRepository; // carrera actual para ver edad,nivel del pj
    private final CareerEventLogRepository careerEventLogRepository; // comprobar si salio ya ese evento
    private final TeamRepository teamRepository; // equipos
    private final EventOptionRepository eventOptionRepository;

    public EventService(EventRepository eventRepository, CareerRepository careerRepository, CareerEventLogRepository careerEventLogRepository, TeamRepository teamRepository, EventOptionRepository eventOptionRepository) {
        this.eventRepository = eventRepository;
        this.careerEventLogRepository = careerEventLogRepository;
        this.careerRepository = careerRepository;
        this.teamRepository = teamRepository;
        this.eventOptionRepository = eventOptionRepository;
    }


    public List<Event> getRandomEvents(Long careerId, int count) {

        // 1. Buscamos la carrera

        Career career = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // 2. Sacamos la edad del PJ, la fase y el nivel de liga

        int age = career.getCurrentAge();

        // Si no tiene equipo -> academia, si tiene equipo -> profesional
        String phase;
        int level;

        if (career.getCurrentTeam() == null) {
            phase = "academia";
            level = 1;
        } else {
            phase = "profesional";
            level = career.getCurrentTeam().getLeague().getLevel();
        }

        // 3. Buscamos todos los eventos elegibles para esta phase y level

        List<Event> availableEvents = eventRepository.findEligibleEvents(age, level, phase);

        // Comprobamos los que ya saliero con findByCareerIdAndEventId(CareerEventLogRepositoyry)

        // 4. Creamos lista para ir metiendo los que pasen el filtro
        List<Event> eventFiltered = new ArrayList<>(); // eventos que cumplen requisitos


        for (Event event : availableEvents) {
            if (careerEventLogRepository.findByCareerIdAndEventId(careerId, event.getId()).isEmpty()) {
                eventFiltered.add(event);
            }
        }

        // De los filtrados elegimos aleatorios y los guardamos en la lista nueva
        List<Event> selected = new ArrayList<>(); // lista con los 2-3 eventos que el jugador verá en ese turno
        Random randomNum = new Random();

        // itera count veces
        for (int i = 0; i < count; i++) {
            if (eventFiltered.isEmpty()) {
                break;  // Si esta vacia la lista sal del bucle
            }
            int index = randomNum.nextInt(eventFiltered.size());
            selected.add(eventFiltered.get(index));
            eventFiltered.remove(index);  // quitar ese evento de ese indice para no repetir
        }

        return selected; // devolvemos los eventos que vera
    }

    public String applyEventConsequences(Long careerId, Long eventId, Long optionId) {


        // 1. Buscamos la carrera, si no hay: error
        Career career = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // 2.Buscamos la opcion elegida, si no hay: error

        EventOption option = eventOptionRepository.findById(optionId).orElseThrow(() -> new RuntimeException("Opción no encontrada"));


        // 3. Nuevo valor = valor anterior + modificador
        career.setSpeed(career.getSpeed() + option.getSpeedMod());
        career.setFinishing(career.getFinishing() + option.getFinishingMod());
        career.setTechnique(career.getTechnique() + option.getTechniqueMod());
        career.setPhysical(career.getPhysical() + option.getPhysicalMod());
        career.setMental(career.getMental() + option.getMentalMod());
        career.setVision(career.getVision() + option.getVisionMod());
        career.setSavings(career.getSavings() + option.getSavingsMod());
        career.setReputation(career.getReputation() + option.getReputationMod());
        career.setCoachRelation(career.getCoachRelation() + option.getCoachRelationMod());
        career.setAgentRelation(career.getAgentRelation() + option.getAgentRelationMod());
        career.setBoardRelation(career.getBoardRelation() + option.getBoardRelationMod());
        career.setPressRelation(career.getPressRelation() + option.getPressRelationMod());
        career.setTeamRelation(career.getTeamRelation() + option.getTeamRelationMod());
        career.setFansRelation(career.getFansRelation() + option.getFansRelationMod());


        // 4. Guardamos la carrera actualizada
        careerRepository.save(career);

        // 5. Registramos los eventos que ya salieron en el Historial de eventos(evitamos duplicados)

        CareerEventLog log = new CareerEventLog(); // creamos registro de log
        log.setCareer(career); // Indicamos a la carrera que pertenece el registro creado

        // Identificamos y registramos el evento que le sale por su id
        log.setEvent(eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Evento no encontrado")));
        log.setOptionChosen(optionId.intValue()); // Registramos la opcion que eligió de ese evento
        log.setSeasonNumber(career.getCurrentAge() - 16); // En que temporada ocurrió,para saber cuantas temporadas llevamos como empieza a los 16 le restamos la edad actual -16
        log.setCreatedAt(LocalDateTime.now()); // Registramo fecha/hora actual


        careerEventLogRepository.save(log);


        return option.getOutcomeText();


    }




}
