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

import com.recetas.backend.entities.Pais;
import com.recetas.backend.helpers.Helpers;
import com.recetas.backend.models.Response;
import com.recetas.backend.repositories.PaisRepository;

@RestController
@RequestMapping("/paises")
@CrossOrigin(origins = "*")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private Helpers helpers;

    @GetMapping()
    public ResponseEntity<?> listarPaises(){

        Map <String, Object> response = new HashMap<>();

        try {
            List <Pais> paises = paisRepository.findAll();

            response = helpers.apiResponse(new Response<>(true, "Lista de paises.", paises));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al encontrar la lista de paises");  
        }     
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPais(@PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        try {
            
            Pais paisEncontrado = paisRepository.findById(id).orElse(null);

            if(paisEncontrado == null){
                response = helpers.apiResponse(new Response<>(false, "El país no existe.", paisEncontrado));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response = helpers.apiResponse(new Response<>(true, "Se ha encontrado el país con éxito.", paisEncontrado));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al encontrar el país.");  
        }     
    }

    // // Obtener por nombre
    // @GetMapping("/search")
    // public ResponseEntity<?> searchByNombre(@RequestParam String nombre){

    //     List <Pais> paisNombre = paisRepository.findAllByNombrePaisContaining(nombre);
    //     return new ResponseEntity<>(paisNombre, HttpStatus.OK);

    // }

    @PostMapping()
    public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Pais pais, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            return helpers.validarCampos(result);
        }

        try {
             paisRepository.save(pais);

             response = helpers.apiResponse(new Response<>(true, "El país ha sido creado con éxito.", pais));

             return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataAccessException error){

            return helpers.catchError(error, "Ha sucedido un error al crear el país.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@Valid @RequestBody Pais pais, BindingResult result, @PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            return helpers.validarCampos(result);
        }

        try {
            
            Pais paisEncontrado = paisRepository.findById(id).orElse(null);

            if(paisEncontrado == null){
                response = helpers.apiResponse(new Response<>(false, "El país no existe.", paisEncontrado));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            paisEncontrado.setNombrePais(pais.getNombrePais());

            paisRepository.save(paisEncontrado);

            response = helpers.apiResponse(new Response<>(true, "Se ha actualizado el país con éxito.", ""));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al actualizar el país.");  
        }     
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id){

        Map <String, Object> response = new HashMap<>();

        try {
            
            Pais paisEncontrado = paisRepository.findById(id).orElse(null);

            if(paisEncontrado == null){
                response = helpers.apiResponse(new Response<>(false, "El país no existe.", paisEncontrado));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            paisRepository.delete(paisEncontrado);

            response = helpers.apiResponse(new Response<>(true, "Se ha eliminado el país con éxito.", ""));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException error) {

            return helpers.catchError(error, "Ha sucedido un error al eliminar el país.");  
        }     
    }


    
}
    

