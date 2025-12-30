package com.sq.tasks.controllers;

import com.sq.tasks.domain.dto.TaskDto;
import com.sq.tasks.domain.entities.Task;
import com.sq.tasks.mappers.TaskMapper;
import com.sq.tasks.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listAllTasks(taskListId)
                .stream().map(taskMapper::toDto).toList();
    }

    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto
    ){
        Task task = taskService.createTask(taskListId,taskMapper.fromDto(taskDto));
        return taskMapper.toDto(task);
    }

    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ){
        return taskService.getTask(taskListId,taskId)
                .map(taskMapper::toDto);
    }

    @PutMapping("/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto
    ){
        Task task = taskService.updateTask(taskListId,taskId,taskMapper.fromDto(taskDto));
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ){
        taskService.deleteTask(taskListId,taskId);
    }


}
