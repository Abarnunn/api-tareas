package com.example.api_tareas.service;

import org.springframework.stereotype.Service;
import com.example.api_tareas.repository.TareaRepository;
import com.example.api_tareas.exception.TareaNotFoundException;
import com.example.api_tareas.model.Tarea;

import java.util.List;

@Service
public class TareaService {
    
    private final TareaRepository repository;

    /**
     * Constructor para inyectar el repositorio de tareas.
     * @param repository instancia de TareaRepository para acceder a la base de datos
     */
    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todas las tareas alamcenadas en la base de datos.
     * @return lista de tareas
     */
    public List<Tarea> getAllTareas() {
        return repository.findAll();
    }

    /**
     * Guarda una nueva tarea en la base de datos.
     * @param tarea objeto con los datos de la tarea
     * @return la tarea guardada con su ID generado
     */
    public Tarea saveTarea(Tarea tarea) {
        return repository.save(tarea);
    }

    public void deleteTarea(Long id) {
        repository.deleteById(id);
    }
    
    /**
     * Actualiza una tarea existente. Si la tarea no existe, lanza una excepción.
     * @param id ID de la tarea a actualizar
     * @param nuevaTarea objeto con los nuevos datos de la tarea
     * @return la tarea actualizada
     */
    public Tarea updateTarea(Long id, Tarea nuevaTarea) {
    return repository.findById(id)
        .map(tarea -> {
            tarea.setTitulo(nuevaTarea.getTitulo());
            tarea.setDescripcion(nuevaTarea.getDescripcion());
            tarea.setCompletada(nuevaTarea.isCompletada());
            return repository.save(tarea);
        })
        .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada con id " + id));
    }

}