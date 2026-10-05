package com.proyecto.trabajoya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.trabajoya.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Usuario findByDocumento(String documento);
    // esas listas iban en los otros repositorios (contrato y servicio)
}
