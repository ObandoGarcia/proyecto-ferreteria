package com.obando.proyecto_ferreteria.controller;

import com.obando.proyecto_ferreteria.model.Producto;
import com.obando.proyecto_ferreteria.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listar(){
        return productoService.traerProductos();
    }

    @GetMapping("/{codProducto}")
    public ResponseEntity<?> buscar(@PathVariable Long codProducto){
        Producto producto = productoService.buscarProductoPorId(codProducto);

        if (producto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(producto);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Producto producto){
        Producto productoCreado = productoService.crearProducto(producto);

        if (productoCreado == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().body(productoCreado);
    }

    @PutMapping("/{codProducto}")
    public  ResponseEntity<?> actualizar(@PathVariable Long codProducto, @RequestBody Producto producto){
        Producto productoDesdeBd = productoService.buscarProductoPorId(codProducto);

        if (productoDesdeBd == null){
            return ResponseEntity.notFound().build();
        }

        Producto productoEditado = productoService.actualizarProducto(codProducto, producto);

        if (productoEditado == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(productoEditado);
    }

    @DeleteMapping("/{codProducto}")
    public ResponseEntity<?> eliminar(@PathVariable Long codProducto){
        Producto productoDesdeBd = productoService.buscarProductoPorId(codProducto);

        if (productoDesdeBd == null){
            return ResponseEntity.notFound().build();
        }

        productoService.eliminarProducto(codProducto);

        return ResponseEntity.noContent().build();
    }
}
