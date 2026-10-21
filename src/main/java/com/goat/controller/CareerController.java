package com.goat.controller;


import com.goat.dto.CreateCareerRequest;
import com.goat.model.Career;
import com.goat.repository.CareerRepository;
import com.goat.service.CareerService;
import org.springframework.web.bind.annotation.*;

@RestController  // indica que esta clase maneja peticiones HTTP - endpoint
@RequestMapping("/api/careers") //  las rutas empiezan por "/api/careers"
public class CareerController {

    private final CareerService careerService;
    private final CareerRepository careerRepository;

    // Inyectamos el service para poder usarlo despues
    public CareerController(CareerService careerService, CareerRepository careerRepository){
        this.careerService = careerService;
        this.careerRepository = careerRepository;
    }

    @PostMapping // Cuando haya un POST a /api/career se ejecuta este metodo, como no se define ruta se usa la generiuca de @RequestMapping
    public Career createCareer(@RequestBody CreateCareerRequest request){
        return careerService.createCareer(
                request.getPlayerName(),
                request.getPositionId(),
                request.getNationality()
        );
    }

    // LLevar info de la carrera al front
    @GetMapping("/{id}")
    public Career getCareer(@PathVariable Long id) {
        return careerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }


    @GetMapping("/health")
    public String upBackend(){
        return "OK";
    }

}
