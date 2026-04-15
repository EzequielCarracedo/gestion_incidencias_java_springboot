package com.ezequielcarracedo.gestionincidencias.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Incidencia {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank(message = "LA DESCRIPCION ES OBLIGATORIA")
    @Size(min = 1, max = 100, message = "LA DESCRIPCION ES MUY LARGA, MAXIMO 100 CARACTERES")
    private String descripcion;

    @NotNull(message = "USUARIO NO PUEDE ESTAR VACIO")
    private Usuario user;

    private EstatIncidencia estado;

    // BUILDER
    public Incidencia() {
        this.estado = EstatIncidencia.ABIERTA;
    }

    public Incidencia(String descripcion, Usuario user) {

        this.descripcion = descripcion;

        this.user = user;

        this.estado = EstatIncidencia.ABIERTA;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Usuario getUser() {
        return user;
    }

    public EstatIncidencia getEstado() {
        return estado;
    }

    // SETTER

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        if (this.descripcion == null || this.descripcion.isBlank()) { // Validación
            throw new IllegalArgumentException("La descripcion no puede estar vacia.");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setEstado(EstatIncidencia nuevoEstado) {
        if (this.estado == EstatIncidencia.ABIERTA && nuevoEstado == EstatIncidencia.EN_PROCESO) {
            this.estado = EstatIncidencia.EN_PROCESO;

        } else if (this.estado == EstatIncidencia.EN_PROCESO && nuevoEstado == EstatIncidencia.CERRADA) {
            this.estado = EstatIncidencia.CERRADA;

        } else if (this.estado == EstatIncidencia.ABIERTA && nuevoEstado == EstatIncidencia.ABIERTA) {
            this.estado = EstatIncidencia.ABIERTA;
        } else {
            throw new IllegalArgumentException("NO SE PUEDE CAMBIAR A " + nuevoEstado + " DIRECTAMENTE.\n");
        }

    }

    public String imprimirIncidencia() {
        return "ID INCIDENCIA: " + this.getId() + "\nDESCRIPCION: " + this.getDescripcion() + "\nID USUARIO: "
                + this.user.getId() + "\nNOMBRE: " + this.getUser().getNom();
    }

}