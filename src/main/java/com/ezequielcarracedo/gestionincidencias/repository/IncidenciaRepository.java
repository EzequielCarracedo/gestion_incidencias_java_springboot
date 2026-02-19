package com.ezequielcarracedo.gestionincidencias.repository;


import org.springframework.data.jpa.repository.JpaRepository; // AQUESTA LÍNIA ÉS LA CLAU
import com.ezequielcarracedo.gestionincidencias.model.Incidencia;
import org.springframework.stereotype.Repository;


@Repository
public interface IncidenciaRepository  extends JpaRepository<Incidencia, Integer> {}