package com.ezequielcarracedo.gestionincidencias.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.ezequielcarracedo.gestionincidencias.exception.UsuarioNoEncontradoException;
import com.ezequielcarracedo.gestionincidencias.model.Usuario;

@Service
public class UsuarioService {
    private Map<Integer, Usuario> llistatUsuarios;
    private AtomicInteger contadorUser = new AtomicInteger(1000);

    public UsuarioService() {
        this.llistatUsuarios = new HashMap<>();
        Usuario usuario = new Usuario("Ezequiel", "correoL@gmail.com");
        usuario.setId(contadorUser.getAndIncrement());
        llistatUsuarios.put(usuario.getId(), usuario);
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setId(contadorUser.getAndIncrement());
        llistatUsuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        if (llistatUsuarios == null) {
            throw new UsuarioNoEncontradoException("LISTA DE USUARIOS VACIA");
        }
        return new ArrayList<>(llistatUsuarios.values());
    }

    public boolean eliminarUsuario(int id) {
        return llistatUsuarios.remove(id) != null;
    }

    public Usuario buscarPorId(int id) {

        if (llistatUsuarios == null) {
            throw new UsuarioNoEncontradoException("LA LISTA ESTA VACIA");
        }
        Usuario user = llistatUsuarios.get(id);
        if (user == null) {
            throw new UsuarioNoEncontradoException("NO EXISTE NINGUN USUARIO CON EL ID: " + id);
        }
        return user;
    }

    public Usuario actualizarUsuario(Usuario usuarioNou, int id) {
        if (llistatUsuarios == null) {
            throw new UsuarioNoEncontradoException("LA LISTA ESTA VACIA");
        }
        for (int it = 0; it < llistatUsuarios.size(); it++) {
            if (llistatUsuarios.get(it).getId() == id) {
                llistatUsuarios.get(it).setNom(usuarioNou.getNom());
                llistatUsuarios.get(it).setEmail(usuarioNou.getEmail());
                return llistatUsuarios.get(it);
            }
        }
        Usuario userModificar = llistatUsuarios.get(id);

        if (userModificar != null) {
            userModificar.setNom(usuarioNou.getNom());
            userModificar.setEmail(usuarioNou.getEmail());

            llistatUsuarios.put(id, userModificar);
            return userModificar;
        }

        throw new UsuarioNoEncontradoException("NO EXISTE NINGUN USUARIO CON EL ID: " + id);
    }

}
