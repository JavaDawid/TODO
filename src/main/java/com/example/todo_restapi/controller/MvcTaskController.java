package com.example.todo_restapi.controller;

import com.example.todo_restapi.models.TaskDto;
import com.example.todo_restapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class MvcTaskController {
    private final TaskService taskService;

    @GetMapping("/tables")
    public String getAllTasks(Model model) {
        List<TaskDto> tasks = taskService.getAll();
        model.addAttribute("tasks", tasks);
        return "table";
    }

    @GetMapping("")
    public String getTask(@RequestParam("id") Long id, Model model) {
        Optional<TaskDto> task = taskService.getTaskById(id);
        task.ifPresent(taskDto -> model.addAttribute("task", taskDto));
        return "task";
    }

    @GetMapping("/update")
    public String getTaskToUpdate(@RequestParam("id") Long id, Model model) {
        Optional<TaskDto> task = taskService.getTaskById(id);
        task.ifPresent(taskDto -> model.addAttribute("task", taskDto));
        return "update";
    }

    @PostMapping("")
    public String updateTask(@RequestParam("id") Long id, @ModelAttribute TaskDto taskDto, Model model) {
        Optional<TaskDto> taskOptional = taskService.updateTask(id, taskDto);
        taskOptional.ifPresent(task -> model.addAttribute("task", task));
        return "task";
    }

    @PostMapping("/end")
    public String endTask(@RequestParam("id") Long id, Model model) {
        Optional<TaskDto> taskDtoOptional = taskService.completedTaskRequest(id);
        taskDtoOptional.ifPresent(task -> model.addAttribute("task", task));
        return "task";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam("id") Long id, Model model) {
        Optional<TaskDto> taskById = taskService.getTaskById(id);
        taskById.ifPresent(taskDto -> model.addAttribute("task", taskDto));
        taskService.deleteTask(id);
        return "task";
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task", taskDto);
        return "create";
    }

    @PostMapping("/create")
    public String createNewTaskDto(@ModelAttribute TaskDto taskDto, Model model) {
        Long taskId = taskService.saveTask(taskDto);
        Optional<TaskDto> taskById = taskService.getTaskById(taskId);
        taskById.ifPresent(task -> model.addAttribute("task", task));
        return "task";
    }

}
