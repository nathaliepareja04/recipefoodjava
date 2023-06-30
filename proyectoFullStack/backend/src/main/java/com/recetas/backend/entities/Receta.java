package com.recetas.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recetas")
public class Receta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo 'nombre' es obligatorio.")
    @Size(min = 4, max = 50)
    @Column(name = "nombre")
    private String nombre;

    @NotEmpty(message = "El campo 'instrucciones' es obligatorio.")
    @Size(min = 4, max = 3000)
    @Column(name = "instrucciones" , nullable = false)
    private String instrucciones;

    @NotEmpty(message = "El campo 'imageUrl' es obligatorio.")
    @Size(min = 4)
    @Column(name = "imageUrl" , nullable = false)
    private String imageUrl;

    @NotEmpty(message = "El campo 'videoUrl' es obligatorio.")
    @Size(min = 4)
    @Column(name = "videoUrl" , nullable = false)
    private String videoUrl;

    @NotEmpty(message = "El campo 'ingredientes' es obligatorio.")
    @Size(min = 4, max = 3000)
    @Column(name = "ingredientes" , nullable = false)
    private String ingredientes;

     // Categoria
     @ManyToOne
     @JoinColumn(name = "categoria_id", nullable = false)
     @JsonProperty(access = Access.READ_WRITE)
     private Categoria categoria;
 
     // Pais
     @ManyToOne
     @JoinColumn(name = "pais_id", nullable = false)
     @JsonProperty(access = Access.READ_WRITE)
     private Pais pais;
}
