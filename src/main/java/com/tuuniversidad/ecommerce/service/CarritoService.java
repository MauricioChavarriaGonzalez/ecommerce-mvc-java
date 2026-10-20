package com.tuuniversidad.ecommerce.service;

import com.tuuniversidad.ecommerce.model.Carrito;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope // ¡Esta anotación es la clave de todo!
public class CarritoService {
    
    private Carrito carrito = new Carrito();

    public Carrito getCarrito() {
        return carrito;
    }

    public void vaciarCarrito() {
        this.carrito = new Carrito();
    }
} 