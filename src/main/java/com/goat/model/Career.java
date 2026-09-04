package com.goat.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "career")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String playerName;

    @Column(length = 50)
    private String nationality;
    private Integer currentAge = 16;
    private Integer speed = 30;
    private Integer technique = 30 ;
    private Integer finishing = 30;
    private Integer physical = 30;
    private Integer mental = 30;
    private Integer vision = 30;
    private Integer reputation = 30;
    private Integer totalGoals = 0;
    private Integer totalAssists = 0;
    private Integer totalTitles = 0;

    private Integer salary = 500;
    private Integer savings = 0;

    // BOOST
    private Boolean hasPhysio = false;
    private Boolean hasNutritionist = false;
    private Boolean hasPsychology = false;
    private Boolean hasTrainer = false;
    private Boolean hasBootsPremium = false;
    private Boolean hasImageAgent = false;
    private Boolean hasVideoAnalyst = false;
    private Boolean hasPrivateChef = false;

    private Double growthMultiplier = 1.0;

// Como nos percibe el entorno
    private Integer coachRelation = 50;      // entrenador
    private Integer teamRelation = 50;       // compañeros
    private Integer fansRelation = 50;       // afición
    private Integer pressRelation = 50;      // prensa
    private Integer agentRelation = 50;      // representante
    private Integer boardRelation = 50;      // directiva

    // Flags/careerHistory — memoria del juego para decisiones (JSON como String)
    @Column(columnDefinition = "TEXT")
    private String careerHistory = "{}";

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Column(length = 20)
    private String side; // elegir izq o dcha (solo extremo)

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team currentTeam;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerSeason> seasons;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerEventLog> eventLog;

    @Column(length = 20)
    private  String status = "active";

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competitor> competitors;

    // ROL
    @Column(name="player_role", length = 20)
    private String currentRole = "Cantera";

    private Integer contractYearsLeft = 0;

    private int preseasonStep = 0; // 0=no empezada, 1=vacaciones hecha, 2=preparación hecha, 3=completa

    private LocalDateTime createdAt;



}
