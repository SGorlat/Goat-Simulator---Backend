package com.goat.service;

import com.goat.model.Career;
import com.goat.model.CareerSeason;
import com.goat.model.Ranking;
import com.goat.repository.CareerRepository;
import com.goat.repository.CareerSeasonRepository;
import com.goat.repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RankingService {

private final RankingRepository rankingRepository;
private final CareerRepository careerRepository;
private final CareerSeasonRepository careerSeasonRepository;

public RankingService(RankingRepository rankingRepository,CareerRepository careerRepository,CareerSeasonRepository careerSeasonRepository){
    this.rankingRepository = rankingRepository;
    this.careerRepository = careerRepository;
    this.careerSeasonRepository = careerSeasonRepository;
}

public Ranking saveToRanking(Long careerId){

    Career myCareer = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

    // Buscamos las temporadas de esta career
    List<CareerSeason> seasons = careerSeasonRepository.findByCareerIdOrderBySeasonNumberAsc(careerId);


    Ranking myRanking = new Ranking();

    myRanking.setPlayerName(myCareer.getPlayerName());
    myRanking.setTotalGoals(myCareer.getTotalGoals());
    myRanking.setTotalAssists(myCareer.getTotalAssists());
    myRanking.setTotalTitles(myCareer.getTotalTitles());
    myRanking.setCreatedAt(LocalDateTime.now());
    myRanking.setCareer(myCareer);

    // Comprobar si ganó premios individuales en alguna temporada
    boolean wonBallon = false;
    boolean wonKopa = false;
    boolean wonGoldenFoot = false;

    for(CareerSeason season: seasons){
        if (season.getTitlesWon() != null) {
            if (season.getTitlesWon().contains("Balón de Oro")) wonBallon = true;
            if (season.getTitlesWon().contains("Trofeo Kopa")) wonKopa = true;
            if (season.getTitlesWon().contains("Bota de Oro")) wonGoldenFoot = true;
        }
    }

    // Calcular puntuación total de carrera
    int goalPoints = myCareer.getTotalGoals() * 2;        // goles pesan x2
    int assistPoints = myCareer.getTotalAssists();          // asistencias pesan x1
    int reputationPoints = myCareer.getReputation() * 3;    // fama pesa x3
    int titlePoints = myCareer.getTotalTitles() * 50;       // cada título vale 50
    int awardPoints = 0;
    if (wonBallon) awardPoints += 100;     // Balón de Oro vale 100
    if (wonKopa) awardPoints += 30;        // Kopa vale 30
    if (wonGoldenFoot) awardPoints += 80;  // Bota de Oro vale 80

    int totalScore = goalPoints + assistPoints + reputationPoints + titlePoints + awardPoints;


    myRanking.setBallonDor(wonBallon);
    myRanking.setTrophyKopa(wonKopa);
    myRanking.setGoldenFoot(wonGoldenFoot);
    myRanking.setTotalScore(totalScore);

    return rankingRepository.save(myRanking);


}

public List<Ranking> getTopRankings(){
    return rankingRepository.findTop10ByOrderByTotalScoreDesc();
}


}
