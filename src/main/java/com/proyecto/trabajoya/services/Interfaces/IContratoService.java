package com.proyecto.trabajoya.services.Interfaces;

import java.util.List;

import com.proyecto.trabajoya.models.Contrato;

public interface IContratoService {
    boolean registrarContrato(Contrato contrato);
    Contrato obtenerContrato(Integer id);
    Contrato buscarPorCodigo(String codigo);
    boolean modificarContrato(Contrato contrato);
    boolean removerContrato(Integer id);
    List<Contrato> listarContratos();
    List<Contrato> listarPorCliente(Integer idCliente);
    List<Contrato> listarPorPrestador(Integer idPrestador);
}