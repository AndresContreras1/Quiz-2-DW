package com.example.quiz.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.quiz.entities.Producto;
import com.example.quiz.repository.ProductoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        productoRepository.save(new Producto("Cafe molido", "Bebidas", new BigDecimal("18500.00"), true));
        productoRepository.save(new Producto("Jugo de naranja", "Bebidas", new BigDecimal("6200.00"), true));
        productoRepository.save(new Producto("Gaseosa retirada", "Bebidas", new BigDecimal("4000.00"), false));
        productoRepository.save(new Producto("Arroz", "Granos", new BigDecimal("3900.00"), true));
        productoRepository.save(new Producto("Lentejas", "Granos", new BigDecimal("5100.00"), true));
        productoRepository.save(new Producto("Jabon de manos", "Aseo", new BigDecimal("7800.00"), true));
    }
}
