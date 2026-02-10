package com.ezequielcarracedo.gestionincidencias.model;

import java.util.regex.Pattern;

public record Usuario(int id, String nom, String email) {

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Usuario {
   

        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("\nEl nombre no puede estar vacío\n");
        }
        if (email == null || email.isBlank()|| !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("\nEl email esta vacio o su formato es incorrecto.\n");
        }
    }

}
