package com.enyoi.challenge4.aplication.services.impl;

import com.enyoi.challenge4.aplication.dtos.ClientesDTO;
import com.enyoi.challenge4.aplication.dtos.ProductosDTO;
import com.enyoi.challenge4.aplication.entities.Clientes;
import com.enyoi.challenge4.aplication.entities.Productos;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarClientesRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearClientesRequest;
import com.enyoi.challenge4.aplication.repositories.ClientesRepository;
import com.enyoi.challenge4.aplication.services.IClientesService;
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
public class ClientesService implements IClientesService {

    private final ModelMapper modelMapper;

    private static final String NOEXISTENDATOS = "Datos no existen";

    //Repositorios
    private final ClientesRepository clientesRepository;

    @Override
    public List<ClientesDTO> findAll() {
        List<Clientes> clientesList = clientesRepository.findAll();
        List<ClientesDTO> clientesDTOList = new ArrayList<>();
        clientesList.forEach(clientes -> {
            ClientesDTO clientesDTO = modelMapper.map(clientes, ClientesDTO.class);
            clientesDTOList.add(clientesDTO);
        });
        return clientesDTOList;
    }

    @Override
    public ClientesDTO findById(Long id) {
        Optional<Clientes> clientesOptional = clientesRepository.findById(id);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ClientesDTO findByTipoIdentificacion(String tipoIdentificacion) {
        Optional<Clientes> clientesOptional = clientesRepository.findByTipoIdentificacion(tipoIdentificacion);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return  modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ClientesDTO findByNumeroIdentificacion(String numeroIdentificacion) {
        Optional<Clientes> clientesOptional = clientesRepository.findByNumeroIdentificacion(numeroIdentificacion);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return  modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    public ClientesDTO findByPrimerNombre(String primerNombre) {
        Optional<Clientes> clientesOptional = clientesRepository.findByPrimerNombre(primerNombre);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound("Producto no existe");
        }
    }

    @Override
    public ClientesDTO findBySegundoNombre(String segundoNombre) {
        Optional<Clientes> clientesOptional = clientesRepository.findBySegundoNombre(segundoNombre);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound("Producto no existe");
        }
    }

    @Override
    public ClientesDTO findByPrimerApellido(String primerApellido) {
        Optional<Clientes> clientesOptional = clientesRepository.findByPrimerApellido(primerApellido);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound("Producto no existe");
        }
    }

    @Override
    public ClientesDTO findBySegundoApellido(String segundoApellido) {
        Optional<Clientes> clientesOptional = clientesRepository.findBySegundoApellido(segundoApellido);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);

        } else {
            throw new NotDataFound("Producto no existe");
        }
    }

    @Override
    public ClientesDTO findByDireccion(String direccion) {
        Optional<Clientes> clientesOptional = clientesRepository.findByDireccion(direccion);
        Clientes clientes;
        if (clientesOptional.isPresent()) {
            clientes = clientesOptional.get();
            return modelMapper.map(clientes, ClientesDTO.class);
        } else {
            throw new NotDataFound(NOEXISTENDATOS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClientesDTO create(CrearClientesRequest crearClientesRequest) {
        Optional<Clientes> clientesOptional = clientesRepository.findByNumeroIdentificacion
                (crearClientesRequest.getNumeroIdentificacion());
        if (clientesOptional.isPresent()) {
            throw new AlreadyExists("Número de identificación ya existe");
        }else {
            Clientes clientes = new Clientes();
            clientes.setTipoIdentificacion(crearClientesRequest.getTipoIdentificacion());
            clientes.setNumeroIdentificacion(crearClientesRequest.getNumeroIdentificacion());
            clientes.setPrimerNombre(crearClientesRequest.getPrimerNombre());
            clientes.setSegundoNombre(crearClientesRequest.getSegundoNombre());
            clientes.setPrimerApellido(crearClientesRequest.getPrimerApellido());
            clientes.setSegundoApellido(crearClientesRequest.getSegundoApellido());
            clientes.setDireccion(crearClientesRequest.getDireccion());
            return modelMapper.map(clientesRepository.save(clientes), ClientesDTO.class);

        }
    }

    @Override
    public ClientesDTO update(ActualizarClientesRequest actualizarClientesRequest) {
        Optional<Clientes> clientesOptional = clientesRepository.findById(actualizarClientesRequest.getClienteId());
        if (!clientesOptional.isPresent()){
            throw new NotDataFound(NOEXISTENDATOS);
        }else {
            Clientes clientesUpdate = clientesOptional.get();
            clientesUpdate.setTipoIdentificacion(actualizarClientesRequest.getTipoIdentificacion());
            clientesUpdate.setNumeroIdentificacion(actualizarClientesRequest.getNumeroIdentificacion());
            clientesUpdate.setPrimerNombre(actualizarClientesRequest.getPrimerNombre());
            clientesUpdate.setSegundoNombre(actualizarClientesRequest.getSegundoNombre());
            clientesUpdate.setPrimerApellido(actualizarClientesRequest.getPrimerApellido());
            clientesUpdate.setSegundoApellido(actualizarClientesRequest.getSegundoApellido());
            clientesUpdate.setDireccion(actualizarClientesRequest.getDireccion());

            clientesUpdate = clientesRepository.save(clientesUpdate);
            return modelMapper.map(clientesUpdate, ClientesDTO.class);

        }
    }

    @Override
    public String delete(Long id) {
        Optional<Clientes> clientesOptional = clientesRepository.findById(id);
        if (!clientesOptional.isPresent()){
            throw new NotDataFound(NOEXISTENDATOS);
        }else {
            clientesRepository.deleteById(id);
        }
        return clientesOptional.get() + "Eliminado";
    }


}
