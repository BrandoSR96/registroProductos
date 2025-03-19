package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.ProductRequest;
import com.example.demo.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void testRegistrarProducto_Success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        ProductRequest request = new ProductRequest(null, "Producto Test", new Date(System.currentTimeMillis()));
        Map<String, Object> response = new HashMap();
        response.put("p_codigoRespuesta", 0);
        response.put("p_mensajeRespuesta", "Ejecución con éxito");
    }

    @Test
    void testRegistrarProducto_Failure() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        ProductRequest request = new ProductRequest(null, "Producto Test", new Date(System.currentTimeMillis()));
        Map<String, Object> response = new HashMap<>();
        response.put("p_codigoRespuesta", 1);
        response.put("p_mensajeRespuesta", "Error en la ejecución del procedimiento");

    }
}
