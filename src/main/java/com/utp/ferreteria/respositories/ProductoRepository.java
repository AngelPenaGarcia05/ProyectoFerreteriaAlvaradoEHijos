package com.utp.ferreteria.respositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.utp.ferreteria.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {

    List<Producto> findByNombreContaining(String filtro);

    List<Producto> findByCategoria(String categoria);
}
