package com.proyecto.trabajoya.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "calificaciones")
public class Calificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Integer idCalificacion;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 10, message = "El código no puede superar los 10 caracteres")
    @Column(nullable = false, unique = true, length = 10)
    private String codigo;

    @NotNull(message = "El ID del usuario emisor es obligatorio")
    @Column(name = "id_usuario_emisor", nullable = false)
    private Integer idUsuarioEmisor;

    @NotNull(message = "El ID del usuario receptor es obligatorio")
    @Column(name = "id_usuario_receptor", nullable = false)
    private Integer idUsuarioReceptor;

    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    @Column(nullable = false)
    private Integer puntuacion; // Rango de 1 a 5

    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 250, message = "El comentario no puede superar los 250 caracteres")
    @Column(nullable = false, length = 250)
    private String comentario;
    
    // Evidencia fotográfica 
    @Column(nullable = true, length = 255)
    private String evidencia;

    // Relación Uno a Uno con el Contrato calificado
    @NotNull(message = "El contrato asociado es obligatorio")
    @OneToOne
    @JoinColumn(name = "id_contrato", nullable = false, unique = true)
    private Contrato contrato;

    public Calificacion() {
    }

    public Calificacion(String codigo, Integer idUsuarioEmisor, Integer idUsuarioReceptor,
            Integer puntuacion, String comentario, String evidencia, Contrato contrato) {
        this.codigo = codigo;
        this.idUsuarioEmisor = idUsuarioEmisor;
        this.idUsuarioReceptor = idUsuarioReceptor;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.evidencia = evidencia;
        this.contrato = contrato;
    }

    // Getters y Setters
    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdUsuarioEmisor() {
        return idUsuarioEmisor;
    }

    public void setIdUsuarioEmisor(Integer idUsuarioEmisor) {
        this.idUsuarioEmisor = idUsuarioEmisor;
    }

    public Integer getIdUsuarioReceptor() {
        return idUsuarioReceptor;
    }

    public void setIdUsuarioReceptor(Integer idUsuarioReceptor) {
        this.idUsuarioReceptor = idUsuarioReceptor;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}