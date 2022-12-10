package com.enyoi.challenge4.aplication.repositories;

import com.enyoi.challenge4.aplication.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    @Override
    Optional<Clientes> findById(Long id);

    Optional<Clientes> findByTipoIdentificacion(String tipoIdentificacion);

    Optional<Clientes> findByNumeroIdentificacion(String numeroIdentificacion);

    Optional<Clientes> findByPrimerNombre(String primerNombre);

    Optional<Clientes> findBySegundoNombre(String segundoNombre);

    Optional<Clientes> findByPrimerApellido(String primerApellido);

    Optional<Clientes> findBySegundoApellido(String segundoApellido);

    Optional<Clientes> findByDireccion(String direccion);


}
