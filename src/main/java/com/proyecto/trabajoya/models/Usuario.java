package com.proyecto.trabajoya.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_usuario")
    private int idUsuario;

    @Column (nullable = false, unique = false, length = 50)
    private String nombres;

    @Column (nullable = false, unique = false, length = 50)
    private String apellidos;

    @Column (nullable = false, unique = true, length = 12)
    private String documento;

    @Column (nullable = false, unique = true, length = 50)
    private String correo;

    @Column (nullable = false, unique = true, length = 10)
    private String celular;

    @Column (nullable = false, unique = false, length = 255)
    private String contraseña;

    @Column (nullable = false, unique = true, length = 10)
    private String nickName;

    //servicio
    @OneToMany(mappedBy = "usuario")
    private List<Servicio> servicios;

    //contrato
    @OneToMany(mappedBy = "usuario")
    private List<Contrato> contrato;

    public Usuario() {
    }

    public Usuario(String nombres, String apellidos, String documento, String correo, String celular, String contraseña,
            String nickName, List<Servicio> servicios, List<Contrato> contrato) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.correo = correo;
        this.celular = celular;
        this.contraseña = contraseña;
        this.nickName = nickName;
        this.servicios = servicios;
        this.contrato = contrato;
    }

    public Usuario(int idUsuario, String nombres, String apellidos, String documento, String correo, String celular,
            String contraseña, String nickName, List<Servicio> servicios, List<Contrato> contrato) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.correo = correo;
        this.celular = celular;
        this.contraseña = contraseña;
        this.nickName = nickName;
        this.servicios = servicios;
        this.contrato= contrato;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Contrato> getContrato() {
        return contrato;
    }

    public void setContrato(List<Contrato> contrato) {
        this.contrato = contrato;
    }
}
