package com.goat.service;

import com.goat.model.Career;
import com.goat.model.Event;
import com.goat.repository.CareerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


// CONECTA TO DO , CUANDO SE CLICA BOTON(FRONTEND LLAMA) EL GAMESERVICE DECIDE QUE HACER
@Service
public class GameService {

    private final CareerRepository careerRepository; // Para leer la carrera, accede a datos. Solo lee y escribe en la BBDD
    private final CareerService careerService; // lógica de carrera, crea carreras con stats, genera competidores, rol....
    private final EventService eventService; // Para eventos
    private final SimulationEngine simulationEngine; // Para simular la temporada
    private final TransferService transferService; // Para ofertas/etapa fichajes

    public GameService(CareerRepository careerRepository,CareerService careerService,EventService eventService,SimulationEngine simulationEngine,TransferService transferService){
        this.careerRepository = careerRepository;
        this.careerService = careerService;
        this.eventService = eventService;
        this.simulationEngine = simulationEngine;
        this.transferService = transferService;
    }

    public List<Event> nextTurn(Long careerId){

        Career career = careerRepository.findById(careerId).orElseThrow(()-> new RuntimeException("Carrera no encontrada"));

        // retorna 3 eventos
        return eventService.getRandomEvents(careerId,5);

        // FUTURO
//        if (career.getCurrentTeam() == null) {
//            return eventService.getRandomEvents(careerId, 3);  // academia
//        } else if (preSeason) {
//            return eventService.getRandomEvents(careerId, 2);  // pretemporada
//        } else if (offSeason) {
//            simulationEngine.simulateSeason(careerId);          // simular
//            return transferService.generateOffers(careerId);    // ofertas
//        } else {
//            return eventService.getRandomEvents(careerId, 3);  // profesional
//        }
    }

}
