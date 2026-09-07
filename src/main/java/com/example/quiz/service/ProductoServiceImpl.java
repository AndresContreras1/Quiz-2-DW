package com.example.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.entities.Producto;
import com.example.quiz.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto registrar(Producto producto) {
        // JPA guarda por clave primaria: si el cuerpo del POST trae un id, no
        // crea un producto nuevo, sobrescribe el que ya tuviera ese id.
        producto.setId(null);

        if (producto.getActivo() == null) {
            producto.setActivo(true);
        }

        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.buscarPorCategoria(categoria);
    }
}
