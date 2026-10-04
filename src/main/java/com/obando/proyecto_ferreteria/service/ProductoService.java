package com.obando.proyecto_ferreteria.service;

import com.obando.proyecto_ferreteria.model.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> traerProductos();
    Producto buscarProductoPorId(Long codProducto);
    Producto crearProducto(Producto producto);
    Producto actualizarProducto(Long codProducto, Producto producto);
    boolean eliminarProducto(Long codProducto);
}
