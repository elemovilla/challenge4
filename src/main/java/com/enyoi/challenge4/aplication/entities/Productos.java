package com.enyoi.challenge4.aplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre_productos", nullable = false, length = 50)
    private String nombreProductos;

    @NonNull
    @Column(name = "valor_unidad", nullable = false, length = 80)
    private Double valorUnidad;

    @NonNull
    @Column(name = "estado", nullable = false, length = 50)
    private Long estado;

}
