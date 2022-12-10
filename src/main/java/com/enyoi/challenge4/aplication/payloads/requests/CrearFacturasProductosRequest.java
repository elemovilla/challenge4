package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class CrearFacturasProductosRequest {


    private Double valorUnidad;
    @NonNull
    private int cantidad;

    private Double total;
    @NonNull
    private Long productoId;
    @NonNull
    private Long facturasId;

}
