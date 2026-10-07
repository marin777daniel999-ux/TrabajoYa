package com.proyecto.trabajoya.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contratos")
public class Contrato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Integer idContrato;

    @NotBlank(message = "El código del contrato es obligatorio")
    @Size(max = 10, message = "El código no puede superar los 10 caracteres")
    @Column(nullable = false, unique = true, length = 10)
    private String codigo;

    @NotNull(message = "La hora del contrato es obligatoria")
    @Column(nullable = false)
    private LocalTime horaContrato;

    @NotBlank(message = "El lugar es obligatorio")
    @Size(max = 100, message = "El lugar no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String lugar;

    @NotNull(message = "El precio negociable es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a cero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioNegociable;

    @NotNull(message = "La fecha del contrato es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaContrato;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private Boolean estado = true; // True: Activo/En curso, False: Finalizado

    // Relación con el Servicio contratado
    @NotNull(message = "El servicio es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;

    // Relación con el Cliente (Usuario que contrata)
    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    // Relación con el Prestador (Trabajador que realiza el servicio)
    @NotNull(message = "El prestador es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_prestador", nullable = false)
    private Usuario prestador;

    // Calificación asociada al contrato 
    @OneToOne(mappedBy = "contrato", cascade = CascadeType.ALL)
    private Calificacion calificacion;

    public Contrato() {
    }

    public Contrato(String codigo, LocalTime horaContrato, String lugar, BigDecimal precioNegociable, 
            LocalDate fechaContrato, LocalDate fechaFin, Boolean estado, Servicio servicio, Usuario cliente, Usuario prestador) {
        this.codigo = codigo;
        this.horaContrato = horaContrato;
        this.lugar = lugar;
        this.precioNegociable = precioNegociable;
        this.fechaContrato = fechaContrato;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.servicio = servicio;
        this.cliente = cliente;
        this.prestador = prestador;
    }


    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalTime getHoraContrato() {
        return horaContrato;
    }

    public void setHoraContrato(LocalTime horaContrato) {
        this.horaContrato = horaContrato;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public BigDecimal getPrecioNegociable() {
        return precioNegociable;
    }

    public void setPrecioNegociable(BigDecimal precioNegociable) {
        this.precioNegociable = precioNegociable;
    }

    public LocalDate getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(LocalDate fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getPrestador() {
        return prestador;
    }

    public void setPrestador(Usuario prestador) {
        this.prestador = prestador;
    }

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }
}