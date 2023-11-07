package com.example.todo_restapi.exception;

import java.util.logging.Logger;

public class TaskNotExistException extends RuntimeException {

    public TaskNotExistException(Long id) {
        Logger logger = Logger.getLogger(TaskNotExistException.class.getName());
        logger.warning("Zadanie o ID " + id + " nie istnieje");
    }
}
