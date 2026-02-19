package com.ezequielcarracedo.gestionincidencias.model;

import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id_cliente")
    private int id;
    private String nom;
    private String email;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public Usuario(){}

    public Usuario(int id, String nom, String email) {

        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("\nEl nombre no puede estar vacío\n");
        }
        this.nom = nom;
        if (email == null || email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("\nEl email esta vacio o su formato es incorrecto.\n");
        }
        this.email = email;
    }

    String getNom(){
        return nom;
    }

    int getId(){
        return id;
    }

    String getEmail(){
        return email;
    }

}
