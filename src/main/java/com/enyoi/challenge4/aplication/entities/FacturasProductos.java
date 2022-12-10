package com.enyoi.challenge4.aplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "facturas_productos")
public class FacturasProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "valor_unidad", nullable = false, length = 80)
    private Double valorUnidad;

    @NonNull
    @Column(name = "cantidad", nullable = false, length = 50)
    private int cantidad;

    @NonNull
    @Column(name = "total", nullable = false, length = 50)
    private Double total;


    @JoinColumn(name = "facturas_id",referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false)
    private Facturas facturasId;

    @JoinColumn(name = "productos_id",referencedColumnName = "id",nullable = false)
    @ManyToOne(optional = false)
    private Productos productosId;
}
