package com.proyecto.trabajoya.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.trabajoya.models.Servicio;
import com.proyecto.trabajoya.repository.ServicioRepository;
import com.proyecto.trabajoya.services.Interfaces.IContratoService;
import com.proyecto.trabajoya.services.Interfaces.IServicioService;
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;

@Controller 
@RequestMapping ("/servicios")
public class ServicioController {
    private final IServicioService servicioService;
    private final IContratoService contratoService;
    private final IUsuarioService usuarioService;
    
    public ServicioController(IServicioService servicioService, IContratoService contratoService, IUsuarioService usuarioService) {
        this.servicioService = servicioService;
        this.contratoService = contratoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping 
    public String verServicios(Model model) {
        model.addAttribute("servicios", new Servicio());
        model.addAttribute("listaContratos", contratoService.listarContratos());
        return "servicios";
    }

    @PostMapping
    public String procesarAccion(@RequestParam (required = false) String accion,
                                 @RequestParam (required = false) String codigo,
                                 @ModelAttribute Servicio servicio,
                                 Model model) {
        String mensaje="";
        String tipoMensaje="exito";
        try {
            if ("crear".equals(accion)) {
                servicioService.registrarServicio(servicio);
                mensaje="servicio registrado";
                model.addAttribute("servicio", new Servicio());
            }else if("modificar".equals(accion)){
                servicioService.modificarServicio(servicio);
                mensaje="servicio modificado";
                model.addAttribute("servicio", new Servicio());
            }else if("eliminar".equals(accion)){
                servicioService.removerServicio(servicio.getIdServicio());
                mensaje="servicio eliminado";
                model.addAttribute("servicio", new Servicio());
            }else if("buscar".equals(accion)){
                Servicio encontrado = servicioService.buscarPorCodigo(codigo);
                if(encontrado != null){
                    model.addAttribute("encargado", encontrado);
                    mensaje="servicio encontrado";
                }else{
                    mensaje="No se encontró servicio con codigo: "+codigo;
                    tipoMensaje="error";
                    model.addAttribute("servicio", servicio);
                }
            }else if("listar".equals(accion)){
                List<Servicio> lista=servicioService.listarServicios();
                model.addAttribute("listaServicios", lista);
                return "list/listaServicios";
            }
        } catch (Exception e) {
            tipoMensaje="error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            if(errStr.contains("duplicate")){
                if(errStr.contains("codigo")){
                    mensaje="Error, ya existe servicio con el codigo:"+servicio.getCodigo();
                }
            }else{
                mensaje="Error, "+e.getMessage();
            }
            model.addAttribute("servicio", servicio);
        }
        model.addAttribute("listaContratos", contratoService.listarContratos());
        model.addAttribute("listaUsuarios",usuarioService.listarUsuarios());
        model.addAttribute("mensaje",mensaje);
        model.addAttribute("tipoMensaje",tipoMensaje);
        return "servicios";
    }

}
