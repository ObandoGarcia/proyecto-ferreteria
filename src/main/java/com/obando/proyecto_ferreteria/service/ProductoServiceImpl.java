package com.obando.proyecto_ferreteria.service;

import com.obando.proyecto_ferreteria.model.Producto;
import com.obando.proyecto_ferreteria.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> traerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Long codProducto) {
        return productoRepository.findById(codProducto).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        if (producto == null) return null;

        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long codProducto, Producto producto) {
        Producto productoDesdeLaBd = buscarProductoPorId(codProducto);
        if (productoDesdeLaBd == null) return null;

        productoDesdeLaBd.setNombre(producto.getNombre());
        productoDesdeLaBd.setMarca(producto.getMarca());
        productoDesdeLaBd.setPrecio(producto.getPrecio());
        productoDesdeLaBd.setCategoria(producto.getCategoria());
        productoDesdeLaBd.setCantidad(producto.getCantidad());
        productoDesdeLaBd.setDescripcion(producto.getDescripcion());

        return productoRepository.save(productoDesdeLaBd);
    }

    @Override
    public boolean eliminarProducto(Long codProducto) {
        Producto productoDesdeLaBd = buscarProductoPorId(codProducto);
        if (productoDesdeLaBd == null) return false;

        productoRepository.delete(productoDesdeLaBd);

        return true;
    }
}
