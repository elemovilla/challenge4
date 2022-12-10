package com.enyoi.challenge4.aplication.controller;

import com.enyoi.challenge4.aplication.dtos.CajasDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarCajasRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearCajasRequest;
import com.enyoi.challenge4.aplication.services.ICajasService;
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
@RequestMapping("/cajas")
public class CajasController {

    private final ResponseDTOService responseDTOService;

    private final ICajasService cajasService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-todos")
    public ResponseEntity<List<CajasDTO>> obtenerTodos() {
        return (ResponseEntity<List<CajasDTO>>) responseDTOService
                .response(cajasService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-id")
    public ResponseEntity<CajasDTO> obtenerUno(@RequestParam("id") Long id) {
        return (ResponseEntity<CajasDTO>) responseDTOService
                .response(cajasService.findById(id), HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-por-no-caja")
    public ResponseEntity<List<CajasDTO>> obtenerNoCajas(@RequestParam("noCajas")
                                                                             String noCajas) {
        return (ResponseEntity<List<CajasDTO>>) responseDTOService
                .response(cajasService.findByNoCajas(noCajas), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PostMapping("/crear-caja")
    public ResponseEntity<CajasDTO> create(@Valid @RequestBody CrearCajasRequest crearCajasRequest,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<CajasDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<CajasDTO>) responseDTOService.response(cajasService.create(crearCajasRequest),HttpStatus.CREATED);

        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PutMapping("/actualizar-caja")
    public ResponseEntity<CajasDTO> actualizarCliente(@Valid @RequestBody ActualizarCajasRequest actualizarCajasRequest,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<CajasDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<CajasDTO>) responseDTOService.response(cajasService.update(actualizarCajasRequest),
                    HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @DeleteMapping("/eliminar-una-Caja")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(cajasService.delete(id), HttpStatus.OK);
    }
}
