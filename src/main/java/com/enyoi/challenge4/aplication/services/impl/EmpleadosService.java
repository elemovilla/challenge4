package com.enyoi.challenge4.aplication.services.impl;


import com.enyoi.challenge4.aplication.dtos.EmpleadosDTO;
import com.enyoi.challenge4.aplication.entities.Cajas;
import com.enyoi.challenge4.aplication.entities.Empleados;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarEmpleadosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearEmpeladosRequest;
import com.enyoi.challenge4.aplication.repositories.CajasRepository;
import com.enyoi.challenge4.aplication.repositories.EmpleadosRepository;
import com.enyoi.challenge4.aplication.services.IEmpleadosService;
import com.enyoi.challenge4.base.exceptions.AlreadyExists;
import com.enyoi.challenge4.base.exceptions.NotDataFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadosService implements IEmpleadosService {

    private final ModelMapper modelMapper;

    private static final String NOEXISTENDATOS = "Datos no existen";

    private final EmpleadosRepository empleadosRepository;

    private final CajasRepository cajasRepository;


    @Override
    public List<EmpleadosDTO> findAll() {
        List<Empleados> empleadosList = empleadosRepository.findAll();
        List<EmpleadosDTO> empleadosDTOList = new ArrayList<>();

        empleadosList.forEach(empleados -> {
            EmpleadosDTO empleadosDTO = modelMapper.map(empleados, EmpleadosDTO.class);
            empleadosDTOList.add(empleadosDTO);
        });

        return empleadosDTOList;
    }

    @Override
    public EmpleadosDTO findById(Long id) {
        Optional<Empleados> empleadosOptional = empleadosRepository.findById(id);
        Empleados empleados;
        if (empleadosOptional.isPresent()) {
            empleados = empleadosOptional.get();
            return  modelMapper.map(empleados, EmpleadosDTO.class);

        } else {
            throw new NotDataFound("empleado no existe");
        }
    }

    @Override
    public EmpleadosDTO findByNumeroIdentificacion(String numeroIdentificacion) {
        Optional<Empleados> empleadosOptional = empleadosRepository.findByNumeroIdentificacion(numeroIdentificacion);
        Empleados empleados;
        if (empleadosOptional.isPresent()) {
            empleados = empleadosOptional.get();
            return modelMapper.map(empleados, EmpleadosDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public EmpleadosDTO findByNombreEmpleados(String nombreEmpleado) {
        Optional<Empleados> empleadosOptional = empleadosRepository.findByNombreEmpleado(nombreEmpleado);
        Empleados empleados;
        if (empleadosOptional.isPresent()) {
            empleados = empleadosOptional.get();
            return modelMapper.map(empleados, EmpleadosDTO.class);

        } else {
            throw new NotDataFound("cargo no existe");
        }
    }

    @Override
    public Page<EmpleadosDTO> findByCajasId(Long cajasId, int page, int size, String columnFilter, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, columnFilter));
        Cajas cajas = cajasRepository.findById(cajasId)
                .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
        List<EmpleadosDTO> list = empleadosRepository.findByCajasId(cajas, pageable)
                .stream()
                .map(empleados -> modelMapper.map(empleados, EmpleadosDTO.class))
                .collect(Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EmpleadosDTO create(CrearEmpeladosRequest crearEmpeladosRequest) {
        Optional<Empleados> empleadosOptional = empleadosRepository.findByNumeroIdentificacion(crearEmpeladosRequest.getNumeroIdentificacion());
        if (empleadosOptional.isPresent()) {
            throw new AlreadyExists("nombre empleado ya existe. ");
        } else {
            Empleados empleados = new Empleados();
            empleados.setNumeroIdentificacion(crearEmpeladosRequest.getNumeroIdentificacion());
            empleados.setNombreEmpleado(crearEmpeladosRequest.getNombreEmpleado());
            Cajas cajas = cajasRepository.findById(crearEmpeladosRequest.getCajasId())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            empleados.setCajasId(cajas);
            return modelMapper.map(empleadosRepository.save(empleados), EmpleadosDTO.class);
        }
    }

    @Override
    public EmpleadosDTO update(ActualizarEmpleadosRequest actualizarEmpleadosRequest) {
        Optional<Empleados> empleadosOptional = empleadosRepository.findById(actualizarEmpleadosRequest.getEmpleadosId());
        if (!empleadosOptional.isPresent()) {
            throw new NotDataFound(NOEXISTENDATOS);
        } else {
            Empleados empleadosUpdate = empleadosOptional.get();
            empleadosUpdate.setNumeroIdentificacion(actualizarEmpleadosRequest.getNumeroIdentificacion());
            empleadosUpdate.setNombreEmpleado(actualizarEmpleadosRequest.getNombreEmpleado());
            Cajas cajas = cajasRepository.findByNoCajas(actualizarEmpleadosRequest.getNoCaja())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            empleadosUpdate.setCajasId(cajas);
            empleadosUpdate = empleadosRepository.save(empleadosUpdate);

            empleadosUpdate = empleadosRepository.save(empleadosUpdate);
            return modelMapper.map(empleadosUpdate, EmpleadosDTO.class);
        }
    }

    @Override
    public String delete(Long id) {
        Optional<Empleados> empleadosOptional = Optional.ofNullable(empleadosRepository.findById(id)
                .orElseThrow(() -> new NotDataFound("No existe el empleado: " + id)));
        if (empleadosOptional.isPresent()) {
            empleadosRepository.deleteById(id);

            return modelMapper.map(empleadosOptional.get(), EmpleadosDTO.class).getId() + "Eliminado con Exito";
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }

    }
}

