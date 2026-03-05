package com.ezequielcarracedo.gestionincidencias.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.ezequielcarracedo.gestionincidencias.exception.UsuarioNoEncontradoException;
import com.ezequielcarracedo.gestionincidencias.model.Usuario;

@Service
public class UsuarioService {
    private List<Usuario> llistatUsuarios;
    private AtomicInteger contadorUser = new AtomicInteger(1000);

    public UsuarioService() {
        this.llistatUsuarios = new ArrayList<>();
        Usuario usuario = new Usuario("Ezequiel", "correoL@gmail.com");
        usuario.setId(contadorUser.getAndIncrement());
        llistatUsuarios.add(usuario);
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setId(contadorUser.getAndIncrement());
        llistatUsuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        if (llistatUsuarios.size() == 0) {
            throw new UsuarioNoEncontradoException("LISTA DE USUARIOS VACIA");
        }
        return llistatUsuarios;
    }

    public boolean eliminarUsuario(Usuario usuario) {
        return llistatUsuarios.remove(usuario);
    }

    public Usuario buscarPorId(int id) {

        if (llistatUsuarios.size() == 0) {
            throw new UsuarioNoEncontradoException("LA LISTA ESTA VACIA");
        }

        for (int it = 0; it < llistatUsuarios.size(); it++) {
            int idElemento = llistatUsuarios.get(it).getId();
            if (idElemento == id) {
                return llistatUsuarios.get(it);
            }
        }
        throw new UsuarioNoEncontradoException("NO EXISTE NINGUN USUARIO CON EL ID: " + id);
    }

    public Usuario actualizarUsuario(Usuario usuarioNou, int id) {
        if (llistatUsuarios.size() == 0) {
            throw new UsuarioNoEncontradoException("LA LISTA ESTA VACIA");
        }
        for (int it = 0; it < llistatUsuarios.size(); it++) {
            if (llistatUsuarios.get(it).getId() == id) {
                llistatUsuarios.get(it).setNom(usuarioNou.getNom());
                llistatUsuarios.get(it).setEmail(usuarioNou.getEmail());
                return llistatUsuarios.get(it);
            }
        }

        throw new UsuarioNoEncontradoException("NO EXISTE NINGUN USUARIO CON EL ID: " + id);
    }

}
