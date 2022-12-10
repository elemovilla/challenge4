package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CrearCajasRequest {

    @NotBlank
    private String noCajas;


}
