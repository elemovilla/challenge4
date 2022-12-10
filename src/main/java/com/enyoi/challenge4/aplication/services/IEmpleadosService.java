package com.enyoi.challenge4.aplication.services;

import com.enyoi.challenge4.aplication.dtos.EmpleadosDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarEmpleadosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearEmpeladosRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IEmpleadosService {

    List<EmpleadosDTO> findAll();

    EmpleadosDTO findById(Long id);

    EmpleadosDTO findByNumeroIdentificacion(String numeroIdentificacion);

    EmpleadosDTO findByNombreEmpleados(String nombreEmpleado);

    Page<EmpleadosDTO> findByCajasId(Long cajasId, int page, int size, String columnFilter, Sort.Direction direction);

    EmpleadosDTO create(CrearEmpeladosRequest crearEmpeladosRequest);

    EmpleadosDTO update(ActualizarEmpleadosRequest actualizarEmpleadosRequest);

    String delete(Long id);

}
