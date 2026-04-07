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

    public void delete(Long id) {
        repository.deleteById(id);
    }
}