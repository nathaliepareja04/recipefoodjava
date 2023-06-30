package com.recetas.backend.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.recetas.backend.models.Response;

@Service
public class Helpers {
    
    public Map<String, Object> apiResponse(Response data){

        Map<String, Object> response = new HashMap<>();

        response.put("ok", data.isOk());
        response.put("message", data.getMessage());
        response.put("data", data.getData());

        return response;

    }

    public ResponseEntity<?> validarCampos (BindingResult result){

        Map<String, Object> response = new HashMap<>();

        List<String> errores = result.getFieldErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .collect(Collectors.toList());
        response = apiResponse(new Response<>(false, errores, ""));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> catchError(DataAccessException error, String message){

        Map<String, Object> response = new HashMap<>();

        response = apiResponse(new Response<>(true, message + error.getMessage() + " " + error.getMostSpecificCause().getMessage(), ""  ));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
