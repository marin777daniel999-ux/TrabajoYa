package com.proyecto.trabajoya.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.trabajoya.models.Calificacion;
import com.proyecto.trabajoya.repository.CalificacionRepository;
import com.proyecto.trabajoya.services.Interfaces.ICalificacionService;

@Service 
public class CalificacionServiceImpl implements ICalificacionService {
    private final CalificacionRepository calificacionRepository;

    public CalificacionServiceImpl(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    @Override
    public boolean registrarCalificacion(Calificacion calificacion) {
        calificacionRepository.save(calificacion);
        return true;
    }

    @Override
    public Calificacion obtenerCalificacion(Integer id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    @Override
    public Calificacion buscarPorCodigo(String codigo) {
        return calificacionRepository.findByCodigo(codigo);
    }

    @Override
    public boolean modificarCalificacion(Calificacion calificacion) {
        calificacionRepository.save(calificacion);
        return true;
    }

    @Override
    public boolean removerCalificacion(Integer id) {
        calificacionRepository.deleteById(id);;
        return true;
    }

    @Override
    public List<Calificacion> listarCalifiaciones() {
        return calificacionRepository.findAll();
    }

}
