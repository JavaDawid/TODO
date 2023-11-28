package com.example.todo_restapi.repository;

import com.example.todo_restapi.models.Task;
import com.example.todo_restapi.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByUserEmail(String email);

    Optional<Task> findByIdAndUser(Long id, User user);
}
