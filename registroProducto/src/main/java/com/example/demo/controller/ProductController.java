package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductRequest;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/productos")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrarProducto(@RequestBody ProductRequest request) {
        Map<String, Object> response = productService.insertAndListProducts(
                request.getNombre(),
                request.getFecRegistro()
        );
        return ResponseEntity.ok(response);
    }
}
