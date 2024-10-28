package com.utp.ferreteria.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.utp.ferreteria.dto.ProveedorRequest;
import com.utp.ferreteria.models.Proveedor;
import com.utp.ferreteria.services.ProveedorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getProveedores() {
        return proveedorService.obtenerProveedores();
    }

    @GetMapping("/{id}")
    public Proveedor getProveedor(@PathVariable(name = "id") Long id) {
        return proveedorService.obtenerProveedorPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> guardarProveedor(@RequestBody ProveedorRequest request) {
        proveedorService.guardarProveedor(request);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Proveedor guardado con exito");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarProveedor(@PathVariable(name = "id") Long id,
            @RequestBody ProveedorRequest request) {
        proveedorService.actualizarProveedor(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Proveedor actualizado con exito");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarProveedor(@PathVariable(name = "id") Long id) {
        proveedorService.eliminarProveedor(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Proveedor eliminado con exito");
        return ResponseEntity.ok(response);
    }

}
