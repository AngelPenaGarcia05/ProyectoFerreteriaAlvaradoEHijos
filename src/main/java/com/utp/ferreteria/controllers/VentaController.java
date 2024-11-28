package com.utp.ferreteria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.ferreteria.dto.VentaRequest;
import com.utp.ferreteria.models.Venta;
import com.utp.ferreteria.services.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> insertarVenta(@RequestBody VentaRequest ventaRequest) {
        Venta venta = ventaService.insertarVenta(ventaRequest);
        return ResponseEntity.ok(venta);
    }
}
