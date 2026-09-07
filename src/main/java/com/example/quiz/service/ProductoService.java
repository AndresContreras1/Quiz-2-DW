package com.example.quiz.service;

import java.util.List;

import com.example.quiz.entities.Producto;

// Para el servicio, primero la interfaz y despues la implementacion.
public interface ProductoService {

    Producto registrar(Producto producto);

    List<Producto> buscarPorCategoria(String categoria);
}
