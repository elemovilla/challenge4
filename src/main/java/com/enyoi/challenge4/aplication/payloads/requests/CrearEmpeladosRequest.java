package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CrearEmpeladosRequest {

    @NotBlank
    private String numeroIdentificacion;

    @NotBlank
    private String nombreEmpleado;

    @NonNull
    private Long cajasId;
}
