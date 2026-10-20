package com.tuuniversidad.ecommerce.model;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { 
        return producto; 
    }
    
    public void setProducto(Producto producto) { 
        this.producto = producto; 
    }
    
    public int getCantidad() { 
        return cantidad; 
    }
    
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }
    
    // Método para calcular el precio total de este ítem (Precio unitario * cantidad)
    public double getSubtotal() {
        return producto.getPrecio().doubleValue() * cantidad;
    }
} 