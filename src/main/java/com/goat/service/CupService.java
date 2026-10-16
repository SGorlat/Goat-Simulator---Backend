package com.goat.service;

import com.goat.dto.CupRoundInfo;
import com.goat.model.Career;
import com.goat.model.Team;
import com.goat.repository.CareerRepository;
import com.goat.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CupService {

    private final CareerRepository careerRepository;
    private final TeamRepository teamRepository;


    public CupService(CareerRepository careerRepository,TeamRepository teamRepository){
        this.careerRepository = careerRepository;
        this.teamRepository = teamRepository;
    }

    public CupRoundInfo getCurrentRound(Long careerId){

        Career myCareer = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));


        if(myCareer.getCupEliminated() || myCareer.getCurrentTeam() == null || myCareer.getCupRound() >= 5){
            return null;
        }

        List<Team> rivalsCup = teamRepository.findByLeagueId(myCareer.getCurrentTeam().getLeague().getId());

        Random random = new Random();

        if(myCareer. getCupRound() == 0){
            myCareer.setCupRound(1);
        }


        // quitamos a mi equipo de la bsuqueda(ewn todas las rondas)
        rivalsCup.removeIf(t -> t.getId().equals(myCareer.getCurrentTeam().getId()));

        // sacamos rival de esa lista de rivalsCup con .get() y el aleat(ewn todas las rondas)
        Team rivalTeam = rivalsCup.get(random.nextInt(rivalsCup.size()));


        // Seteamnos info de la ronda,rival y si es la final o no
        CupRoundInfo cupRoundInfo = new CupRoundInfo();
        cupRoundInfo.setRound(myCareer.getCupRound());
        cupRoundInfo.setRivalName(rivalTeam.getName());

        if(myCareer.getCupRound() == 4){
            cupRoundInfo.setFinal(true);
        } else {
            cupRoundInfo.setFinal(false);
        }

        // le damos nombre a la ronda
        switch (myCareer.getCupRound()){
            case 1: cupRoundInfo.setRoundName("Octavos de final"); break;
            case 2: cupRoundInfo.setRoundName("Cuartos de final"); break;
            case 3: cupRoundInfo.setRoundName("Semifinales"); break;
            case 4: cupRoundInfo.setRoundName("Final"); break;
        }

        careerRepository.save(myCareer);

        return cupRoundInfo;

    }


// Muestra el resultado de cada ronda de copa
    public String cupResult(Long careerId, Boolean won){

        Career myCareer = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        if(won){
            myCareer.setCupRound(myCareer.getCupRound() + 1);
        }else{
            myCareer.setCupEliminated(true);

            switch (myCareer.getCupRound()){
                case 1: myCareer.setCupResult("Eliminado en octavos de final"); break;
                case 2: myCareer.setCupResult("Eliminado en cuartos de final"); break;
                case 3: myCareer.setCupResult("Eliminado en semifinales"); break;
                case 4: myCareer.setCupResult("Eliminado en la final,quedaste subcampeón"); break;
            }
        }

        if(myCareer.getCupRound() == 5){
            myCareer.setCupResult("¡Campeón de Copa!");
        }

        careerRepository.save(myCareer);

        return myCareer.getCupResult();

    }


}
