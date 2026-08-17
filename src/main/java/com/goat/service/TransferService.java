package com.goat.service;

import com.goat.dto.TransferOffer;
import com.goat.model.Career;
import com.goat.model.CareerSeason;
import com.goat.model.Team;
import com.goat.repository.CareerRepository;
import com.goat.repository.CareerSeasonRepository;
import com.goat.repository.LeagueRepository;
import com.goat.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TransferService {

    private final CareerRepository careerRepository; // leer/actualizar la carrera
    private final TeamRepository teamRepository; // buscar equipos que puedan fichar
    private final LeagueRepository leagueRepository; // buscar ligas por nivel
    private final CareerSeasonRepository careerSeasonRepository; // ver rendimiento de la última temporada


public TransferService(CareerRepository careerRepository,CareerSeasonRepository careerSeasonRepository, LeagueRepository leagueRepository, TeamRepository teamRepository){
    this.careerRepository = careerRepository;
    this.careerSeasonRepository = careerSeasonRepository;
    this.leagueRepository = leagueRepository;
    this.teamRepository = teamRepository;
}


// Genera lista de ofertas
public List<TransferOffer> generateOffer(Long careerId){

    // Buscamos carrera
    Career career = careerRepository.findById(careerId).orElseThrow(()-> new RuntimeException("Carrera no encontrada"));


    // Buscamos temporada
    List<CareerSeason> seasons = careerSeasonRepository.findByCareerIdOrderBySeasonNumberAsc(careerId);


    if (seasons.isEmpty()) {
        return new ArrayList<>();  // sin temporadas aun, no hay ofertas, array vacio
    }

    CareerSeason lastSeason = seasons.getLast(); // seasons.get(seasons.size() - 1);


    // Según  rating y reputación, decidir qué nivel de club m busca
    double rating = lastSeason.getSeasonRating();
    int reputation = career.getReputation();

    int lvlOffer = 0;

    if(rating > 8 && reputation > 70){
        // club de nivel 3 (élite)
        lvlOffer = 3;
    } else if (rating > 6 && reputation > 40) {
        // club de nivel 2
        lvlOffer = 2;
    }else{
        lvlOffer = 1;
    }

    // Buscar equipo del lvl correspondiente - lista de equipos disponibles

    List<Team> teams = teamRepository.findByLeagueLevel(lvlOffer);

        // Eliminamos mi equipo actual de la lista, elimiando aquellois cuyo id sea = al id de mi equipo actual

        teams.removeIf(t -> t.getId().equals(career.getCurrentTeam().getId()));

    // Elegir 3 aleatorios

    Random random = new Random();
    List<TransferOffer> offers = new ArrayList<>(); // Para guardar ofertas que generamos


    // repetirmos 3 veces
    for(int i=0; i<3; i++){
        Team team = teams.get(random.nextInt(teams.size())); // Elegimos un equipo aleatorio de la lista
        teams.remove(team); // Lo quitamos de la lista para que no salga dos veces

        TransferOffer offer = new TransferOffer(); // Creamos una oferta nueva vacía
        offer.setTeamId(team.getId()); // id del equipo
        offer.setTeamName(team.getName()); // nombre
        offer.setLeagueName(team.getLeague().getName()); // liga en la quie esta
        offer.setLeagueLevel(team.getLeague().getLevel()); // nivel liga


        // Salario base según nivel de liga
        int baseSalary;
        if (team.getLeague().getLevel() == 3) {
            baseSalary = 2000000;    // élite: base 2M
        } else if (team.getLeague().getLevel() == 2) {
            baseSalary = 500000;     // medio: base 500K
        } else {
            baseSalary = 100000;     // bajo: base 100K
        }

        // El prestige y tu reputation ajustan hacia arriba
        int offeredSalary = baseSalary + (team.getPrestige() * 10000) + (career.getReputation() * 5000);

        offer.setOfferedSalary(offeredSalary);

        // Rol — segun si mi OVR es >< que el prestige del equipo me dan rol
        int myOverallRating = (career.getSpeed() + career.getTechnique() + career.getFinishing()
                + career.getPhysical() + career.getMental() + career.getVision()) / 6;

        if (myOverallRating > team.getPrestige()) {
            offer.setRole("Estrella");
        } else if (myOverallRating > team.getPrestige() - 10) {
            offer.setRole("Titular");
        } else if (myOverallRating > team.getPrestige() - 20) {
            offer.setRole("Rotación");
        } else {
            offer.setRole("Suplente");
        }

        // AÑOS CONTRATO, cuanto mejor rol mas años ofrecen

        if (offer.getRole().equals("Estrella")) {
            offer.setContractYears(4 + random.nextInt(2));    // 4-5 años
        } else if (offer.getRole().equals("Titular")) {
            offer.setContractYears(3 + random.nextInt(2));    // 3-4 años
        } else if (offer.getRole().equals("Rotación")) {
            offer.setContractYears(2 + random.nextInt(2));    // 2-3 años
        } else {
            offer.setContractYears(1 + random.nextInt(2));    // 1-2 años
        }

        offers.add(offer); // añadimos la oferta a la lista de ofertas
    }

// RENOVACION si queda 1 año o menos
    if(career.getContractYearsLeft() <=1){
        Team currentTeam = career.getCurrentTeam();         //Obtenemos equipo actual
        if(currentTeam != null){ // enm academia no se renueva
            TransferOffer renewal = new TransferOffer(); // Creamos oferta con los datos
            renewal.setTeamName(currentTeam.getName() + (" (Renovación de contrato)"));
            renewal.setTeamId(currentTeam.getId());
            renewal.setLeagueName(currentTeam.getLeague().getName());
            renewal.setLeagueLevel(currentTeam.getLeague().getLevel());
            renewal.setOfferedSalary(career.getSalary() + (career.getReputation() * 100)); // salario actual + plus de renovacion
            renewal.setRole(career.getCurrentRole());
            renewal.setContractYears(2 + random.nextInt(2)); // 2-3 años 
            offers.add(renewal);
        }
    }

    return offers;

}

    public Career acceptOffer(Long careerId, TransferOffer offer) {

        // Buscamos carrera
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Buscar el equipo nuevo por su id
        Team newTeam = teamRepository.findById(offer.getTeamId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        // Cambiar/asignar a mi career el equipo nuevo, el salario y el rol.
        career.setCurrentTeam(newTeam);
        career.setSalary(offer.getOfferedSalary());
        career.setCurrentRole(offer.getRole());
        career.setContractYearsLeft(offer.getContractYears());

        // Guardar y devolver
        return careerRepository.save(career);
    }

}
