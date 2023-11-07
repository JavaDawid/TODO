package com.example.todo_restapi.service;

import com.example.todo_restapi.models.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface TaskService {

    Long saveTask(TaskDto taskDto);

    Optional<TaskDto> getTaskById(Long id);

    List<TaskDto> getAll();

    Optional<TaskDto> completedTaskRequest(Long id);

    Optional<TaskDto> updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);
}
