package com.example.api_tareas.controller;

import com.example.api_tareas.model.Tarea;
import com.example.api_tareas.service.TareaService;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    /**
     * endpoint para obtener todas las tareas almacenadas en la base de datos.
     * @return lista de tareas
     */
    @GetMapping
    public List<Tarea> getAllTareas() {
        return service.getAllTareas();
    }

    /**
     * endpoint para guardar una nueva tarea en la base de datos.
     * @param tarea
     * @return la tarea guardada con su ID generado
     */
    @PostMapping
    public Tarea saveTarea(@Valid @RequestBody Tarea tarea) {
        return service.saveTarea(tarea);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTarea(id);
    }

    /**
     * endpoint para actualizar una tarea existente. Si la tarea no existe, se lanzará una excepción.
     * @param id ID de la tarea a actualizar
     * @param tarea objeto con los nuevos datos de la tarea
     * @return la tarea actualizada
     */
    @PutMapping("/{id}")
    public Tarea updateTarea(@PathVariable Long id,@Valid @RequestBody Tarea tarea) {
        return service.updateTarea(id, tarea);
    }


}