package com.example.quiz.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Una entidad = una tabla, una instancia = un registro, un atributo = una columna.
 * Sin @Data: arrastra un toString con todos los campos y aqui no hace falta.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String categoria;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Boolean activo;

    // Segundo constructor obligatorio: con todos los datos MENOS el id,
    // porque el id lo genera la base de datos.
    public Producto(String nombre, String categoria, BigDecimal precio, Boolean activo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.activo = activo;
    }
}
