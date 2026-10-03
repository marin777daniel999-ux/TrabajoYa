package com.proyecto.trabajoya.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "calificacion")
public class Calificacion {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_calificacion")
    private int idCalificacion;

    @Column (nullable = false)
    private String codigo;

    //esto No es una foranea, se extrae del contrato
    @Column (nullable = false)
    private Integer idUsuarioEmisor;

    //igual
    @Column (nullable = false)
    private Integer idUsuarioReceptor;

    @Column (nullable = false)
    private int puntuacion;

    @Column (nullable = false, length = 200)
    private String comentario;
    
    /* Esta en String porque podemos guardar imagenes 
        como una URL en la base de datos, tenemos que
        ver como es el proceso para pedir la imagen en los formularios.
        comeme el pene bro 
        que no eque es broma*/
    @Column (nullable = true, length = 255)
    private String evidencia;

    // Contracker
    @OneToOne
    @JoinColumn(name = "idContrato", nullable = false, unique = true)
    private Contrato contrato;

    public Calificacion() {
    }

    public Calificacion(int idCalificacion, String codigo, Integer idUsuarioEmisor,
            Integer idUsuarioReceptor, int puntuacion, String comentario, String evidencia, Contrato contrato) {
        this.idCalificacion = idCalificacion;
        this.codigo = codigo;
        this.idUsuarioEmisor = idUsuarioEmisor;
        this.idUsuarioReceptor = idUsuarioReceptor;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.evidencia = evidencia;
        this.contrato = contrato;
    }

    public Calificacion(String codigo, Integer idUsuarioEmisor, Integer idUsuarioReceptor,
            int puntuacion, String comentario, String evidencia, Contrato contrato) {
        this.codigo = codigo;
        this.idUsuarioEmisor = idUsuarioEmisor;
        this.idUsuarioReceptor = idUsuarioReceptor;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.evidencia = evidencia;
        this.contrato = contrato;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
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
