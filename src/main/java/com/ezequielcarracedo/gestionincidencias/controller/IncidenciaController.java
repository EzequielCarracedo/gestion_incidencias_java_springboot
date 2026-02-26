package com.ezequielcarracedo.gestionincidencias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezequielcarracedo.gestionincidencias.model.Incidencia;
import com.ezequielcarracedo.gestionincidencias.service.IncidenciaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/incidencias")
@RestController
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> getIncidencia(@PathVariable int id) {
       return ResponseEntity.ok(incidenciaService.buscarPorId(id));
       
    }

}
