package com.goat.repository;

import com.goat.model.Career;
import com.goat.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    // dame todos los eventos donde la edad del jugador esté en el rango y el nivel de liga esté en el rango y la fase sea la correcta
    @Query("SELECT e FROM Event e WHERE e.minAge <= :age AND e.maxAge >=  :age AND e.minLeagueLevel <= :level AND e.maxLeagueLevel >= :level AND e.phase = :phase")
    List<Event> findEligibleEvents(@Param("age") Integer age, @Param("level") Integer level, @Param("phase") String phase);




}
