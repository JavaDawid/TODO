package com.example.todo_restapi.models;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private Integer priority;
    private Instant createDate;
    private boolean completed;
}
