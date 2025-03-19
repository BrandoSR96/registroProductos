package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import java.sql.Types;

@Service
public class ProductService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> insertAndListProducts(String nombre, Date date) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_insertAndListProducts")
                .declareParameters(
                    new SqlParameter("p_nombre", Types.VARCHAR),
                    new SqlParameter("p_fec_registro", Types.DATE),
                    new SqlOutParameter("p_codigoRespuesta", Types.INTEGER),
                    new SqlOutParameter("p_mensajeRespuesta", Types.VARCHAR),
                    new SqlOutParameter("p_cursor", Types.REF_CURSOR)
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_nombre", nombre);
        inParams.put("p_fec_registro", new java.sql.Date(date.getTime()));

        Map<String, Object> out = jdbcCall.execute(inParams);

        List<Map<String, Object>> products = (List<Map<String, Object>>) out.get("p_cursor");
        out.put("productos", products);
        
        return out;
    }
}
