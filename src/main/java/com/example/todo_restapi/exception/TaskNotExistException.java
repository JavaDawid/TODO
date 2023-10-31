package com.example.todo_restapi.exception;

public class TaskNotExistException extends RuntimeException {

    public TaskNotExistException(Long id) {
        System.out.println("Zadanie o ID " + id + " nie istnieje");
    }
}
