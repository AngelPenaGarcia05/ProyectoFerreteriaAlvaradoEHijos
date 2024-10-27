package com.utp.ferreteria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.utp.ferreteria.dto.ProductRequest;
import com.utp.ferreteria.models.Producto;
import com.utp.ferreteria.models.Proveedor;
import com.utp.ferreteria.respositories.ProductoRepository;
import com.utp.ferreteria.specifications.ProductoSpecification;


@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorService proveedorService;

    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }
    public Long obtenerCantidadProductos(){
        return productoRepository.count();
    }
    public Long obtenerCantidadConFiltro(String nombre, String categoria, Long inicio, Long cantidad){
        Specification<Producto> specs = ProductoSpecification.conNombre(nombre).and(ProductoSpecification.conCategoria(categoria));
        return productoRepository.count(specs);
    }

    public List<Producto> obtenerProductosConFiltros(String nombre, String categoria, Long inicio, Long cantidad){
        Specification<Producto> specs = ProductoSpecification.conNombre(nombre).and(ProductoSpecification.conCategoria(categoria));
        if (cantidad == 0) {
            cantidad = productoRepository.count(specs);
        }
        return productoRepository.findAll(specs).stream().skip(inicio).limit(cantidad).toList();
    }

    public List<Producto> obtenerRangoProductos(int inicio, int cantidad){
        return productoRepository.findAll().stream().skip(inicio).limit(cantidad).toList();
    }
    public List<Producto> obtenerProductosPorNombreContiene(String filtro){
        return productoRepository.findByNombreContaining(filtro);
    }

    public List<Producto> obtenerProductosPorCategoria(List<Producto> productos, String categoria){
        return productos.stream().filter(producto -> producto.getCategoria().equals(categoria)).toList();
    }
    public List<Producto> obtenerProductosRelacionados(Long id){
        return productoRepository.findByCategoria(productoRepository.findById(id).get().getCategoria()).stream().skip(0).limit(8).toList();
    }

    public Producto obtenerProductoPorId(Long id){
        return productoRepository.findById(id).orElse(null);
    }
    public void guardarProducto(ProductRequest request){
        Proveedor proveedor = proveedorService.obtenerProveedorPorId(request.getProveedorID());
        productoRepository.save(
          Producto.builder()
            .nombre(request.getNombre())
            .precio(request.getPrecio())
            .cantidad(request.getCantidad())
            .descripcion(request.getDescripcion())
            .imagenURL(request.getImagenURL())
            .categoria(request.getCategoria())
            .proveedor(proveedor)
            .build()
        );
    }

    public void actualizarProducto(Long id, ProductRequest request){
        productoRepository.findById(id).ifPresent(producto -> {
            producto.setNombre(request.getNombre());
            producto.setPrecio(request.getPrecio());
            producto.setCantidad(request.getCantidad());
            producto.setDescripcion(request.getDescripcion());
            producto.setImagenURL(request.getImagenURL());
            producto.setCategoria(request.getCategoria());
            producto.setProveedor(proveedorService.obtenerProveedorPorId(request.getProveedorID()));
            productoRepository.save(producto);
        });
    }

    public void eliminarProducto(Long id){
        productoRepository.findById(id).ifPresent(producto -> {
            productoRepository.delete(producto);
        });
    }
}
