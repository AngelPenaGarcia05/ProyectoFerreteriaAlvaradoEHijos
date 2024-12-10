package com.utp.ferreteria.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.utp.ferreteria.models.Venta;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class VentaSpecification {
    public static Specification<Venta> conRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return (Root<Venta> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (fechaInicio == null || fechaFin == null) {
                return cb.conjunction();
            }
            return cb.between(root.get("fecha"), fechaInicio, fechaFin);
        };
    }
    public static Specification<Venta> conCliente(Long clienteId) {
        return (Root<Venta> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (clienteId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("cliente").get("id"), clienteId);
        };
    }
}
