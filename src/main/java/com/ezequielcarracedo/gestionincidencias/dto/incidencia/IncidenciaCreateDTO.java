package com.ezequielcarracedo.gestionincidencias.dto.incidencia;



public class IncidenciaCreateDTO {
    private String descripcion;
    private int userId;


    public String getDescripcion() {
        return this.descripcion;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

}