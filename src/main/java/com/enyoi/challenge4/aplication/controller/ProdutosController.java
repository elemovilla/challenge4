package com.enyoi.challenge4.aplication.controller;

import com.enyoi.challenge4.aplication.dtos.ProductosDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarProductosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearProductosRequest;
import com.enyoi.challenge4.aplication.services.IProductosService;
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
@RequestMapping("/productos")
public class ProdutosController {

    private final ResponseDTOService responseDTOService;

    private final IProductosService productosService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-todos")
    public ResponseEntity<List<ProductosDTO>> obtenerTodos() {
        return (ResponseEntity<List<ProductosDTO>>) responseDTOService.response(productosService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-id")
    public ResponseEntity<ProductosDTO> obtenerUno(@RequestParam("id") Long id) {
        return (ResponseEntity<ProductosDTO>) responseDTOService
                .response(productosService.findById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-nombre-producto")
    public ResponseEntity<ProductosDTO> obtenerNombreProducto(@RequestParam("nombreProductos") String nombreProductos) {
        return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.findByNombreProductos(nombreProductos), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-estados")
    public ResponseEntity<ProductosDTO> obtenerEstado(@RequestParam("estado") Long estado) {
        return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.findByEstado(estado), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PostMapping("/crear-Productos")
    public ResponseEntity<ProductosDTO> create(@Valid @RequestBody CrearProductosRequest crearProductosRequest,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.create(crearProductosRequest),HttpStatus.CREATED);

        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PutMapping("/actualizar-Productos")
    public ResponseEntity<ProductosDTO> update(@Valid @RequestBody ActualizarProductosRequest actualizarProductosRequest,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<ProductosDTO>) responseDTOService.response(productosService.update(actualizarProductosRequest),
                    HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @DeleteMapping("/eliminar-un-producto")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(productosService.delete(id), HttpStatus.OK);
    }

}
