package com.tuuniversidad.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        // Primero verificamos si el producto ya está en el carrito
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                // Si ya existe, solo sumamos la cantidad
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        // Si no existe, lo agregamos como un nuevo ítem
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(Long productoId) {
        // Elimina el producto del carrito si coincide con el ID
        items.removeIf(item -> item.getProducto().getId().equals(productoId));
    }

    public List<ItemCarrito> getItems() { 
        return items; 
    }

    // Calcula el total a pagar sumando los subtotales de cada ítem
    public double getTotal() {
        return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }
}