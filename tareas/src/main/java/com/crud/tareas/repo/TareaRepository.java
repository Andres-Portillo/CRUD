package com.crud.tareas.repo;

import com.crud.tareas.model.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TareaRepository extends MongoRepository<Tarea, String> {
}
