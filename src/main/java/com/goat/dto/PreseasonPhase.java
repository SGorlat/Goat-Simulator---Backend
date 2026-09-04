package com.goat.dto;

import lombok.Data;

import java.util.List;

@Data
public class PreseasonPhase {

    private String id;
    private String title;
    private String description;
    private List<PreseasonOption> options; // lista de opciones de la fase
}
