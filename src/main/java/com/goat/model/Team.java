package com.goat.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500, name = "crest_url")
    private String crestUrl;

    private Integer prestige = 50; // Por defecto

    @ManyToOne  // Muchos equipos pertenecen a UNA liga
    @JoinColumn(name = "league_id", nullable = false)  // La FK en la tabla se llama league_id
    private League league;


}
