package com.goat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "career_event_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerEventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer seasonNumber;
    private Integer optionChosen;
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "career_id", nullable = false)
    @JsonIgnore
    private Career career;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonIgnore
    private Event event;

}
