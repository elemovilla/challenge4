package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CrearProductosRequest {

    @NotBlank
    private String nombreProductos;

    @NonNull
    private Double valorUnidad;


    @NonNull
    private Long estado;
}
