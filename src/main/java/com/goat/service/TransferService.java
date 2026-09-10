package com.goat.service;

import com.goat.dto.TransferOffer;
import com.goat.model.Career;
import com.goat.model.CareerSeason;
import com.goat.model.League;
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

    int lvlOffer = 0;
    if (seasons.isEmpty()) {
        lvlOffer = 1;  // sin temporadas aun, estamos en academia, ofertas lvl1
    }else{


    CareerSeason lastSeason = seasons.getLast(); // seasons.get(seasons.size() - 1);


    // Según  rating y reputación, decidir qué nivel de club m busca
    double rating = lastSeason.getSeasonRating();
    int reputation = career.getReputation();



    if(rating > 8 && reputation > 70){
        // club de nivel 3 (élite)
        lvlOffer = 3;
    } else if (rating > 6 && reputation > 40) {
        // club de nivel 2
        lvlOffer = 2;
    }else{
        lvlOffer = 1;
     }
    }

    // Buscar equipo del lvl correspondiente - lista de equipos disponibles

    List<Team> teams = teamRepository.findByLeagueLevel(lvlOffer);

        // Si tengo equipo: Eliminamos mi equipo actual de la lista, elimiando aquellos cuyo id sea = al id de mi equipo actual
    if(career.getCurrentTeam() != null){
        teams.removeIf(t -> t.getId().equals(career.getCurrentTeam().getId()));
    }

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

        if (myOverallRating > team.getPrestige() + 20) {
            offer.setRole("Estrella");
        } else if (myOverallRating > team.getPrestige() + 5) {
            offer.setRole("Titular");
        } else if (myOverallRating > team.getPrestige() - 10) {
            offer.setRole("Rotación");
        } else {
            offer.setRole("Suplente");
        }

        // AÑOS CONTRATO, cuanto mejor rol mas años ofrecen
        if (offer.getRole().equals("Estrella")) {
            offer.setContractYears(3 + random.nextInt(2));    // 3-4 años
        } else if (offer.getRole().equals("Titular")) {
            offer.setContractYears(2 + random.nextInt(2));    // 2-3 años
        } else if (offer.getRole().equals("Rotación")) {
            offer.setContractYears(1 + random.nextInt(2));    // 1-2 años
        } else {
            offer.setContractYears(1);                         // 1 año
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


    // OFERTA DE TOP CLUB
    if(career.hasFlag("interes_topclub")){
        List<Team> teamsLvl3 = teamRepository.findByLeagueLevel(3);

        // Si tengo equipo: Eliminamos mi equipo actual de la lista, elimiando aquellos cuyo id sea = al id de mi equipo actual
        if(career.getCurrentTeam() != null){
            teamsLvl3.removeIf(t -> t.getId().equals(career.getCurrentTeam().getId()));
        }

        // Escogemos un equipo aleat de la lista de lvl 3 TOP
        if (!teamsLvl3.isEmpty()) {
        Team topTeam = teamsLvl3.get(random.nextInt(teamsLvl3.size()));

        TransferOffer offer = new TransferOffer(); // Creamos una oferta nueva vacía
            offer.setTeamId(topTeam.getId()); // id del equipo
            offer.setTeamName(topTeam.getName()); // nombre
            offer.setLeagueName(topTeam.getLeague().getName()); // liga en la quie esta
            offer.setLeagueLevel(topTeam.getLeague().getLevel());

            int baseSalary = 2000000;
            int offeredSalary = baseSalary + (topTeam.getPrestige() * 10000) + (career.getReputation() * 5000);
            offer.setOfferedSalary(offeredSalary);

            int myOverallRating = (career.getSpeed() + career.getTechnique() + career.getFinishing()
                    + career.getPhysical() + career.getMental() + career.getVision()) / 6;

            if (myOverallRating > topTeam.getPrestige() + 20) {
                offer.setRole("Estrella");
                offer.setContractYears(3 + random.nextInt(2));
            } else if (myOverallRating > topTeam.getPrestige() + 5) {
                offer.setRole("Titular");
                offer.setContractYears(2 + random.nextInt(2));
            } else if (myOverallRating > topTeam.getPrestige() - 10) {
                offer.setRole("Rotación");
                offer.setContractYears(1 + random.nextInt(2));
            } else {
                offer.setRole("Suplente");
                offer.setContractYears(1);
            }

            offers.add(offer);
        }
    }


    // OFERTA - INTERES DE UN CLUB DE X LIGA
    if (career.hasFlag("interes_liga")) {
        String leagueName = career.getFlags().split("interes_liga:")[1].split(",")[0];

        // Comprobar que no juego ya en esa liga
        boolean sameLeague = career.getCurrentTeam() != null
                && career.getCurrentTeam().getLeague().getName().equals(leagueName);

        if (!sameLeague) {
            List<Team> teamsOfLeague = teamRepository.findByLeagueName(leagueName);

            if (career.getCurrentTeam() != null) {
                teamsOfLeague.removeIf(t -> t.getId().equals(career.getCurrentTeam().getId()));
            }

            if (!teamsOfLeague.isEmpty()) {
                Team leagueTeam = teamsOfLeague.get(random.nextInt(teamsOfLeague.size()));

                TransferOffer offer = new TransferOffer(); // Creamos una oferta nueva vacía
                offer.setTeamId(leagueTeam.getId()); // id del equipo
                offer.setTeamName(leagueTeam.getName()); // nombre
                offer.setLeagueName(leagueTeam.getLeague().getName()); // liga en la quie esta
                offer.setLeagueLevel(leagueTeam.getLeague().getLevel());

                // Salario base según nivel de liga
                int baseSalary;
                if (leagueTeam.getLeague().getLevel() == 3) {
                    baseSalary = 2000000;    // élite: base 2M
                } else if (leagueTeam.getLeague().getLevel() == 2) {
                    baseSalary = 500000;     // medio: base 500K
                } else {
                    baseSalary = 100000;     // bajo: base 100K
                }

                // El prestige y tu reputation ajustan hacia arriba
                int offeredSalary = baseSalary + (leagueTeam.getPrestige() * 10000) + (career.getReputation() * 5000);

                offer.setOfferedSalary(offeredSalary);

                int myOverallRating = (career.getSpeed() + career.getTechnique() + career.getFinishing()
                        + career.getPhysical() + career.getMental() + career.getVision()) / 6;

                if (myOverallRating > leagueTeam.getPrestige() + 20) {
                    offer.setRole("Estrella");
                    offer.setContractYears(3 + random.nextInt(2));
                } else if (myOverallRating > leagueTeam.getPrestige() + 5) {
                    offer.setRole("Titular");
                    offer.setContractYears(2 + random.nextInt(2));
                } else if (myOverallRating > leagueTeam.getPrestige() - 10) {
                    offer.setRole("Rotación");
                    offer.setContractYears(1 + random.nextInt(2));
                } else {
                    offer.setRole("Suplente");
                    offer.setContractYears(1);
                }

                offers.add(offer);

            }
        }
    }


    // INTERES EQUIPO CUALQUIERA
    if (career.hasFlag("interes_equipo")){
        String teamName = career.getFlags().split("interes_equipo:")[1].split(",")[0];

        Team teamInterested = teamRepository.findByName(teamName);

        if(teamInterested != null && (career.getCurrentTeam() == null || !teamInterested.getId().equals(career.getCurrentTeam().getId()))){

            TransferOffer offer = new TransferOffer(); // Creamos una oferta nueva vacía
            offer.setTeamId(teamInterested.getId()); // id del equipo
            offer.setTeamName(teamInterested.getName()); // nombre
            offer.setLeagueName(teamInterested.getLeague().getName()); // liga en la quie esta
            offer.setLeagueLevel(teamInterested.getLeague().getLevel());

            // Salario base según nivel de liga
            int baseSalary;
            if (teamInterested.getLeague().getLevel() == 3) {
                baseSalary = 2000000;    // élite: base 2M
            } else if (teamInterested.getLeague().getLevel() == 2) {
                baseSalary = 500000;     // medio: base 500K
            } else {
                baseSalary = 100000;     // bajo: base 100K
            }

            // El prestige y tu reputation ajustan hacia arriba
            int offeredSalary = baseSalary + (teamInterested.getPrestige() * 10000) + (career.getReputation() * 5000);

            offer.setOfferedSalary(offeredSalary);

            int myOverallRating = (career.getSpeed() + career.getTechnique() + career.getFinishing()
                    + career.getPhysical() + career.getMental() + career.getVision()) / 6;

            if (myOverallRating > teamInterested.getPrestige() + 20) {
                offer.setRole("Estrella");
                offer.setContractYears(3 + random.nextInt(2));
            } else if (myOverallRating > teamInterested.getPrestige() + 5) {
                offer.setRole("Titular");
                offer.setContractYears(2 + random.nextInt(2));
            } else if (myOverallRating > teamInterested.getPrestige() - 10) {
                offer.setRole("Rotación");
                offer.setContractYears(1 + random.nextInt(2));
            } else {
                offer.setRole("Suplente");
                offer.setContractYears(1);
            }

            offers.add(offer);

        }

    }

    // INTERES CESION (solo si eres Suplente o Rotación)
    if (career.hasFlag("interes_cesion")) {
        String currentRole = career.getCurrentRole();
        if (currentRole != null && (currentRole.equals("Suplente") || currentRole.equals("Rotación"))) {

            int currentLevel = career.getCurrentTeam().getLeague().getLevel();
            int searchLevel = Math.max(1, currentLevel - 1); // BUSCAMOS NIVEL IGUAL O IONFERIOR A MI LIGA

            List<Team> cessionTeams = teamRepository.findByLeagueLevel(searchLevel);

            if (career.getCurrentTeam() != null) {
                cessionTeams.removeIf(t -> t.getId().equals(career.getCurrentTeam().getId()));
            }

            if (!cessionTeams.isEmpty()) {
                Team cessionTeam = cessionTeams.get(random.nextInt(cessionTeams.size()));

                TransferOffer offer = new TransferOffer();
                offer.setTeamId(cessionTeam.getId());
                offer.setTeamName(cessionTeam.getName() + " (Cesión)");
                offer.setLeagueName(cessionTeam.getLeague().getName());
                offer.setLeagueLevel(cessionTeam.getLeague().getLevel());

                int baseSalary;
                if (cessionTeam.getLeague().getLevel() == 3) {
                    baseSalary = 1000000;
                } else if (cessionTeam.getLeague().getLevel() == 2) {
                    baseSalary = 300000;
                } else {
                    baseSalary = 80000;
                }

                int offeredSalary = baseSalary + (cessionTeam.getPrestige() * 5000);
                offer.setOfferedSalary(offeredSalary);
                offer.setRole("Titular");
                offer.setContractYears(1);

                offers.add(offer);
            }
        }
    }



    return offers;

}

    public Career acceptOffer(Long careerId, TransferOffer offer) {

        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        Team newTeam = teamRepository.findById(offer.getTeamId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        // Si el equipo es el mismo que el actual seteamos nuevas relacioones
        if (career.getCurrentTeam() != null && career.getCurrentTeam().getId().equals(offer.getTeamId())) {
            // Renovación — lealtad premia
            career.setFansRelation(Math.min(100, career.getFansRelation() + 15));
            career.setBoardRelation(Math.min(100, career.getBoardRelation() + 10));
            career.setTeamRelation(Math.min(100, career.getTeamRelation() + 10));
        } else {
            // Si no es el mismo establecemos las relaciones en 50 por defecto con el nuevo equipo
            career.setFansRelation(50);
            career.setBoardRelation(50);
            career.setTeamRelation(50);
            career.setCoachRelation(50);
            career.setPressRelation(40);
        }

        // Seteamos el nuevo equipo,salario,rol y años de contrato de nuestra carrera
        career.setCurrentTeam(newTeam);
        career.setSalary(offer.getOfferedSalary());
        career.setCurrentRole(offer.getRole());
        career.setContractYearsLeft(offer.getContractYears());

        return careerRepository.save(career);
    }

}
