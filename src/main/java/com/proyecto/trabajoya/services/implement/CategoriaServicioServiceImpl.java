package com.proyecto.trabajoya.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.trabajoya.models.CategoriaServicio;
import com.proyecto.trabajoya.repository.CategoriaServicioRepository;
import com.proyecto.trabajoya.services.Interfaces.ICategoriaServicioService;

@Service 
public class CategoriaServicioServiceImpl implements ICategoriaServicioService {
    private final CategoriaServicioRepository categoriaServicioRepository;

    public CategoriaServicioServiceImpl(CategoriaServicioRepository categoriaServicioRepository) {
        this.categoriaServicioRepository = categoriaServicioRepository;
    }

    @Override
    public boolean registrarCategoriaServicio(CategoriaServicio categoriaServicio) {
        categoriaServicioRepository.save(categoriaServicio);
        return true;
    }

    @Override
    public CategoriaServicio obtenerCategoriaServicio(Integer id) {
        return categoriaServicioRepository.findById(id).orElse(null);
    }

    @Override
    public CategoriaServicio buscarPorCodigo(String codigo) {
        return categoriaServicioRepository.findByCodigo(codigo);
    }

    @Override
    public boolean modificarCategoriaServicio(CategoriaServicio categoriaServicio) {
        categoriaServicioRepository.save(categoriaServicio);
        return true;
    }

    @Override
    public boolean removerCategoriaServicio(Integer id) {
        categoriaServicioRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CategoriaServicio> listarCategoriaServicios() {
        return categoriaServicioRepository.findAll();
    }

    @Override
    public List<CategoriaServicio> listarServicioPorCategoria(Integer idServicio) {
        return categoriaServicioRepository.findByServicioIdServicio(idServicio);
    }

}
