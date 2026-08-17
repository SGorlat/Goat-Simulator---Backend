package com.goat.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // "Delantero", "Mediapunta", "Extremo", "Mediocentro"

    // Weights para calcular OVR (suman 1.0) -> cuánto importa cada stat para esa posición
    private Double speedWeight;
    private Double techniqueWeight;
    private Double finishingWeight;
    private Double physicalWeight;
    private Double mentalWeight;
    private Double visionWeight;

    // Stats iniciales al crear carrera con esta posición
    private Integer initialSpeed;
    private Integer initialTechnique;
    private Integer initialFinishing;
    private Integer initialPhysical;
    private Integer initialMental;
    private Integer initialVision;
}


/*
@Entity → "esta clase es una tabla de la BBDD"
@Table(name = "positions") → "la tabla se llama positions"
@Data → Lombok genera getters, setters, toString automáticamente
@Id + @GeneratedValue → clave primaria autoincremental
@Column(nullable = false) → este campo no puede ser null
*/