package com.example.api_tareas.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para la API.
 * Controlar las respuestas por defecto de Spring.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Controlar errores de validación de los campos de entrada.
     * @param ex
     * @return un mapa con el campo y el mensaje de error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return errors;
    }

    /**
     * Controlar errores generales de runtime (ej: tarea no encontrada).
     */
    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntimeErrors(RuntimeException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return errors;
    }
}