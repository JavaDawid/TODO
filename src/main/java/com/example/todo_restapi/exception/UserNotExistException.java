package com.example.todo_restapi.exception;

import java.util.logging.Logger;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        Logger logger = Logger.getLogger(UserNotExistException.class.getName());
        logger.warning(message);
    }
}
