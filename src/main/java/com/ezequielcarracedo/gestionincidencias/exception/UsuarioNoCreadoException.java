package com.ezequielcarracedo.gestionincidencias.exception;

public class UsuarioNoCreadoException extends RuntimeException {
    public UsuarioNoCreadoException(String mensaje) {
        super(mensaje);
    }
}
