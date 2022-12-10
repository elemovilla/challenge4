package com.enyoi.challenge4.aplication.services;


import com.enyoi.challenge4.aplication.dtos.ProductosDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarProductosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearProductosRequest;

import java.util.List;

public interface IProductosService {

    List<ProductosDTO> findAll();
    ProductosDTO findById(Long id);
    ProductosDTO findByNombreProductos(String nombreProductos);
    ProductosDTO findByEstado(Long estado);
    ProductosDTO create(CrearProductosRequest crearProductosRequest);

    ProductosDTO update(ActualizarProductosRequest actualizarProductosRequest);

    String delete(Long id);
}
