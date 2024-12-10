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
public class VentaReponse {
    Long id;
    String tipoPago;
    String estado;
    String fecha;
    ClienteResponse cliente;
    String nombres;
    String direccion;
    String codigoPostal;
    String telefono;
    String instrucciones;
    DetalleVentaResponse[] detallesVenta;
    BigDecimal total;
}
