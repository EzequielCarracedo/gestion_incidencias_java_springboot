package com.ezequielcarracedo.gestionincidencias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezequielcarracedo.gestionincidencias.exception.IncidenciaNoEncontradaException;
import com.ezequielcarracedo.gestionincidencias.model.Incidencia;
import com.ezequielcarracedo.gestionincidencias.service.IncidenciaService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/incidencias")
@RestController
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    // Listar todas las incidencias
    @GetMapping()
    public ResponseEntity<?> getAllIncidencias() throws Exception {
        List<Incidencia> llistatIncidencies;
        try {
            llistatIncidencies = incidenciaService.listarIncidencias();
            return ResponseEntity.ok(llistatIncidencies);
        } catch (IncidenciaNoEncontradaException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

      // Listar por estado
    @GetMapping("estado/{estado}")
    public ResponseEntity<?> getIncidenciaEstado(@PathVariable int estado) {
        List<Incidencia> llistatIncidencia;
        try {
            llistatIncidencia = incidenciaService.listarPorEstado(estado);
            if (llistatIncidencia.size()== 0) {
                return ResponseEntity.status(404).body("NO HAY INCIDENCIA CON ESE ESTADO");
            }
            return ResponseEntity.ok(llistatIncidencia);
        } catch (IncidenciaNoEncontradaException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getIncidencia(@PathVariable int id)throws Exception {
        Incidencia incidencia = new Incidencia();
        try {
            incidencia = incidenciaService.buscarPorId(id);
            return ResponseEntity.ok(incidencia);
        } catch (IncidenciaNoEncontradaException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarIncidencia(@PathVariable int id) {
        Incidencia incidencia = new Incidencia();
        try {
            incidencia = incidenciaService.buscarPorId(id);
            incidenciaService.eliminarIncidencia(incidencia);
            return ResponseEntity.status(204).body("INCIDENCIA BORRADA CORRECTAMENTE");
        } catch (IncidenciaNoEncontradaException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    // Crear incidencias
    @PostMapping()
    public ResponseEntity<?> crearIncidencia(@RequestBody Incidencia incidencia) {
        try {
            incidenciaService.crearIncidencia(incidencia, incidencia.getUser());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.ok(incidencia);
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable int id, @RequestBody Incidencia incidenciaModificada) {
        Incidencia incidencia = new Incidencia();
        try {
            incidencia = incidenciaService.buscarPorId(id);
            incidencia.setDescripcion(incidenciaModificada.getDescripcion());
            incidencia.setEstado(incidenciaModificada.getEstado());
            return ResponseEntity.ok(incidencia);
        } catch (IncidenciaNoEncontradaException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

}
