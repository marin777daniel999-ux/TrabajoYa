package com.proyecto.trabajoya.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.trabajoya.models.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
    
    Servicio findByCodigo(String codigo);
    List<Servicio> findByContratoIdContrato(Integer idContrato);
    List<Servicio> findByUsuarioIdUsuario(Integer idUsuario);
}
