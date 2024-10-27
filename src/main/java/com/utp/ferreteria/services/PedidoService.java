package com.utp.ferreteria.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;

import com.utp.ferreteria.models.Pedido;
import com.utp.ferreteria.respositories.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obtenerPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido guardarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public void actualizarPedido(Long id, Pedido pedido){
        pedidoRepository.findById(id).ifPresent(pedidoExistente -> {
            BeanUtils.copyProperties(pedido, pedidoExistente, "id");
            pedidoRepository.save(pedidoExistente);
        });
    }

    public void eliminarPedido(Long id){
        pedidoRepository.findById(id).ifPresent(pedido -> {
            pedidoRepository.delete(pedido);
        });
    }

    
}
