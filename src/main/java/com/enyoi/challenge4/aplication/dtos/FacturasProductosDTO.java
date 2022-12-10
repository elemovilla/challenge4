package com.enyoi.challenge4.aplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturasProductosDTO {
    private Long id;
    private Double valorUnidad;
    private int cantidadProductos;
    private Double total;
    private FacturasDTO facturasId;
    private ProductosDTO productosId;
}
