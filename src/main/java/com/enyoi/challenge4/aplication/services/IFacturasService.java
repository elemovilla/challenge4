package com.enyoi.challenge4.aplication.services;

import com.enyoi.challenge4.aplication.dtos.FacturasDTO;
import com.enyoi.challenge4.aplication.payloads.requests.CrearFacturasRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IFacturasService {

    List<FacturasDTO> findAll();

    FacturasDTO findById(Long id);

    Page<FacturasDTO> findByClientesId(Long clientesId, int page, int size, String columnFilter, Sort.Direction direction);
    Page<FacturasDTO> findByEmpleadosId(Long empleadosId, int page, int size, String columnFilter, Sort.Direction direction);

    List<FacturasDTO> findByValorUnidad(Double valorUnidad);
    List<FacturasDTO> findByTotal(Double total);

    FacturasDTO findByNoFacturas(Long noFacturas);
    FacturasDTO create(CrearFacturasRequest crearFacturasRequest);

}
