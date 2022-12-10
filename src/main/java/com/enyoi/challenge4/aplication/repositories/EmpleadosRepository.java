package com.enyoi.challenge4.aplication.repositories;

import com.enyoi.challenge4.aplication.entities.Cajas;
import com.enyoi.challenge4.aplication.entities.Clientes;
import com.enyoi.challenge4.aplication.entities.Empleados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {

    @Override
    Optional<Empleados> findById(Long id);

    Optional<Empleados> findByNumeroIdentificacion(String numeroIdentificacion);

    Optional<Empleados> findByNombreEmpleado(String nombreEmpleado);

    Page<Empleados> findByCajasId(Cajas cajasId, Pageable pageable);
}
