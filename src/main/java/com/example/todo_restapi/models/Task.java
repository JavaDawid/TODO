package com.example.todo_restapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq_gen")
    @SequenceGenerator(name = "task_seq_gen", sequenceName = "task_seq", allocationSize = 1)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private Integer priority;
    @NonNull
    private Instant createDate;
    @NonNull
    private boolean completed;

    public Task() {
    }
}
