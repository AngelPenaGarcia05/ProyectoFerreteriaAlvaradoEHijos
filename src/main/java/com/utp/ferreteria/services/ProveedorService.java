package com.utp.ferreteria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.ferreteria.dto.ProveedorRequest;
import com.utp.ferreteria.models.Proveedor;
import com.utp.ferreteria.respositories.ProveedorRepository;

@Service
public class ProveedorService {
    
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> obtenerProveedores(){
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerProveedorPorId(Long id){
        return proveedorRepository.findById(id).orElse(null);
    }

    public void guardarProveedor(ProveedorRequest request){
        proveedorRepository.save(
            Proveedor.builder()
                .nombre(request.getNombre())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .ruc(request.getRuc())
                .build()
        );
    }

    public void actualizarProveedor(Long id, ProveedorRequest request){
        proveedorRepository.findById(id).ifPresent(proveedor -> {
            proveedor.setNombre(request.getNombre());
            proveedor.setDireccion(request.getDireccion());
            proveedor.setTelefono(request.getTelefono());
            proveedor.setEmail(request.getEmail());
            proveedor.setRuc(request.getRuc());
            proveedorRepository.save(proveedor);
        });
    }

    public void eliminarProveedor(Long id){
        proveedorRepository.findById(id).ifPresent(proveedor -> {
            proveedorRepository.delete(proveedor);
        });
    }
}
