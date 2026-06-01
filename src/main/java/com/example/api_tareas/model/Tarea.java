package com.example.api_tareas.model;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PatchMapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min=3, max = 100, message = "El título no puede tener más de 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min=10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres")
    private String descripcion;
    
    private boolean completada = false;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaLimite;

    public void setCompletada(boolean b) {
        throw new UnsupportedOperationException("Unimplemented method 'setCompletada'");
    }
  
}
