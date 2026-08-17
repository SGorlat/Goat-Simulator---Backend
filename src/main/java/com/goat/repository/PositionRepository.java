package com.goat.repository;

import com.goat.model.Position;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{



}
