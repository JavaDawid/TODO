package com.example.todo_restapi.exceptions;

public class NoTaskElementException extends RuntimeException {

    public NoTaskElementException() {
        System.out.println("Brak takiego zadania");
    }
}
