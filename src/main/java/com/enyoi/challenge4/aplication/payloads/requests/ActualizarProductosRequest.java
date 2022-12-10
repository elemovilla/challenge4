package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActualizarProductosRequest {

    @NonNull
    private Long productosId;

    @NotBlank
    private String nombreProductos;

    @NonNull
    private Double valorUnidad;


    @NotNull
    private Long estado;
}
