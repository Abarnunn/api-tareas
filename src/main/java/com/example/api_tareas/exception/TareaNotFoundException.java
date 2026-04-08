package com.example.api_tareas.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para manejar el caso cuando una tarea no se encuentra en la base de datos.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
    public class TareaNotFoundException extends RuntimeException {
        public TareaNotFoundException(String mesage) {
            super(mesage);
        }
    }
