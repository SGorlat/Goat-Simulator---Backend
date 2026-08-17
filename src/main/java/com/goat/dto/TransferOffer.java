package com.goat.dto;

import lombok.Data;

@Data
public class TransferOffer {
    private Long teamId;
    private String teamName;
    private String leagueName;
    private int leagueLevel;
    private int offeredSalary;
    private String role;  // "titular", "rotación", "suplente"
    private int contractYears;
}

//dto para tranferir estos datos del backend al front,enviarle el equipo o equiipos que nos realiza la oferta con esos datos