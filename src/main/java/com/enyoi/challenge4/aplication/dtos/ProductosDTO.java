package com.enyoi.challenge4.aplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductosDTO {

    private Long id;
    private String nombreProductos;
    private Double valorUnidad;
    private Long estado;

}
