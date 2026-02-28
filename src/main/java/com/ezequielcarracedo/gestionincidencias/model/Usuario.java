package com.ezequielcarracedo.gestionincidencias.model;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Usuario {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank(message = "EL NOMBRE ES OBLIGATORIO")
    @Size(min = 1, max = 40, message = "NOMBRE MUY LARGO, MAXIMO 40 CARACTERES")
    private String nom;

    @NotBlank(message = "EL CORREO ES OBLIGATORIO")
    @Size(min = 1, max = 100, message = "CORREO MUY LARGO, MAXIMO 100 CARACTERES")
    @Email(message = "FORMATO INCORRECTO")
    private String email;


    public Usuario() {
    }

    public Usuario(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
