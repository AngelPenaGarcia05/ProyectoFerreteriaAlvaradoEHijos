package com.utp.ferreteria.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utp.ferreteria.models.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}
