package com.utp.ferreteria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Setter;
import lombok.Getter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;

    @Column(length = 50)
    private String estado;

    // Bidirectional dependency
    @ManyToOne
    @JoinColumn(name = "productoID")
    private Producto producto;

}
