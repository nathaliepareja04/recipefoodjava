package com.recetas.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.backend.entities.Categoria;
import com.recetas.backend.helpers.Helpers;
import com.recetas.backend.models.Response;
import com.recetas.backend.repositories.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private Helpers helpers;

    @GetMapping()
    public ResponseEntity<?> listarCategorias(){

        Map <String, Object> response = new HashMap<>();

        try {
            List <Categoria> categorias = categoriaRepository.findAll();

            response = helpers.apiResponse(new Response<>(true, "Lista de categorias.", categorias));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al encontrar la lista de categorias");  
        }     
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCategoria(@PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        try {
            
            Categoria categoriaEncontrada = categoriaRepository.findById(id).orElse(null);

            if(categoriaEncontrada == null){
                response = helpers.apiResponse(new Response<>(false, "La categoría no existe.", categoriaEncontrada));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response = helpers.apiResponse(new Response<>(true, "Se ha encontrado la categoría con éxito.", categoriaEncontrada));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al encontrar la categoría.");  
        }     
    }

    // // Obtener por nombre
    // @GetMapping("/search")
    // public ResponseEntity<?> searchByNombre(@RequestParam String nombre){

    //     List <Categoria> categoriaNombre = categoriaRepository.findAllByNombreCategoriaContaining(nombre);
    //     return new ResponseEntity<>(categoriaNombre, HttpStatus.OK);

    // }

    @PostMapping()
    public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Categoria categoria, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            return helpers.validarCampos(result);
        }

        try {
             categoriaRepository.save(categoria);

             response = helpers.apiResponse(new Response<>(true, "La categoría ha sido creada con éxito.", categoria));

             return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataAccessException error){

            return helpers.catchError(error, "Ha sucedido un error al crear la categoría.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@Valid @RequestBody Categoria categoria, BindingResult result, @PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            return helpers.validarCampos(result);
        }

        try {
            
            Categoria categoriaEncontrada = categoriaRepository.findById(id).orElse(null);

            if(categoriaEncontrada == null){
                response = helpers.apiResponse(new Response<>(false, "La categoría no existe.", categoriaEncontrada));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            categoriaEncontrada.setNombreCategoria(categoria.getNombreCategoria());

            categoriaRepository.save(categoriaEncontrada);

            response = helpers.apiResponse(new Response<>(true, "Se ha actualizado la categoría con éxito.", ""));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al actualizar la categoría.");  
        }     
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        try {
            
            Categoria categoriaEncontrada = categoriaRepository.findById(id).orElse(null);

            if(categoriaEncontrada == null){
                response = helpers.apiResponse(new Response<>(false, "La categoría no existe.", categoriaEncontrada));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            categoriaRepository.delete(categoriaEncontrada);

            response = helpers.apiResponse(new Response<>(true, "Se ha eliminado la categoría con éxito.", ""));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al eliminar la categoría.");  
        }     
    }


    
}
