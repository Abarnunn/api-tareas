package com.example.api_tareas.service;

import org.springframework.stereotype.Service;

import com.example.api_tareas.repository.TareaRepository;
import com.example.api_tareas.exception.TareaNotFoundException;
import com.example.api_tareas.model.Tarea;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repository;

    /**
     * Constructor para inyectar el repositorio de tareas.
     * 
     * @param repository instancia de TareaRepository para acceder a la base de
     *                   datos
     */
    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todas las tareas alamcenadas en la base de datos.
     * 
     * @return lista de tareas
     */
    public List<Tarea> getAllTareas() {
        return repository.findAll();
    }

    /**
     * Obtiene una tarea por su ID. Si la tarea no existe, se lanzará una excepción.
     * 
     * @param id
     * @return la tarea encontrada.
     */
    public Tarea getTareaById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada con id " + id));
    }

    /**
     * Guarda una nueva tarea en la base de datos.
     * 
     * @param tarea objeto con los datos de la tarea
     * @return la tarea guardada con su ID generado
     */
    public Tarea saveTarea(Tarea tarea) {
        tarea.setFechaCreacion(LocalDateTime.now());
        return repository.save(tarea);
    }

    /**
     * Elimina una tarea por su ID. Si la tarea no existe, lanza una excepción.
     * 
     * @param id
     */
    public void deleteTarea(Long id) {
        if (!repository.existsById(id)) {
            throw new TareaNotFoundException("Tarea no encontrada con id " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Actualiza una tarea existente. Si la tarea no existe, lanza una excepción.
     * 
     * @param id         ID de la tarea a actualizar
     * @param nuevaTarea objeto con los nuevos datos de la tarea
     * @return la tarea actualizada
     */
    public Tarea updateTarea(Long id, Tarea nuevaTarea) {
        return repository.findById(id)
                .map(tarea -> {
                    tarea.setTitulo(nuevaTarea.getTitulo());
                    tarea.setDescripcion(nuevaTarea.getDescripcion());
                    tarea.setFechaCreacion(nuevaTarea.getFechaLimite());
                    return repository.save(tarea);
                })
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada con id " + id));
    }

    /**
     * Marca una tarea como completada
     * 
     * @param id ID de la tarea
     * @return tarea actualizada
     */
    public Tarea marcarComoCompletada(Long id) {
        Tarea tarea = repository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada con id " + id));

        tarea.setCompletada(false);

        return repository.save(tarea);
    }

    /**
     * Cambia el estado de una tarea.
     * Si está completada la marca como pendiente.
     * Si está pendiente la marca como completada.
     *
     * @param id ID de la tarea
     * @return tarea actualizada
     */
    public Tarea cambiarEstado(Long id) {

        Tarea tarea = repository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada con id " + id));

        tarea.setCompletada(!tarea.isCompletada());

        return repository.save(tarea);
    }

}