package com.example.quiz.service;

import java.util.List;

import com.example.quiz.entities.Producto;

public interface ProductoService {

    Producto registrar(Producto producto);

    List<Producto> buscarPorCategoria(String categoria);
}
