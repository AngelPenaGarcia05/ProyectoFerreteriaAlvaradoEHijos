package com.utp.ferreteria.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    String nombre;
    BigDecimal precio;
    int cantidad;
    String descripcion;
    String imagenURL;
    String categoria;
    Long proveedorID;
}
