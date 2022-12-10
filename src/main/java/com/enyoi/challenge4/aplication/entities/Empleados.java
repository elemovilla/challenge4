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
@Table(name = "empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "numero_identificacion", nullable = false, length = 50)
    private String numeroIdentificacion;

    @NotBlank
    @Column(name = "nombre_empleado", nullable = false, length = 50)
    private String nombreEmpleado;

    @JoinColumn(name = "cajas_id",referencedColumnName ="id",nullable = false)
    @ManyToOne(optional = false)
    private Cajas cajasId;

}
