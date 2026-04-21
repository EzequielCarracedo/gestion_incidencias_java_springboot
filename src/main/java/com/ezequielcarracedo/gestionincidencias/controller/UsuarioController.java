package com.ezequielcarracedo.gestionincidencias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ezequielcarracedo.gestionincidencias.exception.UsuarioNoEncontradoException;

import com.ezequielcarracedo.gestionincidencias.model.Usuario;

import com.ezequielcarracedo.gestionincidencias.service.UsuarioService;

import jakarta.validation.Valid;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todas las incidencias
    @GetMapping()
    public ResponseEntity<?> getAllUsuarios() throws UsuarioNoEncontradoException {

        return ResponseEntity.ok(usuarioService.listarUsuarios());

    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable int id) throws UsuarioNoEncontradoException {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        boolean eliminat = usuarioService.eliminarUsuario(id);
        if (!eliminat) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Crear usuario
    @PostMapping()
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario) {

        usuarioService.crearUsuario(usuario);

        return ResponseEntity.ok(usuario);

    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable int id, @RequestBody @Valid Usuario usuarioModificado) {

        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuarioModificado, id));

    }

}
