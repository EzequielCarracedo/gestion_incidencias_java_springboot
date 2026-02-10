package com.ezequielcarracedo.gestionincidencias.model;

public class Incidencia {
    private int id;
    private String descripcion;
    private Usuario user;
    private EstatIncidencia estado;

    // BUILDER

    public Incidencia() {

    }

    public Incidencia(int id, String descripcion, Usuario user) {

        this.id = id;
        this.descripcion = descripcion;
        this.user = user;
        this.estado = EstatIncidencia.ABIERTA;

        if (this.descripcion == null || this.descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia.");
        }
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

    // CAMBIAR ESTADO
    public boolean cambiarEstado(EstatIncidencia nuevoEstado) {

        if (estado.equals(EstatIncidencia.ABIERTA) && nuevoEstado.equals(EstatIncidencia.EN_PROCESO)) {
            this.estado = EstatIncidencia.EN_PROCESO;
           
            return true;
        } else if (estado.equals(EstatIncidencia.EN_PROCESO) && nuevoEstado.equals(EstatIncidencia.CERRADA)) {
            this.estado = EstatIncidencia.CERRADA;
           
            return true;
        } else {
            //throw new IllegalArgumentException("NO SE PUEDE CAMBIAR A " + nuevoEstado + " DIRECTAMENTE.\n");
            return false;
        }
    }

    public String imprimirIncidencia() {
        return "ID INCIDENCIA: " + this.getId() + "\nDESCRIPCION: " + this.descripcion + "\nID USUARIO: "
                + this.user.id() + "\nNOMBRE: " + this.user.nom();
    }

}