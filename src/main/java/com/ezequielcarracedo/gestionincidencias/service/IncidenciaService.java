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
    private final UsuarioService usuarioService;
    private AtomicInteger contadorIncidencia = new AtomicInteger(1000);

    public IncidenciaService(UsuarioService usuarioService) {
        this.llistatIncidencies = new ArrayList<>();
        this.usuarioService = usuarioService;
        Usuario userProva = new Usuario("Ezequiel", "correoL@gmail.com");
        userProva = usuarioService.crearUsuario(userProva);
        Incidencia incidencia = new Incidencia("PROBLEMA PANTALLA", userProva);
        incidencia.setId(contadorIncidencia.getAndIncrement());

        llistatIncidencies.add(incidencia);
    }

    public Incidencia crearIncidencia(Incidencia incidencia,  int id) {
        incidencia.setId(contadorIncidencia.getAndIncrement());
        Usuario user = usuarioService.buscarPorId(id);
     //   Usuario userr = usuarioService.crearUsuario(usuario);
        incidencia.setUser(user);
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

    public Incidencia buscarPorId(int id) {

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

    public List<Incidencia> listarPorEstado(EstatIncidencia estado) {
        if (llistatIncidencies.size() == 0) {
            throw new IncidenciaNoEncontradaException("LA LISTA ESTA VACIA");
        }

        List<Incidencia> incidenciesEstat = new ArrayList<Incidencia>();

        for (int it = 0; it < llistatIncidencies.size(); it++) {
            if (llistatIncidencies.get(it).getEstado() == estado) {
                incidenciesEstat.add(llistatIncidencies.get(it));
            }
        }
        return incidenciesEstat;
    }

    public Incidencia actualizarIncidencia(Incidencia incidenciaNova, int id) {
        if (llistatIncidencies.size() == 0) {
            throw new IncidenciaNoEncontradaException("LA LISTA ESTA VACIA");
        }
        for (int it = 0; it < llistatIncidencies.size(); it++) {
            if (llistatIncidencies.get(it).getId() == id) {
                llistatIncidencies.get(it).setDescripcion(incidenciaNova.getDescripcion());
                llistatIncidencies.get(it).setEstado(incidenciaNova.getEstado());
                return llistatIncidencies.get(it);
            }
        }

        throw new IncidenciaNoEncontradaException("NO EXISTE NINGUNA INCIDENCIA CON EL ID: " + incidenciaNova.getId());
    }
}
