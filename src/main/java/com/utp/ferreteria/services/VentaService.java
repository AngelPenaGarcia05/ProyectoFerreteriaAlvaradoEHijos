package com.utp.ferreteria.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.utp.ferreteria.dto.ClienteResponse;
import com.utp.ferreteria.dto.DetalleVentaDTO;
import com.utp.ferreteria.dto.DetalleVentaResponse;
import com.utp.ferreteria.dto.VentaReponse;
import com.utp.ferreteria.dto.VentaRequest;
import com.utp.ferreteria.models.DetalleVenta;
import com.utp.ferreteria.models.Producto;
import com.utp.ferreteria.models.Venta;
import com.utp.ferreteria.respositories.ClienteRepository;
import com.utp.ferreteria.respositories.ProductoRepository;
import com.utp.ferreteria.respositories.VentaRepository;
import com.utp.ferreteria.specifications.VentaSpecification;
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
        //verificar que todos los productos tengan stock disponible
        for (DetalleVentaDTO detalleDTO : ventaRequest.getDetallesVenta()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoID())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            if (producto.getCantidad() < detalleDTO.getCantidad()) {
                throw new RuntimeException("No hay stock disponible");
            }
        }
        Venta venta =Venta.builder()
                .tipoPago(ventaRequest.getTipoPago())
                .estado(ventaRequest.getEstado())
                .fecha(ventaRequest.getFecha())
                .nombres(ventaRequest.getNombres())
                .dirección(ventaRequest.getDireccion())
                .codigoPostal(ventaRequest.getCodigoPostal())
                .telefono(ventaRequest.getTelefono())
                .instrucciones(ventaRequest.getInstrucciones())
                .total(BigDecimal.ZERO)
                .detallesVenta(new ArrayList<>())
                .cliente(clienteRepository.findById(ventaRequest.getClienteID())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado")))
                .build();
        ventaRepository.save(venta);
        for (DetalleVentaDTO detalleDTO : ventaRequest.getDetallesVenta()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoID())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            //actualziar stock
            producto.setCantidad(producto.getCantidad() - detalleDTO.getCantidad());
            DetalleVenta detalleVenta = DetalleVenta.builder()
                    .cantidad(detalleDTO.getCantidad())
                    .producto(producto)
                    .venta(venta)
                    .subtotal(producto.getPrecio().multiply(BigDecimal.valueOf(detalleDTO.getCantidad())))
                    .build();
            detalleVentaRepository.save(detalleVenta);
            venta.getDetallesVenta().add(detalleVenta);
            venta.setTotal(venta.getTotal().add(detalleVenta.getSubtotal()));
        }
            ventaRepository.save(venta);
        
        return venta;
    }

    public List<VentaReponse> getVentas(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        Specification<Venta> specs = VentaSpecification.conRangoFecha(fechaInicio, fechaFin)
            .and(VentaSpecification.conCliente(clienteId));
        List<Venta> ventas = ventaRepository.findAll(specs);
        return ventas.stream().map(venta -> 
            VentaReponse.builder()
                    .id(venta.getId())
                    .tipoPago(venta.getTipoPago())
                    .estado(venta.getEstado())
                    .fecha(venta.getFecha().toString())
                    .nombres(venta.getNombres())
                    .direccion(venta.getDirección())
                    .codigoPostal(venta.getCodigoPostal())
                    .telefono(venta.getTelefono())
                    .instrucciones(venta.getInstrucciones())
                    .total(venta.getTotal())
                    .cliente(ClienteResponse.builder()
                            .id(venta.getCliente().getId())
                            .nombres(venta.getCliente().getNombres())
                            .apellidos(venta.getCliente().getApellidos())
                            .dni(venta.getCliente().getDni())
                            .direccion(venta.getCliente().getDireccion())
                            .telefono(venta.getCliente().getTelefono())
                            .email(venta.getCliente().getEmail())
                            .fechaRegistro(venta.getCliente().getFechaRegistro().toString())
                            .rol(venta.getCliente().getRol().name())
                            .build())
                    .detallesVenta(venta.getDetallesVenta().stream().map(detalleVenta -> {
                        Producto producto = detalleVenta.getProducto();
                        return DetalleVentaResponse.builder()
                                .id(detalleVenta.getId())
                                .cantidad(detalleVenta.getCantidad())
                                .subtotal(detalleVenta.getSubtotal())
                                .producto(producto)
                                .build();
                    }).toArray(DetalleVentaResponse[]::new))
                    .build()
        ).toList();
    }

    public void actualizarEstadoVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        venta.setEstado("Completada");
        ventaRepository.save(venta);
    }

}
