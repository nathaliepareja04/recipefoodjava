package com.recetas.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.backend.entities.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    
}
