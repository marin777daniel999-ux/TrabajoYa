package com.proyecto.trabajoya.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.trabajoya.models.CategoriaServicio;
import com.proyecto.trabajoya.services.Interfaces.ICategoriaServicioService;
import com.proyecto.trabajoya.services.Interfaces.IServicioService;

@Controller 
@RequestMapping("/categorias")
public class CategoriaServicioController {
    
    private final ICategoriaServicioService categoriaServicioService;
    private final IServicioService servicioService;

    public CategoriaServicioController(ICategoriaServicioService categoriaServicioService, 
                                       IServicioService servicioService) {
        this.categoriaServicioService = categoriaServicioService;
        this.servicioService = servicioService;
    }

    @GetMapping 
    public String verCategorias(Model model) {
        model.addAttribute("categoriaServicio", new CategoriaServicio());
        model.addAttribute("listaServicios", servicioService.listarServicios());
        return "categorias";
    }

    @PostMapping
    public String procesarAccion(@RequestParam(required = false) String accion,
                                 @RequestParam(required = false) String codigo,
                                 @ModelAttribute CategoriaServicio categoriaServicio,
                                 Model model) {
        String mensaje = "";
        String tipoMensaje = "exito";

        try {
            String accionStr = (accion != null) ? accion : "";
            
            switch (accionStr) {
                case "crear":
                    categoriaServicioService.registrarCategoriaServicio(categoriaServicio);
                    mensaje = "Categoría registrada exitosamente.";
                    model.addAttribute("categoriaServicio", new CategoriaServicio());
                    break;

                case "modificar":
                    categoriaServicioService.modificarCategoriaServicio(categoriaServicio);
                    mensaje = "Categoría modificada exitosamente.";
                    model.addAttribute("categoriaServicio", new CategoriaServicio());
                    break;

                case "eliminar":
                    if (categoriaServicio.getIdCategoriaServicio() != null) {
                        categoriaServicioService.removerCategoriaServicio(categoriaServicio.getIdCategoriaServicio());
                        mensaje = "Categoría eliminada exitosamente.";
                    } else {
                        mensaje = "Error: ID de categoría no especificado para eliminar.";
                        tipoMensaje = "error";
                    }
                    model.addAttribute("categoriaServicio", new CategoriaServicio());
                    break;

                case "buscar":
                    CategoriaServicio encontrado = categoriaServicioService.buscarPorCodigo(codigo);
                    if (encontrado != null) {
                        model.addAttribute("encargado", encontrado);
                        mensaje = "Categoría encontrada.";
                    } else {
                        mensaje = "No se encontró ninguna categoría con el código: " + codigo;
                        tipoMensaje = "error";
                    }
                    model.addAttribute("categoriaServicio", categoriaServicio);
                    break;

                case "listar":
                    List<CategoriaServicio> lista = categoriaServicioService.listarCategoriaServicios();
                    model.addAttribute("listaCategoriaServicios", lista);
                    return "list/listaCategoriaServicios";

                default:
                    mensaje = "Acción no reconocida.";
                    tipoMensaje = "error";
                    model.addAttribute("categoriaServicio", categoriaServicio);
                    break;
            }
        } catch (Exception e) {
            tipoMensaje = "error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            
            if (errStr.contains("duplicate") || errStr.contains("uk") || errStr.contains("constraint")) {
                if (errStr.contains("codigo")) {
                    mensaje = "Error: Ya existe una categoría registrada con el código: " + categoriaServicio.getCodigo();
                } else {
                    mensaje = "Error: Ya existe un registro con datos duplicados en el sistema.";
                }
            } else {
                mensaje = "Error inesperado: " + e.getMessage();
            }
            model.addAttribute("categoriaServicio", categoriaServicio);
        }

        model.addAttribute("listaServicios", servicioService.listarServicios());
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("tipoMensaje", tipoMensaje);
        
        return "categorias";
    }
}