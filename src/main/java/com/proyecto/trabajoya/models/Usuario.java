package com.proyecto.trabajoya.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 50, message = "Los nombres no pueden superar los 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 50, message = "Los apellidos no pueden superar los 50 caracteres")
    @Column(nullable = false, length = 50)
    private String apellidos;

    @NotBlank(message = "El documento es obligatorio")
    @Size(max = 15, message = "El documento no puede superar los 15 caracteres")
    @Column(nullable = false, unique = true, length = 15)
    private String documento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @NotBlank(message = "El celular es obligatorio")
    @Size(min = 10, max = 15, message = "El celular debe tener entre 10 y 15 dígitos")
    @Column(nullable = false, unique = true, length = 15)
    private String celular;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false, length = 255)
    private String contraseña;

    @NotBlank(message = "El apodo o nickname es obligatorio")
    @Size(max = 20, message = "El nickName no puede superar los 20 caracteres")
    @Column(nullable = false, unique = true, length = 20)
    private String nickName;

    // Rol para diferenciar entre cliente y prestador de servicios
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol; // CLIENTE, PRESTADOR

    // Servicios publicados por el usuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    // Contratos asociados al usuario
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Contrato> contratosComoCliente;

    @OneToMany(mappedBy = "prestador", cascade = CascadeType.ALL)
    private List<Contrato> contratosComoPrestador;

    // para los roles del sistema
    public enum Rol {
        CLIENTE,
        PRESTADOR,
        ADMIN
    }

    public Usuario() {
    }

    public Usuario(String nombres, String apellidos, String documento, String correo, String celular, String contraseña,
            String nickName, Rol rol) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.correo = correo;
        this.celular = celular;
        this.contraseña = contraseña;
        this.nickName = nickName;
        this.rol = rol;
    }

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Contrato> getContratosComoCliente() {
        return contratosComoCliente;
    }

    public void setContratosComoCliente(List<Contrato> contratosComoCliente) {
        this.contratosComoCliente = contratosComoCliente;
    }

    public List<Contrato> getContratosComoPrestador() {
        return contratosComoPrestador;
    }

    public void setContratosComoPrestador(List<Contrato> contratosComoPrestador) {
        this.contratosComoPrestador = contratosComoPrestador;
    }
}