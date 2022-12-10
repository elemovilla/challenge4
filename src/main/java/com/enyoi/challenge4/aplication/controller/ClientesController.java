package com.enyoi.challenge4.aplication.controller;

import com.enyoi.challenge4.aplication.dtos.ClientesDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarClientesRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearClientesRequest;
import com.enyoi.challenge4.aplication.services.IClientesService;
import com.enyoi.challenge4.base.utils.ResponseDTOService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClientesController {

    private final ResponseDTOService responseDTOService;

    private final IClientesService clientesService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-todos")
    public ResponseEntity<List<ClientesDTO>> obtenerTodos() {
        return (ResponseEntity<List<ClientesDTO>>) responseDTOService
                .response(clientesService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-id")
    public ResponseEntity<ClientesDTO> obtenerUno(@RequestParam("id") Long id) {
        return (ResponseEntity<ClientesDTO>) responseDTOService
                .response(clientesService.findById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-tipo-identificacion")
    public ResponseEntity<List<ClientesDTO>> obtenerUnoPorTipoIdentificacion(@RequestParam("tipoIdentificacion")
                                                                             String tipoIdentificacion) {
        return (ResponseEntity<List<ClientesDTO>>) responseDTOService
                .response(clientesService.findByTipoIdentificacion(tipoIdentificacion), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-numero-identificacion")
    public ResponseEntity<ClientesDTO> obtenerUnoPorNumeroIdentificacion(@RequestParam("numeroIdentificacion")
                                                                         String numeroIdentificacion) {
        return (ResponseEntity<ClientesDTO>) responseDTOService
                .response(clientesService.findByNumeroIdentificacion(numeroIdentificacion), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-primer-nombre")
    public ResponseEntity<List<ClientesDTO>> obtenerPorPrimerNombre(@RequestParam("primerNombre") String primerNombre) {
        return (ResponseEntity<List<ClientesDTO>>) responseDTOService
                .response(clientesService.findByPrimerNombre(primerNombre), HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-segundo-nombre")
    public ResponseEntity<List<ClientesDTO>> obtenerPorSegundoNombre(@RequestParam("segundoNombre") String segundoNombre) {
        return (ResponseEntity<List<ClientesDTO>>) responseDTOService
                .response(clientesService.findBySegundoNombre(segundoNombre), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-primer-apellido")
    public ResponseEntity<List<ClientesDTO>> obtenerPorPrimerApellido(@RequestParam("primerApellido") String primerApellido) {
        return (ResponseEntity<List<ClientesDTO>>) responseDTOService
                .response(clientesService.findByPrimerApellido(primerApellido), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-segundo-apellido")
    public ResponseEntity<List<ClientesDTO>> obtenerPorSegundoApellido(@RequestParam("segundoApellido") String segundoApellido) {
        return (ResponseEntity<List<ClientesDTO>>) responseDTOService
                .response(clientesService.findBySegundoApellido(segundoApellido), HttpStatus.OK);

    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-direccion")
    public ResponseEntity<ClientesDTO> obtenerUnoPorDireccion(@RequestParam("direccion")
                                                              String direccion) {
        return (ResponseEntity<ClientesDTO>) responseDTOService
                .response(clientesService.findByDireccion(direccion), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PostMapping("/crear-cliente")
    public ResponseEntity<ClientesDTO> create(@Valid @RequestBody CrearClientesRequest crearClientesRequest,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(clientesService.create(crearClientesRequest),HttpStatus.CREATED);

        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PutMapping("/actualizar-cliente")
    public ResponseEntity<ClientesDTO> actualizarCliente(@Valid @RequestBody ActualizarClientesRequest actualizarClientesRequest,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ClientesDTO>) responseDTOService.response(clientesService.update(actualizarClientesRequest),
                    HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientesDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @DeleteMapping("/eliminar-uno")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(clientesService.delete(id), HttpStatus.OK);
    }

}
