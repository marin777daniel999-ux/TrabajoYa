package com.proyecto.trabajoya.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "servicios")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    @NotBlank(message = "El código del servicio es obligatorio")
    @Size(max = 10, message = "El código no puede superar los 10 caracteres")
    @Column(nullable = false, unique = true, length = 10)
    private String codigo;

    @NotBlank(message = "El nombre del servicio es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(nullable = false, length = 500)
    private String descripcion;

    @NotNull(message = "Debe indicar si cuenta con experiencia")
    @Column(nullable = false)
    private boolean experiencia;

    @Column(length = 255)
    private String evidencia; // URL o ruta de la foto/evidencia

    @NotNull(message = "El precio inicial es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio inicial debe ser mayor a cero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioInicial;

    @Column(nullable = false)
    private boolean estado = true; // Por defecto activo

    @NotNull(message = "La fecha de expiración es obligatoria")
    @FutureOrPresent(message = "La fecha de expiración debe ser actual o futura")
    @Column(nullable = false)
    private LocalDate fechaExpiracion;

    // Relación con el Usuario (Prestador que ofrece el servicio)
    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Relación con la Categoría del Servicio
    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaServicio categoria;

    // Relación Uno a Muchos con Contratos
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<Contrato> contratos;

    public Servicio() {
    }

    public Servicio(String codigo, String nombre, String descripcion, boolean experiencia, String evidencia,
            BigDecimal precioInicial, boolean estado, LocalDate fechaExpiracion, Usuario usuario, CategoriaServicio categoria) {
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
    }

    // Getters y Setters
    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
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

    public BigDecimal getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(BigDecimal precioInicial) {
        this.precioInicial = precioInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
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

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}