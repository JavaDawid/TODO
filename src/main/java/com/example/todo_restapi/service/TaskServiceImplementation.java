package com.example.todo_restapi.service;

import com.example.todo_restapi.exception.TaskNotExistException;
import com.example.todo_restapi.exception.UserNotExistException;
import com.example.todo_restapi.mapper.TaskMapper;
import com.example.todo_restapi.models.Task;
import com.example.todo_restapi.models.TaskDto;
import com.example.todo_restapi.models.User;
import com.example.todo_restapi.repository.TaskRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @NonNull
    private final UserServiceImplementation userServiceImplementation;

    @Override
    public Long saveTask(TaskDto taskDto) {
        User user = getUserFromContext();
        Task task = new Task(taskDto.getTitle(), taskDto.getDescription(),
                taskDto.getPriority(), Instant.now(Clock.systemDefaultZone()), false, user);
        taskRepository.save(task);
        return task.getId();
    }

    @Override
    public Optional<TaskDto> getTaskById(Long id) {
        Optional<Task> optionalTask = getOptionalTask(id);
        return optionalTask.map(taskMapper::taskToTaskDto);
    }

    @Override
    public List<TaskDto> getAll() {
        List<TaskDto> taskDto = new ArrayList<>();
        String userEmail = userContextGetName();
        taskRepository.findAllByUserEmail(userEmail).forEach(task -> taskDto.add(taskMapper.taskToTaskDto(task)));
        return taskDto;
    }

    @Override
    public Optional<TaskDto> completedTaskRequest(Long id) {
        Optional<Task> optionalTask = getOptionalTask(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);
            Task updatedTask = taskRepository.save(task);
            return Optional.of(taskMapper.taskToTaskDto(updatedTask));
        } else {
            throw new TaskNotExistException(id);
        }
    }

    @Override
    public Optional<TaskDto> updateTask(Long id, TaskDto taskDto) {
        Optional<Task> optionalTask = getOptionalTask(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setId(id);
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            Task updatedTask = taskRepository.save(task);
            return Optional.of(taskMapper.taskToTaskDto(updatedTask));
        } else {
            throw new TaskNotExistException(id);
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private Optional<Task> getOptionalTask(Long id) {
        User user = getUserFromContext();
        return taskRepository.findByIdAndUser(id, user);
    }

    private User getUserFromContext() {
        Optional<User> userByEmail = userServiceImplementation.getUserByEmail(userContextGetName());
        if (userByEmail.isPresent()) {
            return userByEmail.get();
        }
        else {
            throw new UserNotExistException("Użytkownik nie istnieje");
        }
    }

    private String userContextGetName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
