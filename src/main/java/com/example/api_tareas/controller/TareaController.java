package com.example.api_tareas.controller;

import com.example.api_tareas.model.Tarea;
import com.example.api_tareas.service.TareaService;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Tarea>> getAllTareas() {
        return ResponseEntity.ok(service.getAllTareas());
    }

    /**
     * endpoint para obtener una tarea por su ID. Si la tarea no existe, se lanzará una excepción.
     * @param id
     * @return la tarea encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTareaById(id));
    }
    /**
     * endpoint para guardar una nueva tarea en la base de datos.
     * @param tarea
     * @return la tarea guardada con su ID generado
     */
    @PostMapping
    public ResponseEntity<Tarea> saveTarea(@Valid @RequestBody Tarea tarea) {
        Tarea nueva = service.saveTarea(tarea);
        return ResponseEntity.status(201).body(nueva);
    }

    /**
     * endpoint para eliminar una tarea por su ID. Si la tarea no existe, lanza una excepción.
     * @param id
     * @return respuesta sin contenido si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTarea(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * endpoint para actualizar una tarea existente. Si la tarea no existe, se lanzará una excepción.
     * @param id ID de la tarea a actualizar
     * @param tarea objeto con los nuevos datos de la tarea
     * @return la tarea actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @Valid @RequestBody Tarea tarea) {
        return ResponseEntity.ok(service.updateTarea(id, tarea));
    }

    @PatchMapping("/{id}/completar")
    public ResponseEntity<Tarea> completar(@PathVariable Long id) {
        return ResponseEntity.ok(service.marcarComoCompletada(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Tarea> cambiarEstado(@PathVariable Long id) {
         return ResponseEntity.ok(service.cambiarEstado(id));
    }


}