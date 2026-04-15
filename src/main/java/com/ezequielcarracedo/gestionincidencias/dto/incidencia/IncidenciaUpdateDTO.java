package com.ezequielcarracedo.gestionincidencias.dto.incidencia;

import com.ezequielcarracedo.gestionincidencias.model.EstatIncidencia;

public class IncidenciaUpdateDTO {
    private String descripcion;
    private EstatIncidencia estado;



    public EstatIncidencia getEstado() {
        return this.estado;
    }

    public String getDescripcion() {
        return this.descripcion;
    }



    public void setEstado(EstatIncidencia estado) {
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}