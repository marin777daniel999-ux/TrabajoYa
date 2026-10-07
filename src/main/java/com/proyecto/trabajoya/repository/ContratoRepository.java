package com.proyecto.trabajoya.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.trabajoya.models.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    

    Optional<Contrato> findByCodigo(String codigo);

    // Listar contratos donde el usuario participa como CLIENTE
    List<Contrato> findByClienteIdUsuario(Integer idCliente);

    // Listar contratos donde el usuario participa como PRESTADOR
    List<Contrato> findByPrestadorIdUsuario(Integer idPrestador);
    
    // Listar contratos según su estado
    List<Contrato> findByEstado(Boolean estado);
}