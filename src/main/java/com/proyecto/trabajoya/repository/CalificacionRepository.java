package com.proyecto.trabajoya.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.trabajoya.models.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    
    Optional<Calificacion> findByCodigo(String codigo);
    
    // Buscar la calificación asociada a un contrato específico
    Optional<Calificacion> findByContratoIdContrato(Integer idContrato);
}