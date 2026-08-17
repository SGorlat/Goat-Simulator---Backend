package com.goat.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leagues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String country;

    @Column(nullable = false)
    private Integer level;

    @Column(length = 50)
    private String continent;



}
