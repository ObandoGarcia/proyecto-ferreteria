package com.obando.proyecto_ferreteria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codProducto;

    private String nombre;
    private String marca;
    private String categoria;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal precio;

    private Integer cantidad;
    private String descripcion;
}
