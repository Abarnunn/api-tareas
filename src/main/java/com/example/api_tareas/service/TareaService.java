package com.example.api_tareas.service;

import org.springframework.stereotype.Service;
import com.example.api_tareas.repository.TareaRepository;
import com.example.api_tareas.model.Tarea;

import java.util.List;

@Service
public class TareaService {
    
    private final TareaRepository repository;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public List<Tarea> getAllTareas() {
        return repository.findAll();
    }

    public Tarea saveTarea(Tarea tarea) {
        return repository.save(tarea);
    }

    public void deleteTarea(Long id) {
        repository.deleteById(id);
    }
    
    public Tarea updateTarea(Long id, Tarea nuevaTarea) {
    return repository.findById(id)
        .map(tarea -> {
            tarea.setTitulo(nuevaTarea.getTitulo());
            tarea.setDescripcion(nuevaTarea.getDescripcion());
            tarea.setCompletada(nuevaTarea.isCompletada());
            return repository.save(tarea);
        })
        .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id " + id));
    }

}