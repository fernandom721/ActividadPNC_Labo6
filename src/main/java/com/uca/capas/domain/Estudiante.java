package com.uca.capas.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity()
@Table(schema="public",name="estudiante")
public class Estudiante {

    @Id()
    @Column(name = "id_estudiante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoEstudiante;

    @Column(name = "nombre")
    @NotEmpty()
    @Size(message = "El campo no debe tener mas de 30 de caracteres", max = 30)
    private String nombre;

    @Column(name = "apellido")
    @Size(message = "El campo no debe tener mas de 30 de caracteres", max = 30)
    @NotEmpty()
    private String apellido;

    @Column(name = "edad")
    @NotNull(message = "El campo no puede estar vacio")
    @Min(value = 18, message = "No puede ser menor de 18 annios")
    private int edad;

    @Column(name = "estado")
    private Boolean estado;

    public Estudiante() { }

    public Integer getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Integer codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}