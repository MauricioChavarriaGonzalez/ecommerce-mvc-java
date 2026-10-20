package com.tuuniversidad.ecommerce;

import com.tuuniversidad.ecommerce.model.Producto;
import com.tuuniversidad.ecommerce.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    DataInitializer(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verificamos si la base de datos está vacía para no duplicar productos al reiniciar
        if (productoRepository.count() == 0) {
            productoRepository.save(new Producto("Laptop Gamer", "Laptop de alta gama para juegos", new BigDecimal("1200.00"), 10));
            productoRepository.save(new Producto("Mouse Inalámbrico", "Mouse ergonómico con Bluetooth", new BigDecimal("45.50"), 50));
            productoRepository.save(new Producto("Teclado Mecánico", "Teclado RGB con switches azules", new BigDecimal("85.00"), 30));
            productoRepository.save(new Producto("Monitor 4K", "Monitor UHD de 27 pulgadas", new BigDecimal("350.00"), 15));
            
            System.out.println("✅ Base de datos inicializada con productos de prueba.");
        }
    }
} 