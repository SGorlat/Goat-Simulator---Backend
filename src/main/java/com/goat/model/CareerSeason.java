package com.goat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "career_seasons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerSeason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seasonNumber;

    @Column(nullable = false)
    private Integer age;

    private Integer goals = 0;
    private Integer assists = 0;

    @Column(columnDefinition = "TEXT")
    private String titlesWon;

    @Column(columnDefinition = "TEXT")
    private String bestMoment;

    private Double seasonRating;

    @ManyToOne // Tu razonamiento es correcto. Muchas temporadas pertenecen a una carrera, pero cada temporada tiene una sola carrera.
    @JoinColumn(name = "career_id", nullable = false)
    @JsonIgnore
    private Career career;

    @ManyToOne // muchas temporadas pueden pasar por equipos distintos, pero cada temporada concreta se juega en un solo equipo
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private Integer leaguePosition;

}
