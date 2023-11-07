package com.example.todo_restapi.service;

import com.example.todo_restapi.exceptions.NoTaskElementException;
import com.example.todo_restapi.mapper.TaskMapper;
import com.example.todo_restapi.models.Task;
import com.example.todo_restapi.models.TaskDto;
import com.example.todo_restapi.repository.TaskRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TaskService {
    @NonNull
    private final TaskRepository taskRepository;
    @NonNull
    private final TaskMapper taskMapper;

    @Override
    public Long saveTask(TaskDto taskDto) {
        Task task = new Task(taskDto.getTitle(), taskDto.getDescription(),
                taskDto.getPriority(), Instant.now(Clock.systemDefaultZone()), false);
        taskRepository.save(task);
        return task.getId();

    }

    @Override
    public Optional<TaskDto> getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(taskMapper::taskToTaskDto);
    }

    @Override
    public List<TaskDto> getAll() {
        List<TaskDto> taskDto = new ArrayList<>();
        taskRepository.findAll().forEach(task -> taskDto.add(taskMapper.taskToTaskDto(task)));
        return taskDto;
    }

    @Override
    public Optional<TaskDto> completedTaskRequest(Long id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(taskDto.isCompleted());
            Task updatedTask = taskRepository.save(task);
            return Optional.of(taskMapper.taskToTaskDto(updatedTask));
        } else {
            throw new NoTaskElementException();
        }
    }

    @Override
    public Optional<TaskDto> updateTask(Long id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            Task updatedTask = taskRepository.save(task);
            return Optional.of(taskMapper.taskToTaskDto(updatedTask));
        } else {
            throw new NoTaskElementException();
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
