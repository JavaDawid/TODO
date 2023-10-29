package com.example.todo_restapi.controller;

import com.example.todo_restapi.models.TaskDto;
import com.example.todo_restapi.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class ApiTaskController {

    private final TaskService taskService;

    @PostMapping("")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Long id = taskService.saveTask(taskDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> allTasks = taskService.getAll();
        if (!allTasks.isEmpty()) {
            // Created WTF?
            return ResponseEntity.status(HttpStatus.CREATED).body(allTasks);
        } else {
            return ResponseEntity.ok(allTasks);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> finishTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        // zwracalbym 200 OK jak udalo sie zrobic update
        return taskService.updateTask(id, taskDto)
                .map(taskDto1 -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Object> replaceTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        // updateTask nie replaceTask
        return taskService.replaceTask(id, taskDto)
                .map(taskDto1 -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
