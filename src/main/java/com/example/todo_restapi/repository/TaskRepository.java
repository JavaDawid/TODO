package com.example.todo_restapi.repository;

import com.example.todo_restapi.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
