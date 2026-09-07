package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.quiz.entities.Producto;

/**
 * Interfaz, no clase. Sin @Repository: Spring Data registra sola cualquier
 * interfaz que extienda JpaRepository.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Consulta JPQL. Se escriben la clase (Producto) y sus atributos Java
     * (p.categoria, p.activo), nunca la tabla ni las columnas: eso es lo que
     * la hace portable a cualquier motor y lo que la separa del SQL nativo.
     *
     * El @Param es el que vincula el :categoria de la consulta con el argumento.
     */
    @Query("""
                SELECT p
                FROM Producto p
                WHERE p.categoria = :categoria
                  AND p.activo = true
            """)
    List<Producto> buscarPorCategoria(@Param("categoria") String categoria);
}
