package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ActualizarFacturasProductosRequest {
    @NonNull
    private Long facturasProductosId;
    @NonNull
    private Double valorUnidad;
    @NonNull
    private int cantidad;
    @NonNull
    private Double total;
    @NonNull
    private Long productoId;
    @NonNull
    private Long facturasId;
}
