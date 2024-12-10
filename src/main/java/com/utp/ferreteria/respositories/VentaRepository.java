package com.utp.ferreteria.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.utp.ferreteria.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>,JpaSpecificationExecutor<Venta> {
    
}
