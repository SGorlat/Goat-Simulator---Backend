package com.goat.repository;


import com.goat.model.CareerSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerSeasonRepository extends JpaRepository<CareerSeason, Long> {

    List<CareerSeason> findByCareerIdOrderBySeasonNumberAsc(Long careerId);

}
