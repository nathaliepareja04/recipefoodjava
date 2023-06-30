package com.recetas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.backend.entities.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findAllByNombreContaining(String nombre);
}
