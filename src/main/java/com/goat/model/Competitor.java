package com.goat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "competitors")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Competitor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String nationality;

    @Column(nullable = false)
    private Integer age;


    private Integer speed = 30;
    private Integer technique = 30 ;
    private Integer finishing = 30;
    private Integer physical = 30;
    private Integer mental = 30;
    private Integer vision = 30;
    private Integer reputation = 30;

    @Column(nullable = false)
    private Double potential; // det cuanto creceran sus stats


    private Integer seasonGoals = 0;
    private Integer seasonAssists = 0;
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "career_id", nullable = false)
    @JsonIgnore
    private Career career;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team currentTeam;









}
