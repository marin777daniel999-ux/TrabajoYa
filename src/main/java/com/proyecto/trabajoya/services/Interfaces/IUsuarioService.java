package com.proyecto.trabajoya.services.Interfaces;

import java.util.List;

import com.proyecto.trabajoya.models.Usuario;

public interface IUsuarioService {
    boolean registrarUsuario(Usuario usuario);
    Usuario obtenerUsuario(Integer id);
    Usuario buscarPorDocumento(String documento);
    boolean modificarUsuario(Usuario usuario);
    boolean removerUsuario(Integer id);
    List<Usuario> listarUsuarios();
}
