package com.enyoi.challenge4.aplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "tipo_identificacion", nullable = false, length = 20)
    private String tipoIdentificacion;

    @NotBlank
    @Column(name = "numero_identificacion", nullable = false, length = 50)
    private String numeroIdentificacion;

    @NotBlank
    @Column(name = "primer_nombre", nullable = false, length = 50)
    private String primerNombre;

    @NotBlank
    @Column(name = "segundo_nombre",nullable = false, length = 50)
    private String segundoNombre;

    @NotBlank
    @Column(name = "primer_apellido", nullable = false, length = 50)
    private String primerApellido;

    @NotBlank
    @Column(name = "segundo_apellido",nullable = false, length = 50)
    private String segundoApellido;

    @NotBlank
    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

}
