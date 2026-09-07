package com.example.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.entities.Producto;
import com.example.quiz.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping
    public Producto registrar(@RequestBody Producto producto) {
        return productoService.registrar(producto);
    }

    @GetMapping("/buscar/{categoria}")
    public List<Producto> buscarPorCategoria(@PathVariable("categoria") String categoria) {
        return productoService.buscarPorCategoria(categoria);
    }
}
