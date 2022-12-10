package com.enyoi.challenge4.aplication.services;


import com.enyoi.challenge4.aplication.dtos.CajasDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarCajasRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearCajasRequest;

import java.util.List;

public interface ICajasService {

    List<CajasDTO> findAll();

    CajasDTO findById(Long id);

    CajasDTO findByNoCajas(String noCajas);
    CajasDTO create(CrearCajasRequest crearCajasRequest);

    CajasDTO update(ActualizarCajasRequest actualizarCajasRequest);

    String delete(Long id);
}
