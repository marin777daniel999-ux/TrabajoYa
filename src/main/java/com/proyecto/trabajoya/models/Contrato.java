package com.proyecto.trabajoya.models;

import java.sql.Time;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "contrato")
public class Contrato {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_contrato")
    private int idContrato;

    @Column (nullable = false, unique = true, length = 6)
    private String codigo;

    @Column (nullable = false)
    private Time horaContrato;

    @Column (nullable = false, length = 50)
    private String lugar;

    @Column (nullable = false)
    private double precioNegociable;

    @Column (nullable = false)
    private LocalDate fechaContrato;

    @Column (nullable = false)
    private LocalDate fechaFin;

    @Column (nullable = false)
    private Boolean estado;

    // servicio (at)
    @ManyToOne
    @JoinColumn(name = "idTrabajo", nullable = false)
    private Servicio servicio;

    //usuario (at)
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    // calificacion
    @OneToOne(mappedBy = "contrato")
    private Calificacion calificacion;

    public Contrato() {
    }

    public Contrato(String codigo, Time horaContrato, String lugar, double precioNegociable, LocalDate fechaContrato,
            LocalDate fechaFin, Boolean estado, Servicio servicio, Usuario usuario) {
        this.codigo = codigo;
        this.horaContrato = horaContrato;
        this.lugar = lugar;
        this.precioNegociable = precioNegociable;
        this.fechaContrato = fechaContrato;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.servicio = servicio;
        this.usuario = usuario;
    }

    public Contrato(int idContrato, String codigo, Time horaContrato, String lugar, double precioNegociable,
            LocalDate fechaContrato, LocalDate fechaFin, Boolean estado, Servicio servicio, Usuario usuario) {
        this.idContrato = idContrato;
        this.codigo = codigo;
        this.horaContrato = horaContrato;
        this.lugar = lugar;
        this.precioNegociable = precioNegociable;
        this.fechaContrato = fechaContrato;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.servicio = servicio;
        this.usuario = usuario;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Time getHoraContrato() {
        return horaContrato;
    }

    public void setHoraContrato(Time horaContrato) {
        this.horaContrato = horaContrato;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getPrecioNegociable() {
        return precioNegociable;
    }

    public void setPrecioNegociable(double precioNegociable) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }
}
