package com.proyecto.trabajoya.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.trabajoya.models.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    
    Optional<Servicio> findByCodigo(String codigo);
    List<Servicio> findByContratos_IdContrato(Integer idContrato);
    // Listar todos los servicios publicados por un usuario (prestador) específico
    List<Servicio> findByUsuarioIdUsuario(Integer idUsuario);

    // Listar servicios según su estado (activos o inactivos)
    List<Servicio> findByEstado(boolean estado);
}