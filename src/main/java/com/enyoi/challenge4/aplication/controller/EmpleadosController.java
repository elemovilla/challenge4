package com.enyoi.challenge4.aplication.controller;

import com.enyoi.challenge4.aplication.dtos.EmpleadosDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarEmpleadosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearEmpeladosRequest;
import com.enyoi.challenge4.aplication.services.IEmpleadosService;
import com.enyoi.challenge4.base.utils.ResponseDTOService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/empleados")
public class EmpleadosController{

    private final ResponseDTOService responseDTOService;

    private final IEmpleadosService empleadosService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-todos")
    public ResponseEntity<List<EmpleadosDTO>> obtenerTodos() {
        return (ResponseEntity<List<EmpleadosDTO>>) responseDTOService
                .response(empleadosService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-id")
    public ResponseEntity<EmpleadosDTO> obtenerUno(@RequestParam("id") Long id) {
        return (ResponseEntity<EmpleadosDTO>) responseDTOService
                .response(empleadosService.findById(id), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-nombre-empleado")
    public ResponseEntity<EmpleadosDTO> obtenerNombreEstado(@RequestParam("nombreEmpleado") String nombreEmpleado) {
        return (ResponseEntity<EmpleadosDTO>) responseDTOService.response(empleadosService.findByNombreEmpleados(nombreEmpleado), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("obtebercajasId")
    public ResponseEntity<Page<EmpleadosDTO>> findByCajasId(@RequestParam(value = "cajasId")Long cajasId,
                                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                                            @RequestParam(name = "size", defaultValue = "10") int size,
                                                            @RequestParam(name = "columnFilter", defaultValue = "id") String columnFilter,
                                                            @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction){
        return (ResponseEntity<Page<EmpleadosDTO>>) responseDTOService.response(empleadosService.findByCajasId(cajasId,page,size,columnFilter,direction),HttpStatus.OK );
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PostMapping("/crear-empleado")
    public ResponseEntity<EmpleadosDTO> create(@Valid @RequestBody CrearEmpeladosRequest crearEmpleadosRequest,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<EmpleadosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<EmpleadosDTO>) responseDTOService.response(empleadosService.create(crearEmpleadosRequest),HttpStatus.CREATED);

        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PutMapping("/actualizar-empleado")
    public ResponseEntity<EmpleadosDTO> update(@Valid @RequestBody ActualizarEmpleadosRequest actualizarEmpleadosRequest,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<EmpleadosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<EmpleadosDTO>) responseDTOService.response(empleadosService.update(actualizarEmpleadosRequest),
                    HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @DeleteMapping("/eliminar-uno")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(empleadosService.delete(id), HttpStatus.OK);
    }



}
