package com.ezequielcarracedo.gestionincidencias.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.ezequielcarracedo.gestionincidencias.dto.incidencia.IncidenciaCreateDTO;
import com.ezequielcarracedo.gestionincidencias.dto.incidencia.IncidenciaUpdateDTO;
import com.ezequielcarracedo.gestionincidencias.exception.IncidenciaNoEncontradaException;

import com.ezequielcarracedo.gestionincidencias.exception.UsuarioNoEncontradoException;
import com.ezequielcarracedo.gestionincidencias.model.EstatIncidencia;
import com.ezequielcarracedo.gestionincidencias.model.Incidencia;
import com.ezequielcarracedo.gestionincidencias.model.Usuario;

@Service
public class IncidenciaService {

    private Map<Integer, Incidencia> llistatIncidencies;
    private final UsuarioService usuarioService;
    private AtomicInteger contadorIncidencia = new AtomicInteger(1000);

    public IncidenciaService(UsuarioService usuarioService) {
        this.llistatIncidencies = new HashMap<>();
        this.usuarioService = usuarioService;

        Usuario userProva = new Usuario("Ezequiel", "correoL@gmail.com");
        userProva = usuarioService.crearUsuario(userProva);

        Incidencia incidencia = new Incidencia("PROBLEMA PANTALLA", userProva);
        incidencia.setId(contadorIncidencia.getAndIncrement());

        llistatIncidencies.put(incidencia.getId(), incidencia);
    }

    public Incidencia crearIncidencia(IncidenciaCreateDTO incidenciaDto, int id) {
        Incidencia incidencia = new Incidencia();

        incidencia.setId(contadorIncidencia.getAndIncrement());
        Usuario user = usuarioService.buscarPorId(id);
        if (user == null) {
            throw new UsuarioNoEncontradoException("USUARIO NO ENCONTRADO");
        }
        incidencia.setUser(user);
        incidencia.setDescripcion(incidenciaDto.getDescripcion());
        llistatIncidencies.put(incidencia.getId(), incidencia);
        return incidencia;
    }

    public List<Incidencia> listarIncidencias() {
        if (llistatIncidencies.size() == 0 || llistatIncidencies == null) {
            throw new IncidenciaNoEncontradaException("LISTA DE INCIDENCIAS VACIA");
        }
        return new ArrayList<Incidencia>(llistatIncidencies.values());
    }

    public boolean eliminarIncidencia(int id) {
        return llistatIncidencies.remove(id) != null;
    }

    public Incidencia buscarPorId(int id) {

        if (llistatIncidencies.size() == 0 || llistatIncidencies == null) {
            throw new IncidenciaNoEncontradaException("LA LISTA ESTA VACIA");
        }

        return llistatIncidencies.get(id);

    }

    public List<Incidencia> listarPorEstado(EstatIncidencia estado) {
        if (llistatIncidencies.size() == 0 || llistatIncidencies == null) {
            throw new IncidenciaNoEncontradaException("LA LISTA ESTA VACIA");
        }

        return llistatIncidencies.values()
                .stream()
                .filter(incidencia -> incidencia.getEstado() == estado)
                .toList();

    }

    public Incidencia actualizarIncidencia(IncidenciaUpdateDTO incidenciaNova, int id) {

        if (llistatIncidencies == null || llistatIncidencies.isEmpty()) {
            throw new IncidenciaNoEncontradaException("LA LISTA ESTA VACIA");
        }

        Incidencia incidenciaModificar = buscarPorId(id);
        if (incidenciaModificar != null) {
            if (!incidenciaNova.getDescripcion().isBlank()) {
                incidenciaModificar.setDescripcion(incidenciaNova.getDescripcion());
            }
            if (incidenciaNova.getEstado() != incidenciaModificar.getEstado()) {
                incidenciaModificar.setEstado(incidenciaNova.getEstado());
            }
            llistatIncidencies.put(id, incidenciaModificar);
            return incidenciaModificar;
        }

        throw new IncidenciaNoEncontradaException("NO EXISTE NINGUNA INCIDENCIA CON EL ID: " + id);
    }
}
