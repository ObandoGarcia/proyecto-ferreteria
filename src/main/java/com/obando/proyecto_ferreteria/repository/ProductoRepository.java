package com.obando.proyecto_ferreteria.repository;

import com.obando.proyecto_ferreteria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
