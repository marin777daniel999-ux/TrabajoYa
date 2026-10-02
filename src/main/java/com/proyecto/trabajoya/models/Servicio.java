package com.proyecto.trabajoya.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "servicios")
public class Servicio {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_servicio")
    private int idServicio;

    @Column (nullable = false, length = 6)
    private String codigo;

    @Column (nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column (nullable = false)
    private boolean experiencia;

    @Column (nullable = false, length = 255)
    private String evidencia;

    @Column (nullable = false)
    private double precioInicial;

    @Column (nullable = false)
    private boolean estado;

    @Column (nullable = false)
    private Date fechaExpiracion;

    //Usuario (at)
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    //CategoriaServicios (at)
    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaServicio categoria;

    // Relación Uno a Muchos con Contrato
    @OneToMany(mappedBy = "servicio")
    private List<Contrato> contrato;

    public Servicio() {
    }

    public Servicio(int idServicio, String codigo, String nombre, String descripcion, boolean experiencia,
            String evidencia, double precioInicial, boolean estado, Date fechaExpiracion, Usuario usuario,
            CategoriaServicio categoria, List<Contrato> contrato) {
        this.idServicio = idServicio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.experiencia = experiencia;
        this.evidencia = evidencia;
        this.precioInicial = precioInicial;
        this.estado = estado;
        this.fechaExpiracion = fechaExpiracion;
        this.usuario = usuario;
        this.categoria = categoria;
        this.contrato = contrato;
    }

    public Servicio(String codigo, String nombre, String descripcion, boolean experiencia, String evidencia,
            double precioInicial, boolean estado, Date fechaExpiracion, Usuario usuario, CategoriaServicio categoria,
            List<Contrato> contrato) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.experiencia = experiencia;
        this.evidencia = evidencia;
        this.precioInicial = precioInicial;
        this.estado = estado;
        this.fechaExpiracion = fechaExpiracion;
        this.usuario = usuario;
        this.categoria = categoria;
        this.contrato = contrato;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isExperiencia() {
        return experiencia;
    }

    public void setExperiencia(boolean experiencia) {
        this.experiencia = experiencia;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CategoriaServicio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaServicio categoria) {
        this.categoria = categoria;
    }

    public List<Contrato> getContrato() {
        return contrato;
    }

    public void setContrato(List<Contrato> contrato) {
        this.contrato = contrato;
    }
}
