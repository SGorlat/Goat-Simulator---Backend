package com.goat.service;

import com.goat.dto.ShopItem;
import com.goat.model.Career;
import com.goat.repository.CareerRepository;
import com.goat.repository.CareerSeasonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    private final CareerRepository careerRepository;



    public ShopService(CareerSeasonRepository careerSeasonRepository, CareerRepository careerRepository){
        this.careerRepository = careerRepository;

    }


    public List<ShopItem> getShopItems(Long careerId){

        Career career = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        ShopItem fisio = new ShopItem();

        fisio.setId("fisio");
        fisio.setName("Fisioterapeuta Privado");
        fisio.setDescription("Mejora el físico (+2) y reduice el riesgo de lesión");
        fisio.setPrice(400000);
        fisio.setActive(career.getHasPhysio());

        ShopItem nutritionist = new ShopItem();

        nutritionist.setId("nutricionista");
        nutritionist.setName("Nutricionista Personal");
        nutritionist.setDescription("Mejora el físico (+1) y la agilidad del jugador (velocidad +1)");
        nutritionist.setPrice(470000);
        nutritionist.setActive(career.getHasNutritionist());

        ShopItem psychologist = new ShopItem();

        psychologist.setId("psicologo");
        psychologist.setName("Psicólogo Personal");
        psychologist.setDescription("Mejora la mentalidad del jugador (+2) y la clarividencia en el juego (visión +2) reduciendo las malas rachas");
        psychologist.setPrice(490000);
        psychologist.setActive(career.getHasPsychology());

        ShopItem trainer = new ShopItem();

        trainer.setId("entrenador");
        trainer.setName("Entrenador Personal");
        trainer.setDescription("Mejora la técnica (+2) y la finalización de jugadas (+1)");
        trainer.setPrice(600000);
        trainer.setActive(career.getHasTrainer());


        ShopItem boots = new ShopItem();

        boots.setId("botas");
        boots.setName("Botas Premium");
        boots.setDescription("Mejora la velocidad de movimiento (+2) y la finalización de jugadas (+1)");
        boots.setPrice(410000);
        boots.setActive(career.getHasBootsPremium());

        ShopItem imageAgent = new ShopItem();

        imageAgent.setId("asesor");
        imageAgent.setName("Asesor de imagen");
        imageAgent.setDescription("Mejora la reputación general del jugador (+3) y su relaciión con la prensa (+2)");
        imageAgent.setPrice(480000);
        imageAgent.setActive(career.getHasImageAgent());

        ShopItem videoAnalist = new ShopItem();

        videoAnalist.setId("analista");
        videoAnalist.setName("Analista de juego");
        videoAnalist.setDescription("Mejora el entendimiento del juego y la vision del jugador sobre el terreno de juego (+3)");
        videoAnalist.setPrice(475000);
        videoAnalist.setActive(career.getHasVideoAnalyst());

        ShopItem chef = new ShopItem();

        chef.setId("chef");
        chef.setName("Chef Personal");
        chef.setDescription("Mejora el físico (+1), la velocidad (+1) y forma del jugador y su confianza mental (+1)");
        chef.setPrice(450000);
        chef.setActive(career.getHasPrivateChef());



        List<ShopItem> items = new ArrayList<>();

        items.add(fisio);
        items.add(nutritionist);
        items.add(psychologist);
        items.add(trainer);
        items.add(boots);
        items.add(imageAgent);
        items.add(videoAnalist);
        items.add(chef);

       return items;


    }

    public Career buyItem(Long careerId, String itemId){

        Career myCareer = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // COMPROBAMOS CUANTO VALE EL ITEM CLICADO

        int itemPrice= 0;

        switch (itemId){
            case "fisio": itemPrice = 400000;
                break;
            case "nutricionista": itemPrice = 470000;
                break;
            case "psicologo": itemPrice = 490000;
                break;
            case "entrenador": itemPrice = 600000;
                break;
            case "botas": itemPrice = 410000;
                break;
            case "asesor": itemPrice = 480000;
                break;
            case "analista": itemPrice = 475000;
                break;
            case "chef": itemPrice = 450000;
                break;
            default: throw new RuntimeException("item no identificado");
        }

        // Si no hay dinero error
        if(myCareer.getSavings() < itemPrice){
            throw new RuntimeException("No tienes dinero suficiente");
        }


        // COMPROBAMOS SI YA LO TENEMOS COMPRADO ESE ITEM
        boolean alreadyPurchased = false;
        switch (itemId){
            case "fisio": alreadyPurchased = myCareer.getHasPhysio();
                break;
            case "nutricionista": alreadyPurchased = myCareer.getHasNutritionist();
                break;
            case "psicologo": alreadyPurchased = myCareer.getHasPsychology();
                break;
            case "entrenador": alreadyPurchased = myCareer.getHasTrainer();
                break;
            case "botas": alreadyPurchased = myCareer.getHasBootsPremium();
                break;
            case "asesor": alreadyPurchased = myCareer.getHasImageAgent();
                break;
            case "analista": alreadyPurchased = myCareer.getHasVideoAnalyst();
                break;
            case "chef": alreadyPurchased = myCareer.getHasPrivateChef();
                break;
            default: throw new RuntimeException("item no identificado");
        }

// Si lo tenemos ERROR
        if(alreadyPurchased){
            throw new RuntimeException("Ya lo tienes esta temporada");
        }

        // COMPROBAMOS CUANTOS ITEM TENEMOS ACTIVADOS
        int totalItemActive = 0;

        // Si alguno es true, totalItemActive +1
        if(myCareer.getHasPhysio()) totalItemActive++;
        if(myCareer.getHasNutritionist()) totalItemActive++;
        if(myCareer.getHasPsychology()) totalItemActive++;
        if(myCareer.getHasTrainer()) totalItemActive++;
        if(myCareer.getHasBootsPremium()) totalItemActive++;
        if(myCareer.getHasImageAgent()) totalItemActive++;
        if(myCareer.getHasVideoAnalyst()) totalItemActive++;
        if(myCareer.getHasPrivateChef()) totalItemActive++;

        // Si hay 3 o mas ERROR
        if(totalItemActive >= 3){
            throw new RuntimeException("Ya tienes 3 items activos");
        }

        // Restamos precio del dinero
        myCareer.setSavings(myCareer.getSavings() - itemPrice);

        // ACTIVAMOS EL ITEM
        switch (itemId){
            case "fisio": myCareer.setHasPhysio(true);
            break;
            case "nutricionista": myCareer.setHasNutritionist(true);
            break;
            case "psicologo": myCareer.setHasPsychology(true);
            break;
            case "entrenador": myCareer.setHasTrainer(true);
            break;
            case "botas": myCareer.setHasBootsPremium(true);
            break;
            case "asesor": myCareer.setHasImageAgent(true);
            break;
            case "analista": myCareer.setHasVideoAnalyst(true);
            break;
            case "chef": myCareer.setHasPrivateChef(true);
            break;
            default: throw new RuntimeException("item no identificado");
        }

        careerRepository.save(myCareer);

        return myCareer;
    }

}
