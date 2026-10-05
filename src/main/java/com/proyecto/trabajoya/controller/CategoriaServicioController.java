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
@RequestMapping ("/categorias")
public class CategoriaServicioController {
    private final ICategoriaServicioService categoriaServicioService;
    private final IServicioService servicioService;

     public CategoriaServicioController(ICategoriaServicioService categoriaServicioService, IServicioService servicioService) {
        this.categoriaServicioService = categoriaServicioService;
        this.servicioService = servicioService;
    }

     @GetMapping 
    public String verCategorias(Model model) {
        model.addAttribute("categorias", new CategoriaServicio());
        model.addAttribute("listaServicios", servicioService.listarServicios());
        return "categorias";
    }

    @PostMapping
    public String procesarAccion(@RequestParam (required = false) String accion,
                                 @RequestParam (required = false) String codigo,
                                 @ModelAttribute CategoriaServicio categoriaServicio,
                                 Model model) {
        String mensaje="";
        String tipoMensaje="exito";
        try {
            if ("crear".equals(accion)) {
                categoriaServicioService.registrarCategoriaServicio(categoriaServicio);
                mensaje="categoria registrada";
                model.addAttribute("categoriaServicio", new CategoriaServicio());
            }else if("modificar".equals(accion)){
                categoriaServicioService.modificarCategoriaServicio(categoriaServicio);
                mensaje="categoria modificada";
                model.addAttribute("categoriaServicio", new CategoriaServicio());
            }else if("eliminar".equals(accion)){
                categoriaServicioService.removerCategoriaServicio(categoriaServicio.getIdCategoriaServicio());
                mensaje="categoriao eliminada";
                model.addAttribute("categoriaServicio", new CategoriaServicio());
            }else if("buscar".equals(accion)){
                CategoriaServicio encontrado = categoriaServicioService.buscarPorCodigo(codigo);
                if(encontrado != null){
                    model.addAttribute("encargado", encontrado);
                    mensaje="categoria encontrada";
                }else{
                    mensaje="No se encontró categoriaServicio con codigo: "+codigo;
                    tipoMensaje="error";
                    model.addAttribute("categoriaServicio", categoriaServicio);
                }
            }else if("listar".equals(accion)){
                List<CategoriaServicio> lista=categoriaServicioService.listarCategoriaServicios();
                model.addAttribute("listaCategoriaServicios", lista);
                return "list/listaCategoriaServicios";
            }
        } catch (Exception e) {
            tipoMensaje="error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            if(errStr.contains("duplicate")){
                if(errStr.contains("codigo")){
                    mensaje="Error, ya existe categoria con el codigo:"+categoriaServicio.getCodigo();
                }
            }else{
                mensaje="Error, "+e.getMessage();
            }
            model.addAttribute("categoriaServicio", categoriaServicio);
        }
        model.addAttribute("listaServicios", servicioService.listarServicios());
        model.addAttribute("mensaje",mensaje);
        model.addAttribute("tipoMensaje",tipoMensaje);
        return "categoriaServicios";
    }

}
