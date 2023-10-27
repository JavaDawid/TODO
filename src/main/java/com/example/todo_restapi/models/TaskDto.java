package com.example.todo_restapi.models;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Integer priority;
    private Instant createDate;
    private boolean completed;

    public TaskDto(String title, String description, Integer priority, Instant createDate, boolean completed) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.createDate = createDate;
        this.completed = completed;
    }

}
