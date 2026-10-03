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
import com.proyecto.trabajoya.repository.ContratoRepository;
import com.proyecto.trabajoya.services.Interfaces.IContratoService;
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;

@Controller 
@RequestMapping ("/contratos")
public class ContratoController {
    private final IContratoService contratoService;
    private final IUsuarioService usuarioService;
    
     public ContratoController(IContratoService contratoService, IUsuarioService usuarioService) {
        this.contratoService = contratoService;
        this.usuarioService = usuarioService;
    }

     @GetMapping 
    public String verContratos(Model model) {
        model.addAttribute("contratos", new Contrato());
        model.addAttribute("listaUsuarios", usuarioService.listarUsuarios());
        return "contratos";
    }

    @PostMapping
    public String procesarAccion(@RequestParam (required = false) String accion,
                                 @RequestParam (required = false) String codigo,
                                 @ModelAttribute Contrato contrato,
                                 Model model) {
        String mensaje="";
        String tipoMensaje="exito";
        try {
            if ("crear".equals(accion)) {
                contratoService.registrarContrato(contrato);
                mensaje="contrato registrado";
                model.addAttribute("contrato", new Contrato());
            }else if("modificar".equals(accion)){
                contratoService.modificarContrato(contrato);
                mensaje="contrato modificado";
                model.addAttribute("contrato", new Contrato());
            }else if("eliminar".equals(accion)){
                contratoService.removerContrato(contrato.getIdContrato());
                mensaje="contrato eliminado";
                model.addAttribute("contrato", new Contrato());
            }else if("buscar".equals(accion)){
                Contrato encontrado = contratoService.buscarPorCodigo(codigo);
                if(encontrado != null){
                    model.addAttribute("encargado", encontrado);
                    mensaje="contrato encontrado";
                }else{
                    mensaje="No se encontró contrato con codigo: "+codigo;
                    tipoMensaje="error";
                    model.addAttribute("contrato", contrato);
                }
            }else if("listar".equals(accion)){
                List<Contrato> lista=contratoService.listarContratos();
                model.addAttribute("listaContratos", lista);
                return "list/listaContratos";
            }
        } catch (Exception e) {
            tipoMensaje="error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            if(errStr.contains("duplicate")){
                if(errStr.contains("codigo")){
                    mensaje="Error, ya existe contrato con el codigo:"+contrato.getCodigo();
                }
            }else{
                mensaje="Error, "+e.getMessage();
            }
            model.addAttribute("contrato", contrato);
        }
        model.addAttribute("listaUsuarios",usuarioService.listarUsuarios());
        model.addAttribute("mensaje",mensaje);
        model.addAttribute("tipoMensaje",tipoMensaje);
        return "contratos";
    }

}
