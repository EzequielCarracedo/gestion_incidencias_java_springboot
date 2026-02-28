package com.ezequielcarracedo.gestionincidencias.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.ezequielcarracedo.gestionincidencias.exception.IncidenciaNoEncontradaException;
import com.ezequielcarracedo.gestionincidencias.model.EstatIncidencia;
import com.ezequielcarracedo.gestionincidencias.model.Incidencia;
import com.ezequielcarracedo.gestionincidencias.model.Usuario;

@Service
public class IncidenciaService {

    private List<Incidencia> llistatIncidencies;
    private AtomicInteger contadorIncidencia = new AtomicInteger(1000);
    private AtomicInteger contadorUser = new AtomicInteger(1000);

    public IncidenciaService() {
        this.llistatIncidencies = new ArrayList<>();
        Incidencia incidencia = new Incidencia("PROBLEMA PANTALLA",
                new Usuario("Ezequiel", "correoL@gmail.com"));
        incidencia.setId(contadorIncidencia.getAndIncrement());
        incidencia.getUser().setId(contadorUser.getAndIncrement());
        llistatIncidencies.add(incidencia);
    }

    public Incidencia crearIncidencia(Incidencia incidencia, Usuario usuario) {
        incidencia.setId(contadorIncidencia.getAndIncrement());
        usuario.setId(contadorUser.getAndIncrement());
        incidencia.setUser(usuario);
        llistatIncidencies.add(incidencia);
        return incidencia;
    }

    public List<Incidencia> listarIncidencias() {
        if (llistatIncidencies.size() == 0) {
            throw new IncidenciaNoEncontradaException("LISTA DE INCIDENCIAS VACIA");
        }
        return llistatIncidencies;
    }

    public boolean eliminarIncidencia(Incidencia incidencia) {
        return llistatIncidencies.remove(incidencia);
    }

    public Incidencia buscarPorId(int id)  {

        if (llistatIncidencies.size() == 0) {
            throw new IncidenciaNoEncontradaException("LA LISTA ESTA VACIA");
        }

        for (int it = 0; it < llistatIncidencies.size(); it++) {
            int idElemento = llistatIncidencies.get(it).getId();
            if (idElemento == id) {
                return llistatIncidencies.get(it);
            }
        }
        throw new IncidenciaNoEncontradaException("NO EXISTE NINGUNA INCIDENCIA CON EL ID: " + id);
    }

    public List<Incidencia> listarPorEstado(int estado) {
        EstatIncidencia estat;
        List<Incidencia> incidenciesEstat = new ArrayList<Incidencia>();
        switch (estado) {
            case 1:
                estat = EstatIncidencia.ABIERTA;
                break;
            case 2:
                estat = EstatIncidencia.EN_PROCESO;
                break;
            default:
                estat = EstatIncidencia.CERRADA;
                break;
        }

        for (int it = 0; it < llistatIncidencies.size(); it++) {
            if (llistatIncidencies.get(it).getEstado() == estat) {
                incidenciesEstat.add(llistatIncidencies.get(it));
            }
        }
        return incidenciesEstat;
    }
}
