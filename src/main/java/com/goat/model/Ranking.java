package com.goat.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "rankings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String playerName;

    @Column(nullable = false)
    private Integer totalScore;
    private Integer totalGoals;
    private Integer totalAssists;
    private Boolean ballonDor = false;
    private Boolean goldenFoot = false;
    private Boolean trophyKopa = false;

    @ManyToOne // cada carrera genera UN ranking al retirarse
    @JoinColumn(name = "career_id")
    private Career career;

    @Column(columnDefinition = "TEXT")
    private String titlesHistory; // Resumen titulos

    private Integer totalTitles = 0;

    private LocalDateTime createdAt;




}
