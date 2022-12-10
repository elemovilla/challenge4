package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ActualizarClientesRequest {

    @NonNull
    private Long clienteId;


    @NotBlank
    private String tipoIdentificacion;


    @NotBlank
    private String numeroIdentificacion;

    @NotBlank
    private String primerNombre;

    @NotBlank
    private String segundoNombre;


    @NotBlank
    private String primerApellido;


    @NotBlank
    private String segundoApellido;


    @NotBlank
    private String direccion;
}
