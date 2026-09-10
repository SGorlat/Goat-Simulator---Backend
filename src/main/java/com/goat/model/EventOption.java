package com.goat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_options")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Texto del evento
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    // Mod -> Modifiers
    private Integer speedMod = 0;
    private Integer techniqueMod = 0;
    private Integer finishingMod = 0;
    private Integer physicalMod = 0;
    private Integer mentalMod = 0;
    private Integer visionMod = 0;
    private Integer reputationMod = 0;
    private Integer savingsMod = 0; // Dinero qwue gana o pierde con la decision (ahorros)

    private Integer coachRelationMod = 0;
    private Integer teamRelationMod = 0;
    private Integer fansRelationMod = 0;
    private Integer pressRelationMod = 0;
    private Integer agentRelationMod = 0;
    private Integer boardRelationMod = 0;

    // Texto post evento
    @Column(nullable = false, columnDefinition = "TEXT")
    private String outcomeText;
    
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonIgnore // Para que no inclkuya el event padre en el JSON que devuelve
    private Event event;

    private String flag;

}


