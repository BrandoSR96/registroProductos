package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class ProductServiceTest {
	
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProductService productService;

    private SimpleJdbcCall mockJdbcCall;

    @Test
    void testInsertAndListProducts_Success() {

        Map<String, Object> response = productService.insertAndListProducts("Producto Test", new Date(System.currentTimeMillis()));

        assertNotNull(response);
        assertEquals(0, response.get("p_codigoRespuesta"));
        assertEquals("Ejecución con éxito", response.get("p_mensajeRespuesta"));
    }

    @Test
    void testInsertAndListProducts_Failure() {

        Map<String, Object> response = productService.insertAndListProducts("Producto Test", new Date(System.currentTimeMillis()));

        assertNotNull(response);
        assertEquals(1, response.get("p_codigoRespuesta"));
        assertEquals("Error en la ejecución del procedimiento", response.get("p_mensajeRespuesta"));
    }

    private Map<String, Object> createMockSuccessResponse() {
        Map<String, Object> result = new HashMap();
        result.put("p_codigoRespuesta", 0);
        result.put("p_mensajeRespuesta", "Ejecución con éxito");
        return result;
    }

    private Map<String, Object> createMockErrorResponse() {
        Map<String, Object> result = new HashMap();
        result.put("p_codigoRespuesta", 1);
        result.put("p_mensajeRespuesta", "Error en la ejecución del procedimiento");
        return result;
    }
}
