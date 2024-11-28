package com.utp.ferreteria.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.ferreteria.dto.DetalleVentaDTO;
import com.utp.ferreteria.dto.VentaRequest;
import com.utp.ferreteria.models.DetalleVenta;
import com.utp.ferreteria.models.Producto;
import com.utp.ferreteria.models.Venta;
import com.utp.ferreteria.models.Cliente;
import com.utp.ferreteria.respositories.ClienteRepository;
import com.utp.ferreteria.respositories.ProductoRepository;
import com.utp.ferreteria.respositories.VentaRepository;
import com.utp.ferreteria.respositories.DetalleVentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public Venta insertarVenta(VentaRequest ventaRequest) {
        // Crear una nueva venta
        Venta venta = new Venta();
        venta.setTipoPago(ventaRequest.getTipoPago());
        venta.setEstado(ventaRequest.getEstado());
        venta.setFecha(ventaRequest.getFecha());
        
        Cliente cliente = clienteRepository.findById(ventaRequest.getClienteID())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        venta.setCliente(cliente);

        // Inicializar la lista de detalles de la venta
        venta.setDetallesVenta(new ArrayList<>());

        // Guardar la venta (sin los detalles aún)
        venta = ventaRepository.save(venta);

        // Procesar los detalles de la venta
        for (DetalleVentaDTO detalleDTO : ventaRequest.getDetallesVenta()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoID())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setCantidad(detalleDTO.getCantidad());
            detalleVenta.setProducto(producto);
            detalleVenta.setVenta(venta);

            // Calcular el subtotal
            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(detalleDTO.getCantidad()));
            detalleVenta.setSubtotal(subtotal);

            // Guardar el detalle de la venta
            detalleVenta = detalleVentaRepository.save(detalleVenta);
            venta.getDetallesVenta().add(detalleVenta);
        }

        return venta;
    }

}
