package com.proyecto.trabajoya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.trabajoya.models.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Integer>{
    
    Contrato findByCodigo (String codigo);
}
