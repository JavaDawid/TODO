package com.example.todo_restapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
// "Task" nie "Tasks"
public class Tasks {

    // Id powinno byc gneerowane z sekwencji bazodanowej lub losowane jezeli to jest UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer priority;
    private Instant createDate;
    private boolean completed;

    public Tasks() {
    }

    // 1. musisz to dawaca?
    // 2. jezeli musisz to why not lombok?
    public Tasks(String title, String description, Integer priority, Instant createData, boolean completed) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.createDate = createData;
        this.completed = completed;
    }
}
