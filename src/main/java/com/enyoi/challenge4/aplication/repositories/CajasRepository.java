package com.enyoi.challenge4.aplication.repositories;

import com.enyoi.challenge4.aplication.entities.Cajas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CajasRepository extends JpaRepository<Cajas, Long> {

    @Override
    Optional<Cajas> findById(Long id);

    Optional<Cajas> findByNoCajas(String noCajas);




}
