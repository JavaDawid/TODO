package com.example.todo_restapi.mapper;

import com.example.todo_restapi.models.Task;
import com.example.todo_restapi.models.TaskDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public class TaskMapper {

    public TaskDto taskToTaskDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getCreateDate(), task.isCompleted());
    }
}
