package com.utp.ferreteria.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.utp.ferreteria.dto.ProveedorRequest;
import com.utp.ferreteria.models.Proveedor;
import com.utp.ferreteria.services.ProveedorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String guardarProveedor(@RequestBody ProveedorRequest request) {
        proveedorService.guardarProveedor(request);
        return "Proveedor guardado con exito";
    }

    @PutMapping("/{id}")
    public String actualizarProveedor(@PathVariable(name = "id") Long id, @RequestBody ProveedorRequest request) {
        proveedorService.actualizarProveedor(id, request);
        return "Proveedor actualizado con exito";
    }


}
