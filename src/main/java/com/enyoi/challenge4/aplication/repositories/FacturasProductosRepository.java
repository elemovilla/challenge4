package com.enyoi.challenge4.aplication.repositories;

import com.enyoi.challenge4.aplication.entities.Facturas;
import com.enyoi.challenge4.aplication.entities.FacturasProductos;
import com.enyoi.challenge4.aplication.entities.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturasProductosRepository extends JpaRepository<FacturasProductos, Long> {

    @Override
    Optional<FacturasProductos> findById(Long id);


    Page<FacturasProductos> findByFacturasId(Facturas facturasId, Pageable pageable);

    Page<FacturasProductos> findByProductosId(Productos productosId, Pageable pageable);



}
