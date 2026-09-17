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

    //
    if(career.getCurrentAge() > 35){
        lvlOffer = 1;
    }else if(career.getCurrentAge() > 32){
        lvlOffer = Math.max(1,lvlOffer - 1);
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

        TransferOffer offer = buildOffer(team, career, random);

        offers.add(offer); // añadimos la oferta a la lista de ofertas
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

            TransferOffer offer = buildOffer(topTeam, career, random);

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

                TransferOffer offer = buildOffer(leagueTeam, career, random);

                offers.add(offer);

            }
        }
    }


    // INTERES EQUIPO CUALQUIERA
    if (career.hasFlag("interes_equipo")){
        String teamName = career.getFlags().split("interes_equipo:")[1].split(",")[0];

        Team teamInterested = teamRepository.findByName(teamName);

        if(teamInterested != null && (career.getCurrentTeam() == null || !teamInterested.getId().equals(career.getCurrentTeam().getId()))){

            TransferOffer offer = buildOffer(teamInterested, career, random);

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

                TransferOffer offer = buildOffer(cessionTeam, career, random);
                offer.setTeamName(offer.getTeamName() + " (Cesión)");
                offer.setRole("Titular");
                offer.setContractYears(1);

                int baseSalaryCesion;
                if (cessionTeam.getLeague().getLevel() == 3) {
                    baseSalaryCesion = 1000000;
                } else if (cessionTeam.getLeague().getLevel() == 2) {
                    baseSalaryCesion = 300000;
                } else {
                    baseSalaryCesion = 80000;
                }
                offer.setOfferedSalary(baseSalaryCesion + (cessionTeam.getPrestige() * 5000));

                offers.add(offer);
            }
        }
    }



    return offers;

}

// HELPER PARA OFERTAS
    private TransferOffer buildOffer(Team team, Career career, Random random){

        TransferOffer offer = new TransferOffer(); // Creamos una oferta nueva vacía
        offer.setTeamId(team.getId()); // id del equipo
        offer.setTeamName(team.getName()); // nombre
        offer.setLeagueName(team.getLeague().getName()); // liga en la quie esta
        offer.setLeagueLevel(team.getLeague().getLevel());

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
        double prestigeMultiplier = 0.5 + (team.getPrestige() / 100.0);
        double reputationMultiplier = 0.5 + (career.getReputation() / 100.0);

        int offeredSalary = (int)(baseSalary * prestigeMultiplier * reputationMultiplier);
        offer.setOfferedSalary(offeredSalary);

        int myOverallRating = (career.getSpeed() + career.getTechnique() + career.getFinishing()
                + career.getPhysical() + career.getMental() + career.getVision()) / 6;

        if (myOverallRating > team.getPrestige() + 20) {
            offer.setRole("Estrella");
            offer.setContractYears(3 + random.nextInt(2));
        } else if (myOverallRating > team.getPrestige() + 5) {
            offer.setRole("Titular");
            offer.setContractYears(2 + random.nextInt(2));
        } else if (myOverallRating > team.getPrestige() - 10) {
            offer.setRole("Rotación");
            offer.setContractYears(1 + random.nextInt(2));
        } else {
            offer.setRole("Suplente");
            offer.setContractYears(1);
        }

        // Si tiene menos de 24 años el minimo de años ofrecidos será de 2
        if(career.getCurrentAge() < 24){
            offer.setContractYears(Math.max(2,offer.getContractYears())); //2 o los que ofertasen
        }

return offer;

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


    // RENOVACION si queda 1 año o menos
    public TransferOffer generateRenewal(Long careerId){

        Career myCareer = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        Random random = new Random();

        if(myCareer.getCurrentTeam() != null){
            TransferOffer renewal = buildOffer(myCareer.getCurrentTeam(), myCareer, random);
            renewal.setTeamName(myCareer.getCurrentTeam().getName() + " (Renovación de contrato)");
            renewal.setOfferedSalary(myCareer.getSalary() + (myCareer.getReputation() * 100));
            renewal.setRole(myCareer.getCurrentRole());
            renewal.setContractYears(2 + random.nextInt(2));

            return renewal;
        }else{
            return null;
        }

    }



}
