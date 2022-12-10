package com.enyoi.challenge4.aplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadosDTO {
    private Long id;
    private String numeroIdentificacion;
    private String nombreEmpleado;
    private CajasDTO cajasId;

}
