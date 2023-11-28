package com.example.todo_restapi.exception;

import java.util.logging.Logger;

public class UserExistException extends RuntimeException {
    public UserExistException(String text) {
        Logger logger = Logger.getLogger(UserExistException.class.getName());
        logger.warning(text);
    }
}
