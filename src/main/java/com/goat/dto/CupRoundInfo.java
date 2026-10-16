package com.goat.dto;

import lombok.Data;

@Data
public class CupRoundInfo {
    private int round;
    private String roundName;
    private String rivalName;
    private boolean isFinal; // si es la final
}
