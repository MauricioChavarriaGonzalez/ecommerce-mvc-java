package com.tuuniversidad.ecommerce.service;

import com.tuuniversidad.ecommerce.model.Producto;
import com.tuuniversidad.ecommerce.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    
    // Inyección de dependencias: Le pedimos a Spring que nos traiga el repositorio
    private final ProductoRepository productoRepository;

    ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Método para obtener todos los productos de la base de datos
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    // Método para guardar un producto nuevo
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }
    
    // Método para buscar un solo producto por su ID (útil para el carrito)
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
} 