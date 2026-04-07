package com.example.api_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_tareas.model.Tarea;

public interface TareaRepository extends JpaRepository <Tarea, Long> {
    
}
