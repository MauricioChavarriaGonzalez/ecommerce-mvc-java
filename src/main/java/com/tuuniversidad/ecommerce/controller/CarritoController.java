package com.tuuniversidad.ecommerce.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuuniversidad.ecommerce.model.Carrito;
import com.tuuniversidad.ecommerce.model.DetalleOrden;
import com.tuuniversidad.ecommerce.model.Orden;
import com.tuuniversidad.ecommerce.model.Producto;
import com.tuuniversidad.ecommerce.repository.OrdenRepository;
import com.tuuniversidad.ecommerce.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final ProductoService productoService;
    private final OrdenRepository ordenRepository;

    public CarritoController(ProductoService productoService, OrdenRepository ordenRepository) {
        this.productoService = productoService;
        this.ordenRepository = ordenRepository;
    }

    private Carrito obtenerCarrito(HttpSession session) {
        Carrito carrito = (Carrito) session.getAttribute("miCarrito");
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("miCarrito", carrito);
        }
        return carrito;
    }

    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        model.addAttribute("carrito", obtenerCarrito(session));
        return "carrito/detalle";
    }

    @GetMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable Long id, HttpSession session) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto != null) {
            obtenerCarrito(session).agregarProducto(producto, 1);
        }
        return "redirect:/productos?agregado=true";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, HttpSession session) {
        obtenerCarrito(session).eliminarProducto(id);
        return "redirect:/carrito";
    }

    @PostMapping("/comprar")
    public String procesarCompra(HttpSession session) {
        Carrito carrito = obtenerCarrito(session);

        if (!carrito.getItems().isEmpty()) {
            // 1. Crear la orden
            Orden nuevaOrden = new Orden();
            nuevaOrden.setTotal(BigDecimal.valueOf(carrito.getTotal()));

            // 2. Crear los detalles
            List<DetalleOrden> detalles = new ArrayList<>();
            carrito.getItems().forEach(item -> {
                detalles.add(new DetalleOrden(
                        nuevaOrden,
                        item.getProducto(),
                        item.getCantidad(),
                        item.getProducto().getPrecio()));
            });

            nuevaOrden.setDetalles(detalles);

            // 3. Guardar en la base de datos
            ordenRepository.save(nuevaOrden);
        }

        // 4. Vaciar el carrito
        session.removeAttribute("miCarrito");
        return "redirect:/productos?compraExitosa=true";
    }
}