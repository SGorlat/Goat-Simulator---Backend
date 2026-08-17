package com.goat.repository;

import com.goat.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    List<Ranking> findTop10ByOrderByTotalScoreDesc();
    Ranking findFirstByOrderByTotalGoalsDesc();
    Ranking findFirstByOrderByTotalAssistsDesc();

}
