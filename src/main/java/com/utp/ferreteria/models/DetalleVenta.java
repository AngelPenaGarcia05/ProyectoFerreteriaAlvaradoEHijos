package com.utp.ferreteria.models;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "detalleventas")
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int cantidad;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "productoID")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "ventaID")
    private Venta venta;
}
