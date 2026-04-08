package com.example.api_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_tareas.model.Tarea;
 /**
  * Interfaz que extiende JpaRepository para proporcionar métodos CRUD para la entidad Tarea.
  */
public interface TareaRepository extends JpaRepository <Tarea, Long> {
    
}
