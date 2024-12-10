package com.utp.ferreteria.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utp.ferreteria.dto.VentaReponse;
import com.utp.ferreteria.dto.VentaRequest;
import com.utp.ferreteria.models.Venta;
import com.utp.ferreteria.services.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaReponse>> getVentas(
        @RequestParam(name = "fechaInicio", required = false) LocalDate fechaInicio,
        @RequestParam(name = "fechaFin", required = false) LocalDate fechaFin,
        @RequestParam(name = "clienteId", required = false) Long clienteId
    ) {
        return ResponseEntity.ok(ventaService.getVentas(fechaInicio, fechaFin, clienteId));
    }

    @PostMapping
    public ResponseEntity<Venta> insertarVenta(@RequestBody VentaRequest ventaRequest) {
        Venta venta = ventaService.insertarVenta(ventaRequest);
        return ResponseEntity.ok(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarEstadoVenta(@PathVariable(name = "id") Long id){
        ventaService.actualizarEstadoVenta(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Estado actualizado con exito");
        return ResponseEntity.ok(response);
    }
}
