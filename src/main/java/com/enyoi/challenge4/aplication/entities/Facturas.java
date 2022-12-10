package com.enyoi.challenge4.aplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "facturas")
public class Facturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "clientes_id",referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false)
    private Clientes clientesId;

    @JoinColumn(name = "empleados_id",referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false)
    private Empleados empleadosId;

    @Column(name = "no_facturas", nullable = false, length = 20)
    private Long noFacturas;

    @NonNull
    @Column(name = "valor_unidad", nullable = false, length = 80)
    private Double valorUnidad;

    @NonNull
    @Column(name = "cantidad", nullable = false, length = 50)
    private int cantidad;

    @NonNull
    @Column(name = "total", nullable = false, length = 50)
    private Double total;

}
