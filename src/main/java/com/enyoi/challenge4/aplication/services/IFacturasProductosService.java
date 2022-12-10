package com.enyoi.challenge4.aplication.services;

import com.enyoi.challenge4.aplication.dtos.FacturasProductosDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarFacturasProductosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearFacturasProductosRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import java.util.List;


public interface IFacturasProductosService {

    List<FacturasProductosDTO> findAll();

    FacturasProductosDTO findById(Long id);


    Page<FacturasProductosDTO> findByFacturasId(Long facturasId, int page, int size, String columnFilter, Sort.Direction direction);

    Page<FacturasProductosDTO> findByProductosId(Long productosId, int page, int size, String columnFilter, Sort.Direction direction);

    List<FacturasProductosDTO> create(List<CrearFacturasProductosRequest> crearFacturasProductosRequest);

    FacturasProductosDTO update(ActualizarFacturasProductosRequest actualizarFacturasProductosRequest);

    String delete(Long id);
}
