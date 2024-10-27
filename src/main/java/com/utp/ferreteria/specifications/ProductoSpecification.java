package com.utp.ferreteria.specifications;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import com.utp.ferreteria.models.Producto;

public class ProductoSpecification {
    public static Specification<Producto> conCategoria(String categoria) {
        return (Root<Producto> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (categoria == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("categoria"), categoria);
        };
    }

    public static Specification<Producto> conNombre(String nombre) {
        return (Root<Producto> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (nombre == null) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
        };
    }
}
