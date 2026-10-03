package com.proyecto.trabajoya.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.trabajoya.models.Servicio;
import com.proyecto.trabajoya.repository.ServicioRepository;
import com.proyecto.trabajoya.services.Interfaces.IServicioService;

@Service 
public class ServicioServiceImpl implements IServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public boolean registrarServicio(Servicio servicio) {
        servicioRepository.save(servicio);
        return true;
    }

    @Override
    public Servicio obtenerServicio(Integer id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public Servicio buscarPorCodigo(String codigo) {
        return servicioRepository.findByCodigo(codigo);
    }

    @Override
    public boolean modificarServicio(Servicio servicio) {
        servicioRepository.save(servicio);
        return true;
    }

    @Override
    public boolean removerServicio(Integer id) {
        servicioRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public List<Servicio> listarContratoPorServicio(Integer idContrato) {
        return servicioRepository.findByContratoIdContrato(idContrato);
    }

    @Override
    public List<Servicio> listarUsuarioPorServicio(Integer idUsuario) {
        return servicioRepository.findByUsuarioIdUsuario(idUsuario);
    }
}
