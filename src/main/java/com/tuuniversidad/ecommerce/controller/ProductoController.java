package com.tuuniversidad.ecommerce.controller;

import com.tuuniversidad.ecommerce.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

    private final ProductoService productoService;

    ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Esta anotación mapea la ruta raíz (http://localhost:8080/) y la ruta /productos
    @GetMapping({"/", "/productos"})
    public String listarProductos(Model model) {
        // Buscamos todos los productos en la base de datos y los enviamos a la vista
        model.addAttribute("productos", productoService.listarTodos());
        
        // Retornamos el nombre del archivo HTML que se va a mostrar
        return "productos/lista"; 
    }
} 