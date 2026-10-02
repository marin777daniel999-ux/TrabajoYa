package com.proyecto.trabajoya.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.trabajoya.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Usuario findByDocumento(String documento);
    List<Usuario> findByServicioIdServicio(int idServicio);
    List<Usuario> findByContratoIdContrato(int idContrato);
}
