package com.proyecto.trabajoya.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.trabajoya.models.Contrato;
import com.proyecto.trabajoya.repository.ContratoRepository;
import com.proyecto.trabajoya.services.Interfaces.IContratoService;

@Service 
public class ContratoServiceImpl implements IContratoService {
    private final ContratoRepository contratoRepository;

    public ContratoServiceImpl(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    @Override
    public boolean registrarContrato(Contrato contrato) {
        contratoRepository.save(contrato);
        return true;
    }

    @Override
    public Contrato obtenerContrato(Integer id) {
        return contratoRepository.findById(id).orElse(null);
    }

    @Override
    public Contrato buscarPorCodigo(String codigo) {
      
        return contratoRepository.findByCodigo(codigo).orElse(null);
    }

    @Override
    public boolean modificarContrato(Contrato contrato) {
        contratoRepository.save(contrato);
        return true;
    }

    @Override
    public boolean removerContrato(Integer id) {
        contratoRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    @Override
    public List<Contrato> listarPorCliente(Integer idCliente) {
        return contratoRepository.findByClienteIdUsuario(idCliente);
    }

    @Override
    public List<Contrato> listarPorPrestador(Integer idPrestador) {
        return contratoRepository.findByPrestadorIdUsuario(idPrestador);
    }
}