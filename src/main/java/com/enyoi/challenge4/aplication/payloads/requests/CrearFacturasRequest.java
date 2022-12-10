package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CrearFacturasRequest {

    @NotNull
    private Long noFacturas;
    @NonNull
    private Double valorUnidad;
    @NotNull
    private int cantidad;
    @NonNull
    private Double total;

    @NotNull
    private Long clientesId;

    @NotNull
    private Long empleadosId;
}
