package com.enyoi.challenge4.aplication.controller;

import com.enyoi.challenge4.aplication.dtos.FacturasProductosDTO;
import com.enyoi.challenge4.aplication.payloads.requests.ActualizarFacturasProductosRequest;
import com.enyoi.challenge4.aplication.payloads.requests.CrearFacturasProductosRequest;
import com.enyoi.challenge4.aplication.services.IFacturasProductosService;
import com.enyoi.challenge4.aplication.services.IProductosService;
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
@RequestMapping("/facturasProductos")
public class FacturasProductosController {

    private final ResponseDTOService responseDTOService;

    private final IFacturasProductosService facturasProductosServiceService;

    private final IProductosService productosService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-todos")
    public ResponseEntity<List<FacturasProductosDTO>> obtenerTodos() {
        return (ResponseEntity<List<FacturasProductosDTO>>) responseDTOService.response(facturasProductosServiceService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-id")
    public ResponseEntity<FacturasProductosDTO> obtenerUno(@RequestParam("id") Long id) {
        return (ResponseEntity<FacturasProductosDTO>) responseDTOService
                .response(facturasProductosServiceService.findById(id), HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("obteber-factura-productos-Id")
    public ResponseEntity<Page<FacturasProductosDTO>> findByProductosId(@RequestParam(value = "productosId")Long productosId,
                                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "10") int size,
                                                              @RequestParam(name = "columnFilter", defaultValue = "id") String columnFilter,
                                                              @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction){
        return (ResponseEntity<Page<FacturasProductosDTO>>) responseDTOService.response(facturasProductosServiceService.findByProductosId(productosId,page,size,columnFilter,direction),HttpStatus.OK );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("obteber-facturas-Id")
    public ResponseEntity<Page<FacturasProductosDTO>> findByFacturasId(@RequestParam(value = "facturasId")Long facturasId,
                                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size,
                                                                        @RequestParam(name = "columnFilter", defaultValue = "id") String columnFilter,
                                                                        @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction){
        return (ResponseEntity<Page<FacturasProductosDTO>>) responseDTOService.response(facturasProductosServiceService.findByFacturasId(facturasId,page,size,columnFilter,direction),HttpStatus.OK );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Producto agotado del Stock",
                    content = @Content)})
    @PostMapping("/crear-facturaproducto")
    public ResponseEntity<FacturasProductosDTO> create(@Valid @RequestBody List<CrearFacturasProductosRequest> crearFacturasProductosRequest,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<FacturasProductosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<FacturasProductosDTO>) responseDTOService.response(facturasProductosServiceService.create(crearFacturasProductosRequest),HttpStatus.CREATED);

        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PutMapping("/actualizar--factura-productos")
    public ResponseEntity<FacturasProductosDTO> update(@Valid @RequestBody ActualizarFacturasProductosRequest actualizarFacturasProductosRequest,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<FacturasProductosDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<FacturasProductosDTO>) responseDTOService.response(facturasProductosServiceService.update(actualizarFacturasProductosRequest),
                    HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasProductosDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @DeleteMapping("/eliminar-un-factura-productos")
    public ResponseEntity<String> eliminarUno(@RequestParam("id") long id) {
        return (ResponseEntity<String>) responseDTOService.response(facturasProductosServiceService.delete(id), HttpStatus.OK);
    }


}
