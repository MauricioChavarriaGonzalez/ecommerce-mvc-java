package com.tuuniversidad.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.tuuniversidad.ecommerce.model.Carrito;

@Service
@SessionScope // Mantiene el carrito vivo por usuario
public class CarritoService {

    private Carrito carrito = new Carrito();

    public Carrito getCarrito() {
        return carrito;
    }

    public void vaciarCarrito() {
        this.carrito = new Carrito();
    }
}