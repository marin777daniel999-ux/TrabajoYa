package com.proyecto.trabajoya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.trabajoya.models.Calificacion;

public interface CalificacionRepository extends JpaRepository<Calificacion, Integer>{
    
    Calificacion findBYCodigo(String codigo);
}
