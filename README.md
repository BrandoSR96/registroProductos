# API de Productos

## Objetivo
Desarrollar una API en microservicio utilizando Java y Spring Boot que implemente un único endpoint REST POST. Este servicio registrará un producto y, en la misma operación, devolverá un listado de todos los productos registrados en la fecha actual (YYYYMMDD).
La API consumirá un procedimiento almacenado (SP) en Oracle SQL, responsable de la inserción y consulta de los productos.

## Tecnologías Utilizadas
- **Lenguaje:** Java
- **Framework:** Spring Boot
- **Base de Datos:** Oracle SQL
- **Pruebas:** JUnit y Mockito

## Arquitectura
El proyecto sigue la arquitectura de microservicios con las siguientes capas:

- **Modelo:** Define la estructura del request.
- **Servicio:** Implementa la lógica de negocio y la comunicación con la base de datos.
- **Controlador:** Expone el endpoint REST.

## Estructura del Proyecto
```
/src/main/java/com/example/demo/
 ├── controller/       # Controladores REST
 ├── model/            # Clases de modelo y DTOs
 ├── service/          # Lógica de negocio y acceso a datos
```

## Endpoint
### Registrar Producto y Listar Productos del Día
**POST** `/api/productos/registrar`

#### Request Body
```json
{
    "idProducto": 1,
    "nombre": "Producto A",
    "fecRegistro": "2025-03-19T00:00:00Z"
}
```

#### Response
```json
{
    "p_codigoRespuesta": 0,
    "p_mensajeRespuesta": "Ejecución con éxito",
    "productos": [
        {
            "ID_PRODUCTO": 1,
            "NOMBRE": "Producto A",
            "FEC_REGISTRO": "20250319"
        }
    ]
}
```

## Procedimiento Almacenado (Oracle SQL)
```sql
CREATE OR REPLACE PROCEDURE SP_INSERTANDLISTPRODUCTS (
    P_NOMBRE IN VARCHAR2,
    P_FEC_REGISTRO IN DATE,
    P_CODIGORESPUESTA OUT NUMBER,
    P_MENSAJERESPUESTA OUT VARCHAR2,
    CURSOR_RESULT OUT SYS_REFCURSOR
)
AS
BEGIN
    BEGIN
        INSERT INTO PRODUCTOS (NOMBRE, FEC_REGISTRO)
        VALUES (P_NOMBRE, P_FEC_REGISTRO);
        P_CODIGORESPUESTA := 0;
        P_MENSAJERESPUESTA := 'Ejecución con éxito';
        OPEN CURSOR_RESULT FOR
        SELECT ID_PRODUCTO, NOMBRE, FEC_REGISTRO FROM PRODUCTOS
        WHERE TRUNC(FEC_REGISTRO) = TRUNC(SYSDATE);
    EXCEPTION
        WHEN OTHERS THEN
            P_CODIGORESPUESTA := 1;
            P_MENSAJERESPUESTA := 'Error en la ejecución del procedimiento: ' || SQLERRM;
    END;
END SP_INSERTANDLISTPRODUCTS;
```

