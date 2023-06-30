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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.backend.entities.Receta;
import com.recetas.backend.helpers.Helpers;
import com.recetas.backend.models.Response;
import com.recetas.backend.repositories.RecetaRepository;

@RestController
@RequestMapping("/recetas")
@CrossOrigin(origins = "*")
public class RecetaController {
    
    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private Helpers helpers;

    @GetMapping()
    public ResponseEntity<?> listarRecetas(){

        Map <String, Object> response = new HashMap<>();

        try {
            List <Receta> recetas = recetaRepository.findAll();

            response = helpers.apiResponse(new Response<>(true, "Lista de recetas.", recetas));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al encontrar la lista de recetas");  
        }     
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCategoria(@PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        try {
            
            Receta recetaEncontrada = recetaRepository.findById(id).orElse(null);

            if(recetaEncontrada == null){
                response = helpers.apiResponse(new Response<>(false, "La receta no existe.", recetaEncontrada));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response = helpers.apiResponse(new Response<>(true, "Se ha encontrado la receta con éxito.", recetaEncontrada));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al encontrar la receta.");  
        }     
    }

    // Obtener por nombre
    @GetMapping("/search")
    public ResponseEntity<?> searchByNombre(@RequestParam String nombre){

        List <Receta> recetasNombre = recetaRepository.findAllByNombreContaining(nombre);
        return new ResponseEntity<>(recetasNombre, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Receta receta, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            return helpers.validarCampos(result);
        }

        try {
             recetaRepository.save(receta);

             response = helpers.apiResponse(new Response<>(true, "La receta ha sido creada con éxito.", receta));

             return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataAccessException error){

            return helpers.catchError(error, "Ha sucedido un error al crear la receta.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@Valid @RequestBody Receta receta, BindingResult result, @PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            return helpers.validarCampos(result);
        }

        try {
            
            Receta recetaEncontrada = recetaRepository.findById(id).orElse(null);

            if(recetaEncontrada == null){
                response = helpers.apiResponse(new Response<>(false, "La receta no existe.", recetaEncontrada));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            recetaEncontrada.setNombre(receta.getNombre());
            recetaEncontrada.setInstrucciones(receta.getInstrucciones());
            recetaEncontrada.setImageUrl(receta.getImageUrl());
            recetaEncontrada.setIngredientes(receta.getIngredientes());

            recetaRepository.save(recetaEncontrada);

            response = helpers.apiResponse(new Response<>(true, "Se ha actualizado la receta con éxito.", ""));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al actualizar la receta.");  
        }     
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        try {
            
            Receta recetaEncontrada = recetaRepository.findById(id).orElse(null);

            if(recetaEncontrada == null){
                response = helpers.apiResponse(new Response<>(false, "La receta no existe.", recetaEncontrada));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            recetaRepository.delete(recetaEncontrada);

            response = helpers.apiResponse(new Response<>(true, "Se ha eliminado la receta con éxito.", ""));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al eliminar la receta.");  
        }     
    }
}
