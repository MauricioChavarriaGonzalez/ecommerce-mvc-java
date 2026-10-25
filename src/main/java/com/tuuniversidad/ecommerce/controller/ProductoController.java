package com.tuuniversidad.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tuuniversidad.ecommerce.model.Carrito;
import com.tuuniversidad.ecommerce.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping({ "/", "/productos" })
    public String listarProductos(Model model, HttpSession session) {
        model.addAttribute("productos", productoService.listarTodos());

        // Obtenemos el carrito de la sesión para el contador
        Carrito carrito = (Carrito) session.getAttribute("miCarrito");
        model.addAttribute("carrito", carrito != null ? carrito : new Carrito());

        return "productos/lista";
    }
}