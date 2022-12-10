package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ActualizarEmpleadosRequest {

    @NonNull
    private Long empleadosId;

    @NotBlank
    private String numeroIdentificacion;

    @NotBlank
    private String nombreEmpleado;

    @NotBlank
    private String noCaja;
}
