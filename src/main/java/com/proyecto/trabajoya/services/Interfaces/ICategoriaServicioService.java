package com.proyecto.trabajoya.services.Interfaces;

import java.util.List;

import com.proyecto.trabajoya.models.CategoriaServicio;

public interface ICategoriaServicioService {
    boolean registrarCategoriaServicio(CategoriaServicio categoriaServicio);
    CategoriaServicio obtenerCategoriaServicio(Integer id);
    CategoriaServicio buscarPorCodigo(String codigo);
    boolean modificarCategoriaServicio(CategoriaServicio categoriaServicio);
    boolean removerCategoriaServicio(Integer id);
    List<CategoriaServicio> listarCategoriaServicios();
    List<CategoriaServicio> listarServicioPorCategoria(Integer idServicio);
}
