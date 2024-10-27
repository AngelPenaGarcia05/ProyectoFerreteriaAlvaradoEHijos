package com.utp.ferreteria.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import com.utp.ferreteria.models.DetalleVenta;
import com.utp.ferreteria.respositories.DetalleVentaRepository;

import java.util.List;


@Service
public class DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> obtenerDetallesVentas(){
        return detalleVentaRepository.findAll();
    }

    public DetalleVenta obtenerDetalleVentaPorId(Long id){
        return detalleVentaRepository.findById(id).orElse(null);
    }

    public DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta){
        return detalleVentaRepository.save(detalleVenta);
    }

    public void actualizarDetalleVenta(Long id, DetalleVenta detalleVenta){
        detalleVentaRepository.findById(id).ifPresent(detalleVentaExistente -> {
            BeanUtils.copyProperties(detalleVenta, detalleVentaExistente, "id");
            detalleVentaRepository.save(detalleVentaExistente);
        });
    }

    public void eliminarDetalleVenta(Long id){
        detalleVentaRepository.findById(id).ifPresent(detalleVenta -> {
            detalleVentaRepository.delete(detalleVenta);
        });
    }
    
}
