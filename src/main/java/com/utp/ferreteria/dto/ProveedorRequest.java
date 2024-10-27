package com.utp.ferreteria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorRequest {
    String nombre;
    String direccion;
    String telefono;
    String email;
    String ruc;
}
