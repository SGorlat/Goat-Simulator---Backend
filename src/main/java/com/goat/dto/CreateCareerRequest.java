package com.goat.dto;

import lombok.Data;

@Data
public class CreateCareerRequest {

    private String playerName;
    private Long positionId;
    private String nationality;

}


/*
* dto -> Data Transfer Objetct
* Contenedor de datos que vive en memoria y muere cuando se procesa la petición.
* Transportas datos entre el frontend y el bacakend, como un formulario"
* Trae estos al backend y devuelve lo que le indiquemos al front en otro dto
* */