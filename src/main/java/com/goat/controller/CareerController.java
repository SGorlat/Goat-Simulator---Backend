package com.goat.controller;


import com.goat.dto.CreateCareerRequest;
import com.goat.model.Career;
import com.goat.service.CareerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // indica que esta clase maneja peticiones HTTP - endpoint
@RequestMapping("/api/careers") //  las rutas empiezan por "/api/careers"
public class CareerController {

    private final CareerService careerService;

    // Inyectamos el service para poder usarlo despues
    public CareerController(CareerService careerService){
        this.careerService = careerService;
    }

    @PostMapping // Cuando haya un POST a /api/career se ejecuta este metodo, como no se define ruta se usa la generiuca de @RequestMapping
    public Career createCareer(@RequestBody CreateCareerRequest request){
        return careerService.createCareer(
                request.getPlayerName(),
                request.getPositionId(),
                request.getNationality()
        );
    }





}
