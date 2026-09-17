package com.goat.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // crea un campo TEXT  para textos largos
    private String description;

    @Column(nullable = false,length = 50)
    private String category;


    private Integer minAge = 16;
    private Integer maxAge = 42;
    private Integer minLeagueLevel = 1;
    private Integer maxLeagueLevel = 4;

    @Column(length = 20)
    private String type = "decision";  // "informativo", "decision", "cadena", "unico"

    @Column(length =  20)
    private String phase = "profesional"; // "academia", "profesional", "pretemporada", "offseason"

    @Column(name = "unlocks_event_id")
    private Long unlocksEventId;       // id del evento que desbloquea (null si no encadena)

    @Column(length = 20)
    private String rarity = "common";

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventOption> options;

    private String minigame;


}
