package com.utp.ferreteria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequest {
    private String tipoPago;
    private String estado;
    private Date fecha;
    private Long clienteID;
    private List<DetalleVentaDTO> detallesVenta; // Asegúrate de tener la clase DetalleVentaDTO
}
