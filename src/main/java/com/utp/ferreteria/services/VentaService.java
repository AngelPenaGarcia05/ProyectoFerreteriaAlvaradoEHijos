package com.utp.ferreteria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utp.ferreteria.models.Venta;
import com.utp.ferreteria.respositories.VentaRepository;
import org.springframework.beans.BeanUtils;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> obtenerVentas(){
        return ventaRepository.findAll();
    }

    public Venta obtenerVentaPorId(Long id){
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta guardarVenta(Venta venta){
        return ventaRepository.save(venta);
    }

    public void actualizarVenta(Long id, Venta venta){
        ventaRepository.findById(id).ifPresent(ventaExistente -> {
            BeanUtils.copyProperties(venta, ventaExistente, "id");
            ventaRepository.save(ventaExistente);
        });
    }

    public void eliminarVenta(Long id){
        ventaRepository.findById(id).ifPresent(venta -> {
            ventaRepository.delete(venta);
        });
    }
}
