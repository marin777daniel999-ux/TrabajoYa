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
import com.proyecto.trabajoya.services.Interfaces.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String verUsuarios(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios";
    }

    @PostMapping
    public String procesarAccion(@RequestParam(required = false) String accion,
                                 @RequestParam(required = false) String documento,
                                 @ModelAttribute Usuario usuario,
                                 Model model) {
        String mensaje = "";
        String tipoMensaje = "exito";

        try {
            switch (accion != null ? accion : "") {
                case "crear":
                    usuarioService.registrarUsuario(usuario);
                    mensaje = "Usuario registrado exitosamente.";
                    model.addAttribute("usuario", new Usuario());
                    break;

                case "modificar":
                    usuarioService.modificarUsuario(usuario);
                    mensaje = "Usuario modificado exitosamente.";
                    model.addAttribute("usuario", new Usuario());
                    break;

                case "eliminar":
                    if (usuario.getIdUsuario() != null) {
                        usuarioService.removerUsuario(usuario.getIdUsuario());
                        mensaje = "Usuario eliminado exitosamente.";
                    } else {
                        mensaje = "Error: ID de usuario no especificado para eliminar.";
                        tipoMensaje = "error";
                    }
                    model.addAttribute("usuario", new Usuario());
                    break;

                case "buscar":
                    Usuario encontrado = usuarioService.buscarPorDocumento(documento);
                    if (encontrado != null) {
                        model.addAttribute("encargado", encontrado);
                        mensaje = "Usuario encontrado.";
                    } else {
                        mensaje = "No se encontró ningún usuario con el documento: " + documento;
                        tipoMensaje = "error";
                    }
                    model.addAttribute("usuario", usuario);
                    break;

                case "listar":
                    List<Usuario> lista = usuarioService.listarUsuarios();
                    model.addAttribute("listaUsuarios", lista);
                    return "list/listaUsuarios";

                default:
                    mensaje = "Acción no reconocida.";
                    tipoMensaje = "error";
                    model.addAttribute("usuario", usuario);
                    break;
            }
        } catch (Exception e) {
            tipoMensaje = "error";
            String errStr = e.getMessage() != null ? e.getMessage().toLowerCase() : "";
            
            if (errStr.contains("duplicate") || errStr.contains("uk") || errStr.contains("constraint")) {
                if (errStr.contains("documento") || errStr.contains("uk51x567hg32si9nj9gjcbabcnm")) {
                    mensaje = "Error: Ya existe un usuario registrado con el documento " + usuario.getDocumento();
                } else if (errStr.contains("correo") || errStr.contains("ukcdmw5hxlfj78uf4997i3qyyw5")) {
                    mensaje = "Error: El correo electrónico ya se encuentra registrado.";
                } else if (errStr.contains("nick_name") || errStr.contains("uk3n22nyrldljogu9917bw2w64f")) {
                    mensaje = "Error: El apodo (nick name) ya está en uso.";
                } else {
                    mensaje = "Error: Ya existe un registro con datos duplicados en el sistema.";
                }
            } else {
                mensaje = "Error inesperado: " + e.getMessage();
            }
            model.addAttribute("usuario", usuario);
        }

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("tipoMensaje", tipoMensaje);
        return "usuarios";
    }
}