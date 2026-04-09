package com.ezequielcarracedo.gestionincidencias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezequielcarracedo.gestionincidencias.model.EstatIncidencia;
import com.ezequielcarracedo.gestionincidencias.model.Incidencia;
import com.ezequielcarracedo.gestionincidencias.service.IncidenciaService;

import jakarta.validation.Valid;


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
        return ResponseEntity.ok(incidenciaService.listarIncidencias());
    }

    // Listar por estado
    @GetMapping("estado/{estado}")
    public ResponseEntity<?> getIncidenciaEstado(@PathVariable EstatIncidencia estado) {

        return ResponseEntity.ok(incidenciaService.listarPorEstado(estado));

    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getIncidencia(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(incidenciaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarIncidencia(@PathVariable int id) {

        Incidencia incidencia = new Incidencia();
        incidencia = incidenciaService.buscarPorId(id);

        incidenciaService.eliminarIncidencia(incidencia);

        return ResponseEntity.noContent().build();
    }

    // Crear incidencias
    @PostMapping()
    public ResponseEntity<?> crearIncidencia(@RequestBody @Valid Incidencia incidencia) {
        incidenciaService.crearIncidencia(incidencia, incidencia.getUser().getId());
        return ResponseEntity.ok(incidencia);
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarIncidencia(@PathVariable int id,
            @RequestBody @Valid Incidencia incidenciaModificada) {

        return ResponseEntity.ok(incidenciaService.actualizarIncidencia(incidenciaModificada, id));

    }

}
