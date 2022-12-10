package com.enyoi.challenge4.aplication.controller;

import com.enyoi.challenge4.aplication.dtos.FacturasDTO;
import com.enyoi.challenge4.aplication.payloads.requests.CrearFacturasRequest;
import com.enyoi.challenge4.aplication.services.IFacturasService;
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
@RequestMapping("/facturas")
public class FacturasController {

    private final ResponseDTOService responseDTOService;

    private final IFacturasService facturasService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-todos")
    public ResponseEntity<List<FacturasDTO>> obtenerTodos() {
        return (ResponseEntity<List<FacturasDTO>>) responseDTOService.response(facturasService.findAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-id")
    public ResponseEntity<FacturasDTO> obtenerUno(@RequestParam("id") Long id) {
        return (ResponseEntity<FacturasDTO>) responseDTOService
                .response(facturasService.findById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-no-factura")
    public ResponseEntity<FacturasDTO> findByNoFactura (@RequestParam ("noFacturas")Long noFacturas) {
        return (ResponseEntity<FacturasDTO>) responseDTOService.response(facturasService.findByNoFacturas(noFacturas), HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-No-unidad")
    public ResponseEntity<FacturasDTO> obtenerNoUnidad(@RequestParam("valorUnidad") Double valorUnidad) {
        return (ResponseEntity<FacturasDTO>) responseDTOService
                .response(facturasService.findByValorUnidad(valorUnidad), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("/obtener-total")
    public ResponseEntity<FacturasDTO> obtenerTotal(@RequestParam("total") Double total) {
        return (ResponseEntity<FacturasDTO>) responseDTOService
                .response(facturasService.findByTotal(total), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("obteber-clientes-Id")
    public ResponseEntity<Page<FacturasDTO>> findByClientesId(@RequestParam(value = "clientesId") Long clientesId,
                                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "10") int size,
                                                              @RequestParam(name = "columnFilter", defaultValue = "id") String columnFilter,
                                                              @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction) {
        return (ResponseEntity<Page<FacturasDTO>>) responseDTOService.response(facturasService.findByClientesId(clientesId, page, size, columnFilter, direction), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @GetMapping("obteberfacturasId")
    public ResponseEntity<Page<FacturasDTO>> findByEmpleadosId(@RequestParam(value = "empleadosId") Long empleadosId,
                                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size,
                                                               @RequestParam(name = "columnFilter", defaultValue = "id") String columnFilter,
                                                               @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction) {
        return (ResponseEntity<Page<FacturasDTO>>) responseDTOService.response(facturasService.findByEmpleadosId(empleadosId, page, size, columnFilter, direction), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacturasDTO.class))}),
            @ApiResponse(responseCode = "500", description = "error al solicitar informacion",
                    content = @Content)})
    @PostMapping("/crear-factura")
    public ResponseEntity<FacturasDTO> create(@Valid @RequestBody CrearFacturasRequest crearFacturasRequest,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return (ResponseEntity<FacturasDTO>) responseDTOService.response(HttpStatus.BAD_REQUEST);
        } else {
            return (ResponseEntity<FacturasDTO>) responseDTOService.response(facturasService.create(crearFacturasRequest), HttpStatus.CREATED);

        }
    }

}
