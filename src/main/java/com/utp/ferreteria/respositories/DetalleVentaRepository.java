package com.utp.ferreteria.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utp.ferreteria.models.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    
}
