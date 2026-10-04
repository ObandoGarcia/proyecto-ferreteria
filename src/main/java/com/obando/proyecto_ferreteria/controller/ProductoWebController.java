package com.obando.proyecto_ferreteria.controller;

import com.obando.proyecto_ferreteria.model.Producto;
import com.obando.proyecto_ferreteria.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
