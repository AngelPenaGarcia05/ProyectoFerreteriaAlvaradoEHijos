package com.utp.ferreteria.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utp.ferreteria.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
