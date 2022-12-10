package com.enyoi.challenge4.aplication.services.impl;

import com.enyoi.challenge4.aplication.dtos.FacturasDTO;
import com.enyoi.challenge4.aplication.entities.*;
import com.enyoi.challenge4.aplication.payloads.requests.CrearFacturasRequest;
import com.enyoi.challenge4.aplication.repositories.ClientesRepository;
import com.enyoi.challenge4.aplication.repositories.EmpleadosRepository;
import com.enyoi.challenge4.aplication.repositories.FacturasRepository;
import com.enyoi.challenge4.aplication.services.IFacturasService;
import com.enyoi.challenge4.base.exceptions.NotDataFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturasService implements IFacturasService {

    private final ModelMapper modelMapper;

    private static final String NOEXISTENDATOS = "Datos no existen";

    private final FacturasRepository facturasRepository;

    private final EmpleadosRepository empleadosRepository;

    private final ClientesRepository clientesRepository;

    @Override
    public List<FacturasDTO> findAll() {
        List<Facturas> facturasList = facturasRepository.findAll();
        List<FacturasDTO> facturasDTOList = new ArrayList<>();

        facturasList.forEach(productos -> {
            FacturasDTO facturasDTO = modelMapper.map(productos, FacturasDTO.class);
            facturasDTOList.add(facturasDTO);
        });

        return facturasDTOList;
    }


    @Override
    public FacturasDTO findById(Long id) {
        Optional<Facturas> facturasOptional = facturasRepository.findById(id);
        Facturas facturas;
        if (facturasOptional.isPresent()) {
            facturas = facturasOptional.get();
            return modelMapper.map(facturas, FacturasDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }


    @Override
    public Page<FacturasDTO> findByClientesId(Long clientesId, int page, int size, String columnFilter, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, columnFilter));
        Clientes clientes = clientesRepository.findById(clientesId)
                .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
        List<FacturasDTO> list = facturasRepository.findByClientesId(clientes, pageable)
                .stream()
                .map(facturas -> modelMapper.map(facturas, FacturasDTO.class))
                .collect(Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<FacturasDTO> findByEmpleadosId(Long empleadosId, int page, int size, String columnFilter, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, columnFilter));
        Empleados empleados = empleadosRepository.findById(empleadosId)
                .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
        List<FacturasDTO> list = facturasRepository.findByEmpleadosId(empleados, pageable)
                .stream()
                .map(facturas -> modelMapper.map(facturas, FacturasDTO.class))
                .collect(Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public List<FacturasDTO> findByValorUnidad(Double valorUnidad) {
        List<Facturas> facturasList = facturasRepository.findAll();
        List<FacturasDTO> facturasDTOList = new ArrayList<>();
        facturasList.forEach(facturas -> {
            FacturasDTO facturasDTO = modelMapper.map(facturas, FacturasDTO.class);
            facturasDTOList.add(facturasDTO);
        });

        return facturasDTOList;
    }


    @Override
    public List<FacturasDTO> findByTotal(Double total) {
        List<Facturas> facturasList = facturasRepository.findAll();
        List<FacturasDTO> facturasDTOList = new ArrayList<>();
        facturasList.forEach(facturas -> {
            FacturasDTO facturasDTO = modelMapper.map(facturas, FacturasDTO.class);
            facturasDTOList.add(facturasDTO);
        });

        return facturasDTOList;
    }


    @Override
    public FacturasDTO findByNoFacturas(Long noFacturas) {
        Optional<Facturas> facturasOptional = facturasRepository.findById(noFacturas);
        Facturas facturas;
        if (facturasOptional.isPresent()) {
            facturas = facturasOptional.get();
            return modelMapper.map(facturas, FacturasDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }


    @Override
    public FacturasDTO create(CrearFacturasRequest crearFacturasRequest) {

        Optional<Facturas> facturasOptional = facturasRepository.findByNoFacturas(crearFacturasRequest.getNoFacturas());
        if (facturasOptional.isPresent()) {
            throw new NotDataFound("numero de factura ya ha sido asignado");
        } else {
            Facturas facturas = new Facturas();
            facturas.setNoFacturas(crearFacturasRequest.getNoFacturas());
            Clientes clientes = clientesRepository.findById(crearFacturasRequest.getClientesId())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturas.setClientesId(clientes);
            facturas.setValorUnidad(crearFacturasRequest.getValorUnidad());
            facturas.setTotal(crearFacturasRequest.getTotal());
            Empleados empleados = empleadosRepository.findById(crearFacturasRequest.getEmpleadosId())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturas.setEmpleadosId(empleados);

            return modelMapper.map(facturasRepository.save(facturas), FacturasDTO.class);
        }
    }
}








