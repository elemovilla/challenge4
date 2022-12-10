package com.enyoi.challenge4.aplication.payloads.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ActualizarCajasRequest {

    @NonNull
    private Long cajasId;

    @NotBlank
    private String noCajas;


}
