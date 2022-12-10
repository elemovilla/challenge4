package com.enyoi.challenge4.aplication.services;

import com.enyoi.challenge4.aplication.dtos.ClientesDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarClientesRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearClientesRequest;

import java.util.List;

public interface IClientesService {

    List<ClientesDTO> findAll();

    ClientesDTO findById(Long id);

    ClientesDTO findByTipoIdentificacion(String tipoIdentificacion);

    ClientesDTO findByNumeroIdentificacion(String numeroIdentificacion);

    ClientesDTO findByPrimerNombre(String primerNombre);

    ClientesDTO findBySegundoNombre(String segundoNombre);

    ClientesDTO findByPrimerApellido(String primerApellido);

    ClientesDTO findBySegundoApellido(String segundoApellido);

    ClientesDTO findByDireccion(String direccion);

    ClientesDTO create(CrearClientesRequest crearClientesRequest);

    ClientesDTO update(ActualizarClientesRequest actualizarClientesRequest);

    String delete(Long id);
}
