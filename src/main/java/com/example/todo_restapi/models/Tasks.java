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
public class Tasks {
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

    public Tasks(String title, String description, Integer priority, Instant createData, boolean completed) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.createDate = createData;
        this.completed = completed;
    }
}
