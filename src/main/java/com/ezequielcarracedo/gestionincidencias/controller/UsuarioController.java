package com.ezequielcarracedo.gestionincidencias.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezequielcarracedo.gestionincidencias.exception.UsuarioNoEncontradoException;

import com.ezequielcarracedo.gestionincidencias.model.Usuario;

import com.ezequielcarracedo.gestionincidencias.service.UsuarioService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
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
        List<Usuario> llistatUsuaris;
        try {
            llistatUsuaris = usuarioService.listarUsuarios();
            return ResponseEntity.ok(llistatUsuaris);
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body("NO HAY USUARIOS");
        }
    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable int id) throws UsuarioNoEncontradoException {
        Usuario usuario = new Usuario();
        try {
            usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body("USUARIO CON ID: " + id + " NO ENCONTRADO");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        Usuario usuario = new Usuario();
        try {
            usuario = usuarioService.buscarPorId(id);
            usuarioService.eliminarUsuario(usuario);
            return ResponseEntity.status(204).body("USUARIO BORRADO CORRECTAMENTE");
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body("USUARIO CON ID: " + id + " NO ENCONTRADO");
        }
    }

    // Crear usuario
    @PostMapping()
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario userNou = usuarioService.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(userNou);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable int id, @RequestBody Usuario usuarioModificado) {
        Usuario usuario = new Usuario();
        try {
            usuario = usuarioService.actualizarUsuario(usuarioModificado, id);

            return ResponseEntity.ok(usuario);
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(404).body("USUARIO CON ID: " + id + " NO ENCONTRADO");
        }

    }

}
