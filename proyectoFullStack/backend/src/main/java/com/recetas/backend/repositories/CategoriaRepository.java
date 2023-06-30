package com.recetas.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.backend.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
