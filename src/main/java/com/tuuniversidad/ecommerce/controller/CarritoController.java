package com.tuuniversidad.ecommerce.controller;

import com.tuuniversidad.ecommerce.model.Producto;
import com.tuuniversidad.ecommerce.service.CarritoService;
import com.tuuniversidad.ecommerce.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito") // Todas las rutas de aquí empezarán con /carrito
public class CarritoController {

    private final CarritoService carritoService;

    private final ProductoService productoService;

    CarritoController(CarritoService carritoService, ProductoService productoService) {
        this.carritoService = carritoService;
        this.productoService = productoService;
    }

    // Muestra la página del carrito
    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("carrito", carritoService.getCarrito());
        return "carrito/detalle";
    }

    // Se activa cuando el usuario hace clic en "Agregar al Carrito"
    @GetMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable Long id, @RequestParam(defaultValue = "1") int cantidad) {
        // Buscamos el producto en la base de datos usando el ID
        Producto producto = productoService.obtenerPorId(id);
        if (producto != null) {
            // Si existe, lo agregamos al carrito de la sesión
            carritoService.getCarrito().agregarProducto(producto, cantidad);
        }
        // Redirigimos al usuario a la página del carrito para que vea su compra
        return "redirect:/carrito";
    }

    // Se activa al hacer clic en "Eliminar" dentro del carrito
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        carritoService.getCarrito().eliminarProducto(id);
        return "redirect:/carrito"; // Recarga la página del carrito
    }

    // Se activa al hacer clic en "Procesar Compra"
    @GetMapping("/comprar")
    public String procesarCompra() {
        // Vaciamos el carrito para simular que la compra terminó
        carritoService.vaciarCarrito();
        
        // Redirigimos a la tienda con un parámetro en la URL (?compraExitosa=true)
        return "redirect:/?compraExitosa=true";
    }
} 