package com.crud.tareas.controller;

import com.crud.tareas.model.Tarea;
import com.crud.tareas.repo.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    // Buscar todas las tareas
    @GetMapping("")
    private List<Tarea> index() {
        return tareaRepository.findAll();
    }

    // Buscar una tarea dado un id
    @GetMapping("{id}")
    private Optional<Tarea> findById(@PathVariable String id) {
        return Optional.ofNullable(
                tareaRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    // Crear una nueva tarea
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    private Tarea create(@RequestBody Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Actualizar una tarea
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    private Tarea update(@PathVariable String id, @RequestBody Tarea tarea) {

        Tarea tareaFromDb = tareaRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        tareaFromDb.setNombre(tarea.getNombre());
        tareaFromDb.setCompletado(tarea.isCompletado());

        return tareaRepository.save(tareaFromDb);
    }

    // Eliminar una tarea
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    private void delete(@PathVariable String id) {

        Tarea tarea = tareaRepository.findById(id).orElseThrow(RuntimeException::new);
        tareaRepository.delete(tarea);
    }


}
