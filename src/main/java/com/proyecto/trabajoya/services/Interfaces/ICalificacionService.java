package com.proyecto.trabajoya.services.Interfaces;

import java.util.List;

import com.proyecto.trabajoya.models.Calificacion;

public interface ICalificacionService {
    boolean registrarCalificacion(Calificacion calificacion);
    Calificacion obtenerCalificacion(Integer id);
    Calificacion buscarPorCodigo(String codigo);
    boolean modificarCalificacion (Calificacion calificacion);
    boolean removerCalificacion (Integer id);
    List<Calificacion> listarCalifiaciones();
}
