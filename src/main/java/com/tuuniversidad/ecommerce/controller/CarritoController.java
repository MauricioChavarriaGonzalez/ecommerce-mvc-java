
package com.tuuniversidad.ecommerce.controller;

import com.tuuniversidad.ecommerce.model.Producto;
import com.tuuniversidad.ecommerce.service.CarritoService;
import com.tuuniversidad.ecommerce.service.ProductoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;
    private final ProductoService productoService;

    public CarritoController(
            CarritoService carritoService,
            ProductoService productoService) {
        this.carritoService = carritoService;
        this.productoService = productoService;
    }

    // Mostrar el carrito
    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("carrito", carritoService.getCarrito());
        return "carrito/detalle";
    }

    // Agregar producto sin salir del catálogo
    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable Long id) {

        Producto producto = productoService.obtenerPorId(id);

        if (producto != null) {
            carritoService.getCarrito().agregarProducto(producto, 1);
        }

        return "redirect:/productos?agregado=true";
    }

    // Eliminar producto del carrito
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {

        carritoService.getCarrito().eliminarProducto(id);

        return "redirect:/carrito";
    }

    // Procesar compra
    @PostMapping("/comprar")
    public String procesarCompra() {

        carritoService.vaciarCarrito();

        return "redirect:/productos?compraExitosa=true";
    }
}
