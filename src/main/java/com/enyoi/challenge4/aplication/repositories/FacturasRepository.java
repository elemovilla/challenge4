package com.enyoi.challenge4.aplication.repositories;

import com.enyoi.challenge4.aplication.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturasRepository extends JpaRepository<Facturas, Long> {

    @Override
    Optional<Facturas> findById(Long id);
    Optional<Facturas> findByNoFacturas(Long noFacturas);

    List<Facturas> findByValorUnidad(Double valorUnidad);

    List<Facturas> findByTotal(Double total);

    Page<Facturas> findByClientesId(Clientes clienteId, Pageable pageable);

    Page<Facturas> findByEmpleadosId(Empleados empleadosId, Pageable pageable);



}
