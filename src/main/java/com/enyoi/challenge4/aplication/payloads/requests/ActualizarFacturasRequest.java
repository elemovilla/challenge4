package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ActualizarFacturasRequest {

    @NonNull
    private Long facturasId;
    @NotBlank
    private String noCaja;
    @NotBlank
    private String nombreEmpledo;
    @NotBlank
    private String noCajas;
    @NotBlank
    private String tipoIdentificacion;
    @NotBlank
    private String numeroIdentificacion;
    @NotBlank
    private String primerNombreCliente;
    @NonNull
    private Double valorUnidad;
    @NonNull
    private int cantidad;
    @NonNull
    private Double total;
}
