package com.goat.controller;

import com.goat.dto.ChooseOptionRequest;
import com.goat.dto.ShopItem;
import com.goat.dto.TransferOffer;
import com.goat.model.Career;
import com.goat.model.CareerSeason;
import com.goat.model.Event;
import com.goat.model.EventOption;
import com.goat.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final CareerService careerService; // para crear carreras
    private final EventService eventService; // para las opciones de eventos
    private final TransferService transferService; // para ofertas de equipos
    private final SimulationEngine simulationEngine; // Simula que pasa en la temporada
    private final GameService gameService; // para siguiiente turno
    private final ShopService shopService;


    public GameController(CareerService careerService,EventService eventService,TransferService transferService,SimulationEngine simulationEngine,GameService gameService,ShopService shopService){
        this.careerService = careerService;
        this.eventService = eventService;
        this.transferService = transferService;
        this.simulationEngine = simulationEngine;
        this.gameService = gameService;
        this.shopService = shopService;
    }

    // GET - Siguiente turno
@GetMapping("/next-turn/{id}")
    public List<Event> nextTurn(@PathVariable Long id){
        return gameService.nextTurn(id);
}

// POST - Enviar opcion elegida (EventService)
@PostMapping("/choose")
    public String chooseOption(@RequestBody ChooseOptionRequest request){
        return eventService.applyEventConsequences(
                request.getCareerId(),
                request.getEventId(),
                request.getOptionId()
        );
}

// POST - Simula que pasa en la temporada
@PostMapping("/simulate/{id}")
    public CareerSeason simulateSeason(@PathVariable Long id){
        return simulationEngine.simulateSeason(id);

}

// GET - Recibir ofertas
@GetMapping("/offers/{id}")
    public List<TransferOffer> getOffers(@PathVariable Long id){
        return transferService.generateOffer(id);
}


// POST - Enviar oferta aceptada
@PostMapping("accept-offer/{id}")
public Career acceptOffert(@PathVariable Long id, @RequestBody TransferOffer offer){
        return transferService.acceptOffer(id,offer);
}

// GET - Obtener la lista de item para la tienda
@GetMapping("/shop/{id}")
public List<ShopItem> getItems(@PathVariable Long id){
        return shopService.getShopItems(id);
}

@PostMapping("/shop/{careerId}/buy/{itemId}")
    public Career buyItem(@PathVariable Long careerId,@PathVariable String itemId){
        return shopService.buyItem(careerId,itemId);
}


}
