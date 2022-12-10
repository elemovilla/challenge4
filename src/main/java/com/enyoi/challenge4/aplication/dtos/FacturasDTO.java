package com.enyoi.challenge4.aplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturasDTO {

    private Long id;
    private Long noFacturas;
    private Double valorUnidad;
    private int cantidad;
    private Double total;
    private ClientesDTO clientesId;
    private EmpleadosDTO empleadosId;
}
