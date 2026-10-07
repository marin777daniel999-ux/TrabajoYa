package com.proyecto.trabajoya.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.trabajoya.models.CategoriaServicio;

@Repository
public interface CategoriaServicioRepository extends JpaRepository<CategoriaServicio, Integer> {
    
    Optional<CategoriaServicio> findByCodigo(String codigo);
    
    // Corregido: se le indicó el tipo Integer al parámetro
    List<CategoriaServicio> findByServiciosIdServicio(Integer idServicio);
    
    Optional<CategoriaServicio> findByNombre(String nombre);
}