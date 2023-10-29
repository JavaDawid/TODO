package com.example.todo_restapi.service;

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
// Required args
@AllArgsConstructor
// Powinnien byc interface TaskService i implementcja TaskServiceImpl
public class TaskService {
    private final TaskRepository taskRepository;


    public Long saveTask(TaskDto taskDto) {
        //Powinnienes korzystac wstrzyknac clock i na clock wywolwac clock.now() inaczej tyego nie da sie testowac
        Tasks tasks = new Tasks(taskDto.getTitle(), taskDto.getDescription(),
                taskDto.getPriority(), Instant.now(), false);
        taskRepository.save(tasks);
        return tasks.getId();
    }

    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id)
                // Dodaj mapstructa do projektui skorzystaj
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(),
                        task.getPriority(), task.getCreateDate(), task.isCompleted()));
    }

    public List<TaskDto> getAll() {
        List<TaskDto> taskDto = new ArrayList<>();

        // nie wiem jak zachowuje sie forEach z taskRepository.findAll() czy pryzpadkiem nie wolal findAll wiele razy
        // Czmeu nie streamem?
        for (Tasks tasks : taskRepository.findAll()) {
            taskDto.add(new TaskDto(tasks.getId(), tasks.getTitle(), tasks.getDescription(),
                    tasks.getPriority(), tasks.getCreateDate(), tasks.isCompleted()));
        }
        return taskDto;
    }

    public Optional<TaskDto> updateTask(Long id, TaskDto taskDto) {
        // Uzywalbym Konwencji UpdateTaskRequest i  UpdateTaskResponse
        Optional<Tasks> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Tasks task = optionalTask.get();
            task.setCompleted(taskDto.isCompleted());
            Tasks updatedTask = taskRepository.save(task);
            return Optional.of(new TaskDto(updatedTask.getTitle(), updatedTask.getDescription(), updatedTask.getPriority(),
                    updatedTask.getCreateDate(), updatedTask.isCompleted()));
        } else {
            // Rzuc bledem a nie zwracaj optional jak nie da sie znalezc
            return Optional.empty();
        }
    }

    public Optional<TaskDto> replaceTask(Long id, TaskDto taskDto) {
        Optional<Tasks> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Tasks task = optionalTask.get();
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
        taskRepository.deleteById(id);
    }
}
