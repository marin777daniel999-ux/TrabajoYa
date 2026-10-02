package com.proyecto.trabajoya.models;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "categoria_servicios")
public class CategoriaServicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_servicio")
    private int idCategoriaServicio;
    
    //Para manejar mejor los repositorios, le puse un código aquí y en los demas modelos que lo debian llevar
    @Column (nullable = false, length = 6)
    private String codigo;

    @Column (nullable = false, length = 50)
    private String nombre;

    @Column (nullable = false, length = 200)
    private String descripcion;

    //servicio
    @OneToMany(mappedBy = "categoria")
    private List<Servicio> servicio;

    public CategoriaServicio() {
    }

    public CategoriaServicio(int idCategoriaServicio, String codigo, String nombre, String descripcion,
            List<Servicio> servicio) {
        this.idCategoriaServicio = idCategoriaServicio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.servicio = servicio;
    }

    public CategoriaServicio(String codigo, String nombre, String descripcion, List<Servicio> servicio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.servicio = servicio;
    }

    public int getIdCategoriaServicio() {
        return idCategoriaServicio;
    }

    public void setIdCategoriaServicio(int idCategoriaServicio) {
        this.idCategoriaServicio = idCategoriaServicio;
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

    public List<Servicio> getServicio() {
        return servicio;
    }

    public void setServicio(List<Servicio> servicio) {
        this.servicio = servicio;
    }
}
