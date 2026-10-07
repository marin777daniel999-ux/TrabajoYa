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
import com.proyecto.trabajoya.services.Interfaces.IContratoService;
import com.proyecto.trabajoya.services.Interfaces.IServicioService;
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;

@Controller 
@RequestMapping("/servicios")
public class ServicioController {
    
    private final IServicioService servicioService;
    private final IContratoService contratoService;
    private final IUsuarioService usuarioService;
    
    public ServicioController(IServicioService servicioService, 
                              IContratoService contratoService, 
                              IUsuarioService usuarioService) {
        this.servicioService = servicioService;
        this.contratoService = contratoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping 
    public String verServicios(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("listaContratos", contratoService.listarContratos());
        model.addAttribute("listaUsuarios", usuarioService.listarUsuarios());
        return "servicios";
    }

    @PostMapping
    public String procesarAccion(@RequestParam(required = false) String accion,
                                 @RequestParam(required = false) String codigo,
                                 @ModelAttribute Servicio servicio,
                                 Model model) {
        String mensaje = "";
        String tipoMensaje = "exito";

        try {
            String accionStr = (accion != null) ? accion : "";
            
            if ("crear".equals(accionStr)) {
                servicioService.registrarServicio(servicio);
                mensaje = "Servicio registrado exitosamente.";
                model.addAttribute("servicio", new Servicio());
                
            } else if ("modificar".equals(accionStr)) {
                servicioService.modificarServicio(servicio);
                mensaje = "Servicio modificado exitosamente.";
                model.addAttribute("servicio", new Servicio());
                
            } else if ("eliminar".equals(accionStr)) {
                if (servicio.getIdServicio() != null) {
                    servicioService.removerServicio(servicio.getIdServicio());
                    mensaje = "Servicio eliminado exitosamente.";
                } else {
                    mensaje = "Error: ID de servicio no especificado para eliminar.";
                    tipoMensaje = "error";
                }
                model.addAttribute("servicio", new Servicio());
                
            } else if ("buscar".equals(accionStr)) {
                Servicio encontrado = servicioService.buscarPorCodigo(codigo);
                if (encontrado != null) {
                    model.addAttribute("encargado", encontrado);
                    mensaje = "Servicio encontrado.";
                } else {
                    mensaje = "No se encontró ningún servicio con el código: " + codigo;
                    tipoMensaje = "error";
                }
                model.addAttribute("servicio", servicio);
                
            } else if ("listar".equals(accionStr)) {
                List<Servicio> lista = servicioService.listarServicios();
                model.addAttribute("listaServicios", lista);
                return "list/listaServicios";
                
            } else {
                mensaje = "Acción no reconocida.";
                tipoMensaje = "error";
                model.addAttribute("servicio", servicio);
            }
            
        } catch (Exception e) {
            tipoMensaje = "error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            
            if (errStr.contains("duplicate") || errStr.contains("uk") || errStr.contains("constraint")) {
                if (errStr.contains("codigo")) {
                    mensaje = "Error: Ya existe un servicio registrado con el código: " + servicio.getCodigo();
                } else {
                    mensaje = "Error: Ya existe un registro con datos duplicados en el sistema.";
                }
            } else {
                mensaje = "Error inesperado: " + e.getMessage();
            }
            model.addAttribute("servicio", servicio);
        }

        model.addAttribute("listaContratos", contratoService.listarContratos());
        model.addAttribute("listaUsuarios", usuarioService.listarUsuarios());
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("tipoMensaje", tipoMensaje);
        
        return "servicios";
    }
}