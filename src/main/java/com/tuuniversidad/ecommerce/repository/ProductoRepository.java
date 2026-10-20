package com.tuuniversidad.ecommerce.repository;

import com.tuuniversidad.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
} 