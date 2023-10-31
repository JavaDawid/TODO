package com.example.todo_restapi.service;

import com.example.todo_restapi.exception.TaskNotExistException;
import com.example.todo_restapi.models.TaskDto;
import com.example.todo_restapi.models.Tasks;
import com.example.todo_restapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;


    public Long saveTask(TaskDto taskDto) {
        Tasks task = new Tasks(taskDto.getTitle(), taskDto.getDescription(),
                taskDto.getPriority(), Instant.now(), false);
        Tasks savedTask = taskRepository.save(task);
        return savedTask.getId();
    }

    public Optional<TaskDto> getTaskById(Long id) {
        if (id > 0 && id < taskRepository.count()) {
            return taskRepository.findById(id)
                    .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(),
                            task.getPriority(), task.getCreateDate(), task.isCompleted()));
        } else {
            throw new TaskNotExistException(id);
        }
    }

    public List<TaskDto> getAll() {
        List<TaskDto> taskDto = new ArrayList<>();
        for (Tasks tasks : taskRepository.findAll()) {
            taskDto.add(new TaskDto(tasks.getId(), tasks.getTitle(), tasks.getDescription(),
                    tasks.getPriority(), tasks.getCreateDate(), tasks.isCompleted()));
        }
        return taskDto;
    }

    public Optional<TaskDto> endTask(Long id) {
        Optional<Tasks> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Tasks task = optionalTask.get();
            task.setCompleted(true);
            Tasks updatedTask = taskRepository.save(task);
            return Optional.of(new TaskDto(updatedTask.getTitle(), updatedTask.getDescription(), updatedTask.getPriority(),
                    updatedTask.getCreateDate(), updatedTask.isCompleted()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<TaskDto> replaceTask(Long id, TaskDto taskDto) {
        Optional<Tasks> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Tasks task = optionalTask.get();
            task.setId(id);
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            Tasks updatedTask = taskRepository.save(task);
            return Optional.of(new TaskDto(updatedTask.getTitle(), updatedTask.getDescription(), updatedTask.getPriority(),
                    updatedTask.getCreateDate(), updatedTask.isCompleted()));
        } else {
            return Optional.empty();
        }
    }

    public void deleteTask(Long id) {
        Optional<Tasks> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.delete(taskOptional.get());
        } else {
            throw new TaskNotExistException(id);
        }
    }
}
