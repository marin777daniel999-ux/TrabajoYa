package com.proyecto.trabajoya.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.trabajoya.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    
    Optional<Usuario> findByDocumento(String documento);

    
    Optional<Usuario> findByCorreo(String correo);

    // Búsqueda por nickName
    Optional<Usuario> findByNickName(String nickName);

    // Listar usuarios filtrados por rol (ej: listar solo prestadores para el catálogo de servicios)
    List<Usuario> findByRol(Usuario.Rol rol);

    // Validación rápida para verificar existencia en registros
    boolean existsByCorreo(String correo);
    boolean existsByDocumento(String documento);
    boolean existsByNickName(String nickName);
}