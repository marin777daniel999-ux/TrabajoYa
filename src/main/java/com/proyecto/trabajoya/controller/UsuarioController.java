package com.proyecto.trabajoya.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.trabajoya.models.Usuario;
import com.proyecto.trabajoya.services.Interfaces.IContratoService;
import com.proyecto.trabajoya.services.Interfaces.IServicioService;
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;
import com.proyecto.trabajoya.services.implement.UsuarioServiceImpl;

@Controller 
@RequestMapping ("/usuarios")
public class UsuarioController {
    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping 
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", new Usuario());
        return "usuarios";
    }

    @PostMapping
    public String procesarAccion(@RequestParam (required = false) String accion,
                                 @RequestParam (required = false) String documento,
                                 @ModelAttribute Usuario usuario,
                                 Model model) {
        String mensaje="";
        String tipoMensaje="exito";
        try {
            if ("crear".equals(accion)) {
                usuarioService.registrarUsuario(usuario);
                mensaje="usuario registrado";
                model.addAttribute("usuario", new Usuario());
            }else if("modificar".equals(accion)){
                usuarioService.modificarUsuario(usuario);
                mensaje="usuario modificado";
                model.addAttribute("usuario", new Usuario());
            }else if("eliminar".equals(accion)){
                usuarioService.removerUsuario(usuario.getIdUsuario());
                mensaje="usuario eliminado";
                model.addAttribute("usuario", new Usuario());
            }else if("buscar".equals(accion)){
                Usuario encontrado = usuarioService.buscarPorDocumento(documento);
                if(encontrado != null){
                    model.addAttribute("encargado", encontrado);
                    mensaje="usuario encontrado";
                }else{
                    mensaje="No se encontró usuario con documento: "+documento;
                    tipoMensaje="error";
                    model.addAttribute("usuario", usuario);
                }
            }else if("listar".equals(accion)){
                List<Usuario> lista=usuarioService.listarUsuarios();
                model.addAttribute("listaUsuarios", lista);
                return "list/listaUsuarios";
            }
        } catch (Exception e) {
            tipoMensaje="error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            if(errStr.contains("duplicate")){
                if(errStr.contains("documento")){
                    mensaje="Error, ya existe usuario con el documento:"+usuario.getDocumento();
                }
            }else{
                mensaje="Error, "+e.getMessage();
            }
            model.addAttribute("usuario", usuario);
        }
        model.addAttribute("mensaje",mensaje);
        model.addAttribute("tipoMensaje",tipoMensaje);
        return "usuarios";
    }
}
