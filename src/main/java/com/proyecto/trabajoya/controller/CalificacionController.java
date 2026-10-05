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
@RequestMapping ("/calificaciones")
public class CalificacionController {
    private final ICalificacionService calificacionService;

     public CalificacionController(ICalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

     @GetMapping 
    public String verCalificacion(Model model) {
        model.addAttribute("calificaciones", new Calificacion());
        return "calificaciones";
    }

    @PostMapping
    public String procesarAccion(@RequestParam (required = false) String accion,
                                 @RequestParam (required = false) String codigo,
                                 @ModelAttribute Calificacion calificacion,
                                 Model model) {
        String mensaje="";
        String tipoMensaje="exito";
        try {
            if ("crear".equals(accion)) {
                calificacionService.registrarCalificacion(calificacion);
                mensaje="califiacion registrada";
                model.addAttribute("calificacion", new Calificacion());
            }else if("modificar".equals(accion)){
                calificacionService.modificarCalificacion(calificacion);
                mensaje="calificacion modificada";
                model.addAttribute("calificacion", new Calificacion());
            }else if("eliminar".equals(accion)){
                calificacionService.removerCalificacion(calificacion.getIdCalificacion());
                mensaje="calificacion eliminada";
                model.addAttribute("calificacion", new Calificacion());
            }else if("buscar".equals(accion)){
                Calificacion encontrado = calificacionService.buscarPorCodigo(codigo);
                if(encontrado != null){
                    model.addAttribute("encargado", encontrado);
                    mensaje="calificacion encontrada";
                }else{
                    mensaje="No se encontró calificacion con codigo: "+codigo;
                    tipoMensaje="error";
                    model.addAttribute("calificacion", calificacion);
                }
            }else if("listar".equals(accion)){
                List<Calificacion> lista=calificacionService.listarCalifiaciones();
                model.addAttribute("listaCalificaciones", lista);
                return "list/listaCalificaciones";
            }
        } catch (Exception e) {
            tipoMensaje="error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            if(errStr.contains("duplicate")){
                if(errStr.contains("codigo")){
                    mensaje="Error, ya existe calificacion con el codigo:"+calificacion.getCodigo();
                }
            }else{
                mensaje="Error, "+e.getMessage();
            }
            model.addAttribute("calificacion", calificacion);
        }
        model.addAttribute("mensaje",mensaje);
        model.addAttribute("tipoMensaje",tipoMensaje);
        return "calificaciones";
    }

}
