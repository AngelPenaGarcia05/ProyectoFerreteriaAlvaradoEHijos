package com.utp.ferreteria.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utp.ferreteria.models.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByNombre(String nombre);
}
