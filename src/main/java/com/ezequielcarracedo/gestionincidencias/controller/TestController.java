package com.ezequielcarracedo.gestionincidencias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezequielcarracedo.gestionincidencias.repository.IncidenciaRepository;

@RestController
public class TestController {

    @Autowired
    private IncidenciaRepository repository;

    @GetMapping("/test/comptar")
    public String comptar() {
        long total = repository.count(); 
        return "Hi ha " + total + " incidències a la base de dades.";
    }
}