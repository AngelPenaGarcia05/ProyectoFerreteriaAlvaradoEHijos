package com.utp.ferreteria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    Long id;
    String nombres;
    String apellidos;
    String dni;
    String direccion;
    String telefono;
    String email;
    String fechaRegistro;
    String rol;
}
