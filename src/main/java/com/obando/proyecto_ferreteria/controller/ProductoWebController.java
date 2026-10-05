package com.obando.proyecto_ferreteria.controller;

import com.obando.proyecto_ferreteria.model.Producto;
import com.obando.proyecto_ferreteria.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoWebController {

    private final ProductoService productoService;

    public ProductoWebController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute(
                "productos",
                productoService.traerProductos()
        );

        return "productos/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo", "Nuevo Producto");

        return "productos/formulario";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Producto producto, Model model){
        Producto resultado;
        if (producto.getCodProducto() == null) {
            resultado = productoService.crearProducto(producto);
        } else {
            resultado = productoService.actualizarProducto(
                    producto.getCodProducto(),
                    producto
            );
        }

        if (resultado == null) {
            model.addAttribute("producto", producto);
            model.addAttribute(
                    "titulo",
                    producto.getCodProducto() == null
                            ? "Registrar producto"
                            : "Editar producto"
            );
            model.addAttribute("error", "Los campos no pueden estar vacios" +
                    "el precio debe ser mayor a cero y el stock debe ser negativo");

            return "productos/formulario";
        }

        return "redirect:/productos";
    }

    @GetMapping("/editar/{codProducto}")
    public String mostrarFormularioEditar(Model model, @PathVariable Long codProducto){
        Producto producto = productoService.buscarProductoPorId(codProducto);

        if (producto == null) {
            return "redirect:/productos";
        }

        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar Producto");

        return "productos/formulario";
    }

    @PostMapping("eliminar/{codProducto}")
    public String eliminar(@PathVariable Long codProducto){
        productoService.eliminarProducto(codProducto);

        return "redirect:/productos";
    }
}
