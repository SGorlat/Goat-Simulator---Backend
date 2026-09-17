package com.goat.repository;

import com.goat.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByLeagueId(Long leagueId);
    List<Team> findByLeagueLevel(Integer level);
    List<Team> findByLeagueName(String name);
    Team findByName(String name);
    List<Team> findByLeagueLevelLessThanEqual(Integer level); // busca equipos de mismo nivel o = al que recibe
}
