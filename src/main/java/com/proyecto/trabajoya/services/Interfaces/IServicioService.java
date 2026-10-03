package com.proyecto.trabajoya.services.Interfaces;

import java.util.List;

import com.proyecto.trabajoya.models.Servicio;

public interface IServicioService {
    boolean registrarServicio(Servicio servicio);
    Servicio obtenerServicio(Integer id);
    Servicio buscarPorCodigo(String codigo);
    boolean modificarServicio(Servicio servicio);
    boolean removerServicio(Integer id);
    List<Servicio> listarServicios();
    List<Servicio> listarContratoPorServicio(Integer idContrato);
    List<Servicio> listarUsuarioPorServicio(Integer idUsuario);
}
