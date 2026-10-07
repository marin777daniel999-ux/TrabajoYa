package com.proyecto.trabajoya.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.trabajoya.models.Contrato;
import com.proyecto.trabajoya.services.Interfaces.IContratoService;
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;

@Controller 
@RequestMapping("/contratos")
public class ContratoController {
    
    private final IContratoService contratoService;
    private final IUsuarioService usuarioService;
    
    public ContratoController(IContratoService contratoService, IUsuarioService usuarioService) {
        this.contratoService = contratoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping 
    public String verContratos(Model model) {
        model.addAttribute("contrato", new Contrato());
        model.addAttribute("listaUsuarios", usuarioService.listarUsuarios());
        return "contratos";
    }

    @PostMapping
    public String procesarAccion(@RequestParam(required = false) String accion,
                                 @RequestParam(required = false) String codigo,
                                 @ModelAttribute Contrato contrato,
                                 Model model) {
        String mensaje = "";
        String tipoMensaje = "exito";

        try {
            String accionStr = (accion != null) ? accion : "";
            
            switch (accionStr) {
                case "crear":
                    contratoService.registrarContrato(contrato);
                    mensaje = "Contrato registrado exitosamente.";
                    model.addAttribute("contrato", new Contrato());
                    break;

                case "modificar":
                    contratoService.modificarContrato(contrato);
                    mensaje = "Contrato modificado exitosamente.";
                    model.addAttribute("contrato", new Contrato());
                    break;

                case "eliminar":
                    if (contrato.getIdContrato() != null) {
                        contratoService.removerContrato(contrato.getIdContrato());
                        mensaje = "Contrato eliminado exitosamente.";
                    } else {
                        mensaje = "Error: ID de contrato no especificado para eliminar.";
                        tipoMensaje = "error";
                    }
                    model.addAttribute("contrato", new Contrato());
                    break;

                case "buscar":
                    Contrato encontrado = contratoService.buscarPorCodigo(codigo);
                    if (encontrado != null) {
                        model.addAttribute("encargado", encontrado);
                        mensaje = "Contrato encontrado.";
                    } else {
                        mensaje = "No se encontró ningún contrato con el código: " + codigo;
                        tipoMensaje = "error";
                    }
                    model.addAttribute("contrato", contrato);
                    break;

                case "listar":
                    List<Contrato> lista = contratoService.listarContratos();
                    model.addAttribute("listaContratos", lista);
                    return "list/listaContratos";

                default:
                    mensaje = "Acción no reconocida.";
                    tipoMensaje = "error";
                    model.addAttribute("contrato", contrato);
                    break;
            }
        } catch (Exception e) {
            tipoMensaje = "error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            
            if (errStr.contains("duplicate") || errStr.contains("uk") || errStr.contains("constraint")) {
                if (errStr.contains("codigo")) {
                    mensaje = "Error: Ya existe un contrato registrado con el código: " + contrato.getCodigo();
                } else {
                    mensaje = "Error: Ya existe un registro con datos duplicados en el sistema.";
                }
            } else {
                mensaje = "Error inesperado: " + e.getMessage();
            }
            model.addAttribute("contrato", contrato);
        }

        model.addAttribute("listaUsuarios", usuarioService.listarUsuarios());
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("tipoMensaje", tipoMensaje);
        
        return "contratos";
    }
}