package com.goat.service;

import com.goat.dto.PreseasonOption;
import com.goat.dto.PreseasonPhase;
import com.goat.model.Career;
import com.goat.repository.CareerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreseasonService {

    private final CareerRepository careerRepository;

    public PreseasonService(CareerRepository careerRepository){
        this.careerRepository = careerRepository;
    }



    public List<PreseasonPhase> getPhases(Long careerId){

        Career mycareer = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));


        // OPCIONES FASE VACACIONES

        PreseasonOption rest = new PreseasonOption(); // Descansar
        rest.setId("descansar");
        rest.setText("Descansar a tope: playa, familia y desconectar");
        rest.setEffect("+2 mental, -1 físico");

        PreseasonOption train = new PreseasonOption(); // Entrenar
        train.setId("entrenar");
        train.setText("Entrenar por tu cuenta todo el verano");
        train.setEffect("+2 físico, -1 mental");

        PreseasonOption balanced = new PreseasonOption();
        balanced.setId("equiliobrado");
        balanced.setText("Combinar vacaciones con trabajo en el gimnasio");
        balanced.setEffect("+1 mental, +1 físico");

        // OPCIONES FASE PREPARACION TEMPORADA

        PreseasonOption technique = new PreseasonOption();
        technique.setId("tecnica");
        technique.setText("Trabajar la técnica y el control de balón");
        technique.setEffect("+3 técnica");

        PreseasonOption physical = new PreseasonOption();
        physical.setId("fisico");
        physical.setText("Ponerte fuerte físicamente");
        physical.setEffect("+2 físico, +1 velocidad");

        PreseasonOption tactic = new PreseasonOption();
        tactic.setId("tactica");
        tactic.setText("Estudiar vídeo y mejorar tu lectura del juego");
        tactic.setEffect("+2 visión, +1 mental");

        // OPCIONES FASE AMISTOSOS

        PreseasonOption full = new PreseasonOption();
        full.setId("atope");
        full.setText("Salir a darlo todo y demostrar que eres titular");
        full.setEffect("+2 definición, +1 reputación, riesgo lesión leve");

        PreseasonOption calm = new PreseasonOption();
        calm.setId("tranquilo");
        calm.setText("Jugar tranquilo sin arriesgar");
        calm.setEffect("+1 técnica, +1 confianza míster");

        PreseasonOption leader = new PreseasonOption();
        leader.setId("lider");
        leader.setText("Hablar al equipo y liderar desde el primer minuto");
        leader.setEffect("+2 relación vestuario, +1 relación míster");


        // FASE VACACIONES
        PreseasonPhase holidays = new PreseasonPhase();
        holidays.setId("vacaciones");
        holidays.setTitle("Vacaciones");
        holidays.setDescription("La temporada ha terminado. El míster te dice: 'Quiero que llegues fresco en agosto. Cuídate este verano");
        holidays.setOptions(List.of(rest, train, balanced));

        // FASE PREPARACION
        PreseasonPhase preparation = new PreseasonPhase();
        preparation.setId("preparacion");
        preparation.setTitle("Preparación");
        preparation.setDescription("Primer día de pretemporada. El míster te llama al despacho: 'Este año quiero que trabajes un aspecto concreto de tu juego.'");
        preparation.setOptions(List.of(technique, physical, tactic));

        // FASE AMISTOSO
        PreseasonPhase friendlyMatch = new PreseasonPhase();
        friendlyMatch.setId("amistoso");
        friendlyMatch.setTitle("Partido amistoso");
        friendlyMatch.setDescription("Último amistoso de pretemporada. El míster te pone de titular: 'Quiero ver qué tienes. Demuéstrame que puedo contar contigo.'");
        friendlyMatch.setOptions(List.of(full, calm, leader));


return List.of(holidays,preparation,friendlyMatch);

    }


    public Career chooseOption(Long careerId, String phaseId, String optionId){

        Career mycareer = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Validar orden de fases
        if (phaseId.equals("vacaciones") && mycareer.getPreseasonStep() != 0) {
            throw new RuntimeException("Ya hiciste las vacaciones");
        }
        if (phaseId.equals("preparacion") && mycareer.getPreseasonStep() != 1) {
            throw new RuntimeException("Primero haz las vacaciones");
        }
        if (phaseId.equals("amistoso") && mycareer.getPreseasonStep() != 2) {
            throw new RuntimeException("Primero haz la preparación");
        }


        switch (optionId){
            case "descansar": mycareer.setMental(mycareer.getMental() + 2);
                mycareer.setPhysical(mycareer.getPhysical() - 1);
                break;
            case "entrenar": mycareer.setPhysical(mycareer.getPhysical() + 2);
                mycareer.setMental(mycareer.getMental() - 1);
                break;
            case "equilibrado": mycareer.setMental(mycareer.getMental() + 1);
                mycareer.setPhysical(mycareer.getPhysical() + 1);
                break;
            case "tecnica": mycareer.setTechnique(mycareer.getTechnique() + 3);
                break;
            case "fisico": mycareer.setPhysical(mycareer.getPhysical() + 2);
                mycareer.setSpeed(mycareer.getSpeed() + 1);
                break;
            case "tactica": mycareer.setVision(mycareer.getVision() + 2);
            mycareer.setMental(mycareer.getMental() + 1);
                break;
            case "atope": mycareer.setFinishing(mycareer.getFinishing() + 2);
                mycareer.setReputation(mycareer.getReputation() + 1);
                break;
            case "tranquilo": mycareer.setTechnique(mycareer.getTechnique() + 1);
                mycareer.setCoachRelation(mycareer.getCoachRelation() + 1);
                break;
            case "lider":  mycareer.setTeamRelation(mycareer.getTeamRelation() + 2);
                mycareer.setCoachRelation(mycareer.getCoachRelation() + 1);
                break;
            default: throw new RuntimeException("Opción no identificada");
        }

        mycareer.setPreseasonStep(mycareer.getPreseasonStep() + 1);

        careerRepository.save(mycareer);
        return mycareer;

    }


}
