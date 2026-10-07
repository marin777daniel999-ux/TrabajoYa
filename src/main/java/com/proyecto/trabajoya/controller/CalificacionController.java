package com.proyecto.trabajoya.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.trabajoya.models.Calificacion;
import com.proyecto.trabajoya.services.Interfaces.ICalificacionService;

@Controller 
@RequestMapping("/calificaciones")
public class CalificacionController {
    
    private final ICalificacionService calificacionService;

    public CalificacionController(ICalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping 
    public String verCalificacion(Model model) {
        model.addAttribute("calificacion", new Calificacion());
        return "calificaciones";
    }

    @PostMapping
    public String procesarAccion(@RequestParam(required = false) String accion,
                                 @RequestParam(required = false) String codigo,
                                 @ModelAttribute Calificacion calificacion,
                                 Model model) {
        String mensaje = "";
        String tipoMensaje = "exito";

        try {
            String accionStr = (accion != null) ? accion : "";
            
            switch (accionStr) {
                case "crear":
                    calificacionService.registrarCalificacion(calificacion);
                    mensaje = "Calificación registrada exitosamente.";
                    model.addAttribute("calificacion", new Calificacion());
                    break;

                case "modificar":
                    calificacionService.modificarCalificacion(calificacion);
                    mensaje = "Calificación modificada exitosamente.";
                    model.addAttribute("calificacion", new Calificacion());
                    break;

                case "eliminar":
                    if (calificacion.getIdCalificacion() != null) {
                        calificacionService.removerCalificacion(calificacion.getIdCalificacion());
                        mensaje = "Calificación eliminada exitosamente.";
                    } else {
                        mensaje = "Error: ID de calificación no especificado para eliminar.";
                        tipoMensaje = "error";
                    }
                    model.addAttribute("calificacion", new Calificacion());
                    break;

                case "buscar":
                    Calificacion encontrado = calificacionService.buscarPorCodigo(codigo);
                    if (encontrado != null) {
                        model.addAttribute("encargado", encontrado);
                        mensaje = "Calificación encontrada.";
                    } else {
                        mensaje = "No se encontró ninguna calificación con el código: " + codigo;
                        tipoMensaje = "error";
                    }
                    model.addAttribute("calificacion", calificacion);
                    break;

                case "listar":
                    List<Calificacion> lista = calificacionService.listarCalifiaciones();
                    model.addAttribute("listaCalificaciones", lista);
                    return "list/listaCalificaciones";

                default:
                    mensaje = "Acción no reconocida.";
                    tipoMensaje = "error";
                    model.addAttribute("calificacion", calificacion);
                    break;
            }
        } catch (Exception e) {
            tipoMensaje = "error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            
            if (errStr.contains("duplicate") || errStr.contains("uk") || errStr.contains("constraint")) {
                if (errStr.contains("codigo")) {
                    mensaje = "Error: Ya existe una calificación registrada con el código: " + calificacion.getCodigo();
                } else {
                    mensaje = "Error: Ya existe un registro con datos duplicados en el sistema.";
                }
            } else {
                mensaje = "Error inesperado: " + e.getMessage();
            }
            model.addAttribute("calificacion", calificacion);
        }

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("tipoMensaje", tipoMensaje);
        
        return "calificaciones";
    }
}