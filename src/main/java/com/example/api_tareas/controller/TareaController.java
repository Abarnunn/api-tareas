package com.example.api_tareas.controller;

import com.example.api_tareas.model.Tarea;
import com.example.api_tareas.service.TareaService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tarea> getAllTareas() {
        return service.getAllTareas();
    }

    @PostMapping
    public Tarea saveTarea(@RequestBody Tarea tarea) {
        return service.saveTarea(tarea);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}