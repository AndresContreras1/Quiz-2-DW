package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.quiz.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("""
                SELECT p
                FROM Producto p
                WHERE p.categoria = :categoria
                  AND p.activo = true
            """)
    List<Producto> buscarPorCategoria(@Param("categoria") String categoria);
}
