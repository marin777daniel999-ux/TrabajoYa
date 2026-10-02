package com.proyecto.trabajoya.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.trabajoya.models.CategoriaServicio;

public interface CategoriaServicioRepository extends JpaRepository<CategoriaServicio, Integer>{
    CategoriaServicio findByCodigo(String codigo);
    List<CategoriaServicio> findByServicioIdServicio(Integer idServicio);
}
