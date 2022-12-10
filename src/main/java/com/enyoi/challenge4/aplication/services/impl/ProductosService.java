package com.enyoi.challenge4.aplication.services.impl;

import com.enyoi.challenge4.aplication.dtos.ProductosDTO;
import com.enyoi.challenge4.aplication.entities.Productos;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarProductosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearProductosRequest;
import com.enyoi.challenge4.aplication.repositories.ProductosRepository;
import com.enyoi.challenge4.aplication.services.IProductosService;
import com.enyoi.challenge4.base.exceptions.AlreadyExists;
import com.enyoi.challenge4.base.exceptions.NotDataFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductosService implements IProductosService {

    private final ModelMapper modelMapper;

    private static final String NOEXISTENDATOS = "Datos no existen";

    private final ProductosRepository productosRepository;

    @Override
    public List<ProductosDTO> findAll() {
        List<Productos> productosList = productosRepository.findAll();
        List<ProductosDTO> productosDTOList = new ArrayList<>();

        productosList.forEach(productos -> {
            ProductosDTO productosDTO = modelMapper.map(productos, ProductosDTO.class);
            productosDTOList.add(productosDTO);
        });

        return productosDTOList;
    }


    @Override
    public ProductosDTO findById(Long id) {
        Optional<Productos> productosOptional = productosRepository.findById(id);
        Productos productos;
        if (productosOptional.isPresent()) {
            productos = productosOptional.get();
            return modelMapper.map(productos, ProductosDTO.class);

        } else {
            throw new NotDataFound("producto no existe");
        }
    }

    @Override
    public ProductosDTO findByNombreProductos(String nombreProductos) {
        Optional<Productos> productosOptional = productosRepository.findByNombreProductos(nombreProductos);
        Productos productos;
        if (productosOptional.isPresent()) {
            productos = productosOptional.get();
            return modelMapper.map(productos, ProductosDTO.class);

        } else {
            throw new NotDataFound("Producto no existe");
        }
    }

    @Override
    public ProductosDTO findByEstado(Long estado) {
        Optional<Productos> productosOptional = productosRepository.findByEstado(estado);
        Productos productos;
        if (productosOptional.isPresent()) {
            productos = productosOptional.get();
            return modelMapper.map(productos, ProductosDTO.class);

        } else {
            throw new NotDataFound("Producto no existe");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductosDTO create(CrearProductosRequest crearProductosRequest) {
        Optional<Productos> productosOptional = productosRepository.findByNombreProductos(crearProductosRequest.getNombreProductos());
        if (productosOptional.isPresent()) {
            throw new AlreadyExists("nombre producto ya existe. ");
        } else {
            Productos productos = new Productos();
            productos.setNombreProductos(crearProductosRequest.getNombreProductos());
            productos.setValorUnidad(crearProductosRequest.getValorUnidad());
            productos.setEstado(crearProductosRequest.getEstado());
            return modelMapper.map(productosRepository.save(productos), ProductosDTO.class);
        }
    }

    @Override
    public ProductosDTO update(ActualizarProductosRequest actualizarProductosRequest) {
        Optional<Productos> productosOptional = productosRepository.findById(actualizarProductosRequest.getProductosId());
        if (!productosOptional.isPresent()) {
            throw new NotDataFound(NOEXISTENDATOS);
        } else {
            Productos productosUpdate = productosOptional.get();
            productosUpdate.setNombreProductos(actualizarProductosRequest.getNombreProductos());
            productosUpdate.setValorUnidad(actualizarProductosRequest.getValorUnidad());
            productosUpdate.setEstado(actualizarProductosRequest.getEstado());

            productosUpdate = productosRepository.save(productosUpdate);
            return modelMapper.map(productosUpdate, ProductosDTO.class);
        }
    }

    @Override
    public String delete(Long id) {
        Optional<Productos> productosOptional = Optional.ofNullable(productosRepository.findById(id)
                .orElseThrow(() -> new NotDataFound("No existe el producto: " + id)));
        if (productosOptional.isPresent()) {
            productosRepository.deleteById(id);

            return modelMapper.map(productosOptional.get(), ProductosDTO.class).getId() + "Eliminado con Exito";
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }
}
