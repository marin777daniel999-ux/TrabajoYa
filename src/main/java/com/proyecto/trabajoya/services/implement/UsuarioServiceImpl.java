package com.proyecto.trabajoya.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.trabajoya.models.Usuario;
import com.proyecto.trabajoya.repository.UsuarioRepository;
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;

@Service 
public class UsuarioServiceImpl implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public Usuario obtenerUsuario(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario buscarPorDocumento(String documento) {
        
        return usuarioRepository.findByDocumento(documento).orElse(null);
    }

    @Override
    public boolean modificarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public boolean removerUsuario(Integer id) {
        usuarioRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}