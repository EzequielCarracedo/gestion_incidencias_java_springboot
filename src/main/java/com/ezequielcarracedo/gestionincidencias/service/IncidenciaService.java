package com.ezequielcarracedo.gestionincidencias.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ezequielcarracedo.gestionincidencias.model.Incidencia;
import com.ezequielcarracedo.gestionincidencias.model.Usuario;

@Service
public class IncidenciaService {

    private List<Incidencia> llistatIncidencies;

    public IncidenciaService() {
       this.llistatIncidencies = new ArrayList<>();
    }

    public Incidencia crearIncidencia(Incidencia incidencia, Usuario usuario) {

        incidencia.setId(100);
        incidencia.setUser(usuario);
        llistatIncidencies.add(incidencia);
        return incidencia;

    }

    public List<Incidencia> listarIncidencias() {
        return llistatIncidencies;
    }

    public void eliminarIncidencia(int index) {
        llistatIncidencies.remove(index);
    }

    public Incidencia buscarPorId(int id) {

        for (int it = 0; it < llistatIncidencies.size(); it++) {
            int idElemento = llistatIncidencies.get(it).getId();
            if (idElemento == id) {
                return llistatIncidencies.get(it);
            }
        }
        return null;
    }

    public List<Incidencia> listarPorEstado(int estado) {
        String estat = "";
        List<Incidencia> incidenciesEstat = new ArrayList<Incidencia>();
        switch (estado) {
            case 1:
                estat = "ABIERTA";
                break;
            case 2:
                estat = "EN_PROCESO";
                break;
            default:
                estat = "CERRADA";
                break;
        }
        for (int it = 0; it < llistatIncidencies.size(); it++) {
            if (llistatIncidencies.get(it).getEstado().toString().equals(estat)) {
                incidenciesEstat.add(llistatIncidencies.get(it));
            }
        }
        return incidenciesEstat;
    }
}
