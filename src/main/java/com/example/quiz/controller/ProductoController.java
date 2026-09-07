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

/**
 * RestController = Controller + ResponseBody: devuelve datos, no vistas.
 *
 * Sin CrossOrigin a proposito: la pagina se sirve desde resources/static del
 * mismo Spring Boot, asi que es el mismo origen y no hay CORS que resolver.
 *
 * El controlador solo habla con el servicio, nunca con el repositorio.
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // POST http://localhost:8080/productos
    // El dato viaja en el cuerpo, por eso RequestBody.
    @PostMapping
    public Producto registrar(@RequestBody Producto producto) {
        return productoService.registrar(producto);
    }

    // GET http://localhost:8080/productos/buscar/Bebidas
    // El dato viaja en la URL, por eso PathVariable.
    @GetMapping("/buscar/{categoria}")
    public List<Producto> buscarPorCategoria(@PathVariable("categoria") String categoria) {
        return productoService.buscarPorCategoria(categoria);
    }
}
