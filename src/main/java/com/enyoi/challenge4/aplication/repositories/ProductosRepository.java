package com.enyoi.challenge4.aplication.repositories;

import com.enyoi.challenge4.aplication.entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {

    @Override
    Optional<Productos> findById(Long id);
    Optional<Productos> findByNombreProductos(String nombreProductos);
    Optional<Productos> findByEstado(Long estado);


}
