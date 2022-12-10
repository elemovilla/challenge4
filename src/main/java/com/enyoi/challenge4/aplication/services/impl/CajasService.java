package com.enyoi.challenge4.aplication.services.impl;

import com.enyoi.challenge4.aplication.dtos.CajasDTO;
import com.enyoi.challenge4.aplication.entities.Cajas;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarCajasRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearCajasRequest;
import com.enyoi.challenge4.aplication.repositories.CajasRepository;
import com.enyoi.challenge4.aplication.repositories.EmpleadosRepository;
import com.enyoi.challenge4.aplication.services.ICajasService;
import com.enyoi.challenge4.base.exceptions.NotDataFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajasService implements ICajasService {

    private final ModelMapper modelMapper;

    private static final String NOEXISTENDATOS = "Datos no existen";

    private final CajasRepository cajasRepository;

    private final EmpleadosRepository empleadosRepository;

    @Override
    public List<CajasDTO> findAll() {
        List<Cajas> cajasList = cajasRepository.findAll();
        List<CajasDTO> cajasDTOList = new ArrayList<>();
        cajasList.forEach(cajas -> {
            CajasDTO cajasDTO = modelMapper.map(cajas, CajasDTO.class);
            cajasDTOList.add(cajasDTO);
        });
        return cajasDTOList;
    }

    @Override
    public CajasDTO findById(Long id) {
        Optional<Cajas> cajasOptional = cajasRepository.findById(id);
        Cajas cajas;
        if (cajasOptional.isPresent()) {
            cajas = cajasOptional.get();
            return modelMapper.map(cajas, CajasDTO.class);

        } else {
            throw new NotDataFound("el Empleado no existe");
        }
    }

    @Override
    public CajasDTO findByNoCajas(String noCajas) {
        Optional<Cajas> cajasOptional = cajasRepository.findByNoCajas(noCajas);
        Cajas cajas;
        if (cajasOptional.isPresent()) {
            cajas = cajasOptional.get();
            return modelMapper.map(cajas, CajasDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public CajasDTO create(CrearCajasRequest crearCajasRequest) {
        Optional<Cajas> cajasOptional = cajasRepository.findByNoCajas(crearCajasRequest.getNoCajas());
        if (cajasOptional.isPresent()){
            throw new NotDataFound("no de caja no existe");
        }else{
            Cajas cajas = new Cajas();
            cajas.setNoCajas(crearCajasRequest.getNoCajas());
            return modelMapper.map(cajasRepository.save(cajas), CajasDTO.class);
        }
    }

    @Override
    public CajasDTO update(ActualizarCajasRequest actualizarCajasRequest) {
        Optional<Cajas> cajasOptional = cajasRepository.findById(actualizarCajasRequest.getCajasId());
        if (cajasOptional.isPresent()) {
            Cajas updateCajas = cajasOptional.get();
            updateCajas.setNoCajas(actualizarCajasRequest.getNoCajas() != null
                    ? actualizarCajasRequest.getNoCajas()
                    : updateCajas.getNoCajas());
            return modelMapper.map(cajasRepository.save(updateCajas), CajasDTO.class);
        } else {
            throw new NotDataFound("Cargo no existe");
        }
    }
    @Override
    public String delete(Long id) {
        Optional<Cajas> cajasOptional = Optional.ofNullable(cajasRepository.findById(id)
                .orElseThrow(() -> new NotDataFound("No existe la caja: " + id)));
        if (cajasOptional.isPresent()) {
            cajasRepository.deleteById(id);
            return modelMapper.map(cajasOptional.get(), CajasDTO.class).getId() + "caja Eliminado con Exito";

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }
}
