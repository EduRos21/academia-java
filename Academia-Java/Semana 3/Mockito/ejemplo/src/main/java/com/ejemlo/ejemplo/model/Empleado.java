package com.ejemlo.ejemplo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "empleados")
public class Empleado {

    @Id
    private String id;

    private String nombre;

    @Indexed(unique = true)
    private String correo;

    @Indexed(unique = true)
    private String telefono;

    private String sueldo;

    private LocalDateTime fechaRegistro;

    public Empleado() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public Empleado(String nombre, String correo, String telefono, String sueldo) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.sueldo = sueldo;
        this.fechaRegistro = LocalDateTime.now();

    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }
}
