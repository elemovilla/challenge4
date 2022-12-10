package com.enyoi.challenge4.aplication.services.impl;


import com.enyoi.challenge4.aplication.dtos.FacturasProductosDTO;
import com.enyoi.challenge4.aplication.entities.*;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarFacturasProductosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearFacturasProductosRequest;
import com.enyoi.challenge4.aplication.repositories.FacturasProductosRepository;
import com.enyoi.challenge4.aplication.repositories.FacturasRepository;
import com.enyoi.challenge4.aplication.repositories.ProductosRepository;
import com.enyoi.challenge4.aplication.services.IFacturasProductosService;
import com.enyoi.challenge4.base.exceptions.AlreadyExists;
import com.enyoi.challenge4.base.exceptions.NotDataFound;
import com.enyoi.challenge4.base.exceptions.ResourceNotFoundException;
import com.enyoi.challenge4.base.utils.ResponseDTOService;
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
public class FacturasProductosService implements IFacturasProductosService {

    private final ResponseDTOService responseDTOService;
    private static final String NOEXISTENDATOS = "Datos no existen ";
    private final FacturasProductosRepository facturasProductosRepository;
    private final FacturasRepository facturasRepository;
    private final ProductosRepository productosRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<FacturasProductosDTO> findAll() {
        List<FacturasProductos> facturasProductosList = facturasProductosRepository.findAll();
        List<FacturasProductosDTO> facturasProductosDTOList = new ArrayList<>();

        facturasProductosList.forEach(facturasProductos -> {
            FacturasProductosDTO facturasProductosDTO = modelMapper.map(facturasProductos, FacturasProductosDTO.class);
            facturasProductosDTOList.add(facturasProductosDTO);
        });

        return facturasProductosDTOList;
    }

    @Override
    public FacturasProductosDTO findById(Long id) {
        Optional<FacturasProductos> facturasProductosOptional = facturasProductosRepository.findById(id);
        FacturasProductos facturasProductos;
        if (facturasProductosOptional.isPresent()) {
            facturasProductos = facturasProductosOptional.get();
            return modelMapper.map(facturasProductos, FacturasProductosDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public Page<FacturasProductosDTO> findByFacturasId(Long facturasId, int page, int size, String columnFilter, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, columnFilter));
        Facturas facturas = facturasRepository.findById(facturasId)
                .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
        List<FacturasProductosDTO> list = facturasProductosRepository.findByFacturasId(facturas, pageable)
                .stream()
                .map(facturasProductos -> modelMapper.map(facturasProductos, FacturasProductosDTO.class))
                .collect(Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Page<FacturasProductosDTO> findByProductosId(Long productosId, int page, int size, String columnFilter, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, columnFilter));
        Productos productos = productosRepository.findById(productosId)
                .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
        List<FacturasProductosDTO> list = facturasProductosRepository.findByProductosId(productos, pageable)
                .stream()
                .map(facturasProductos -> modelMapper.map(facturasProductos, FacturasProductosDTO.class))
                .collect(Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }


    @Override
    public List<FacturasProductosDTO> create(List<CrearFacturasProductosRequest> crearFacturasProductosRequest) {
        List<FacturasProductos> facturasProductosList = new ArrayList<>();
        List<FacturasProductosDTO> facturasProductosDTOList = new ArrayList<>();
        crearFacturasProductosRequest.forEach(facturasproductos -> {
            var facturas = facturasRepository.findById(facturasproductos.getFacturasId());
            if (facturas.isPresent()) {

                   FacturasProductos facturasProductosCrear = new FacturasProductos();
                   facturasProductosCrear.setFacturasId(facturas.get());
                   Productos productos = productosRepository.findById(facturasproductos.getProductoId())
                           .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
                   if(productos.getEstado()>0){
                       facturasProductosCrear.setProductosId(productos);
                       facturasProductosCrear.setValorUnidad(facturasproductos.getValorUnidad());
                       facturasProductosCrear.setCantidad(facturasproductos.getCantidad());
                       facturasProductosCrear.setTotal(facturasproductos.getTotal());
                       FacturasProductosDTO facturasProductosDTO = modelMapper.map(facturasProductosRepository.save(facturasProductosCrear), FacturasProductosDTO.class);
                       facturasProductosDTOList.add(facturasProductosDTO);

               }else{

                       throw new NotDataFound( " Sin productos en Stock") ;
                   }

            } else {
                throw new AlreadyExists("Número de factura no existe");
            }});return facturasProductosDTOList;
    }


    @Override
    public FacturasProductosDTO update(ActualizarFacturasProductosRequest actualizarFacturasProductosRequest) {
        Optional<FacturasProductos> facturasProductosOptional = facturasProductosRepository.findById(actualizarFacturasProductosRequest.getFacturasProductosId());
        if (facturasProductosOptional.isPresent()){
            FacturasProductos facturasProductosUpdate = facturasProductosOptional.get();
            Productos productos = productosRepository.findById(actualizarFacturasProductosRequest.getProductoId())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturasProductosUpdate.setProductosId(productos);
            Facturas facturas = facturasRepository.findById(actualizarFacturasProductosRequest.getFacturasId())
                    .orElseThrow(() -> new NotDataFound(NOEXISTENDATOS));
            facturasProductosUpdate.setFacturasId(facturas);
            facturasProductosUpdate.setValorUnidad(actualizarFacturasProductosRequest.getValorUnidad());
            facturasProductosUpdate.setCantidad(actualizarFacturasProductosRequest.getCantidad());
            facturasProductosUpdate.setTotal(actualizarFacturasProductosRequest.getTotal());

            facturasProductosUpdate = facturasProductosRepository.save(facturasProductosUpdate);
            return  modelMapper.map(facturasProductosUpdate, FacturasProductosDTO.class);
        }else {

            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public String delete(Long id) {
        Optional<FacturasProductos> facturasProductosOptional = facturasProductosRepository.findById(id);
        if (!facturasProductosOptional.isPresent()){
            throw new NotDataFound(NOEXISTENDATOS);
        }else {
            facturasProductosRepository.deleteById(id);
        }
        return facturasProductosOptional.get() + "Eliminado";
    }
}
