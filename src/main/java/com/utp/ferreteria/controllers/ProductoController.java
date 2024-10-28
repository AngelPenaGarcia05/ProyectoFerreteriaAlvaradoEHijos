package com.utp.ferreteria.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utp.ferreteria.dto.ProductRequest;
import com.utp.ferreteria.models.Producto;
import com.utp.ferreteria.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "categoria", required = false) String categoria,
            @RequestParam(name = "inicio", required = false, defaultValue = "0") Long inicio,
            @RequestParam(name = "cantidad", required = false, defaultValue = "0") Long cantidad) {
        List<Producto> productos = productoService.obtenerProductosConFiltros(nombre, categoria, inicio, cantidad);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Map<String, Object>> getProductosConFiltros(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "categoria", required = false) String categoria,
            @RequestParam(name = "inicio", required = false, defaultValue = "0") Long inicio,
            @RequestParam(name = "cantidad", required = false, defaultValue = "0") Long cantidad) {
        List<Producto> productos = productoService.obtenerProductosConFiltros(nombre, categoria, inicio, cantidad);
        Map<String, Object> response = new HashMap<>();
        response.put("productos", productos);
        response.put("cantidad", productoService.obtenerCantidadProductos());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<Long> obtenerCantidadProductos(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "categoria", required = false) String categoria,
            @RequestParam(name = "inicio", required = false, defaultValue = "0") Long inicio,
            @RequestParam(name = "cantidad", required = false, defaultValue = "0") Long cantidad) {
        Long cantidadProductosConFiltro = productoService.obtenerCantidadConFiltro(nombre, categoria, inicio, cantidad);
        return ResponseEntity.ok(cantidadProductosConFiltro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable(name = "id") Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> guardarProducto(@RequestBody ProductRequest request) {
        productoService.guardarProducto(request);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Producto guardado con éxito");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarProducto(@PathVariable(name = "id") Long id,
            @RequestBody ProductRequest request) {
        productoService.actualizarProducto(id, request);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Producto actualizado con éxito");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable(name = "id") Long id) {
        productoService.eliminarProducto(id);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Producto eliminado con éxito");
        return ResponseEntity.ok(response);
    }

}
