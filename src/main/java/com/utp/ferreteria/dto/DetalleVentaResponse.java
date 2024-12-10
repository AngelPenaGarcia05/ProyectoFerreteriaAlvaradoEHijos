package com.utp.ferreteria.dto;

import java.math.BigDecimal;

import com.utp.ferreteria.models.Producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaResponse {
    Long id;
    int cantidad;
    BigDecimal subtotal;
    Producto producto;

}
