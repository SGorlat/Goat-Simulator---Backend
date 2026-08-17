package com.goat.repository;

import com.goat.model.EventOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOptionRepository extends JpaRepository<EventOption, Long> {


}
