package com.sq.tasks.mappers.impl;

import com.sq.tasks.domain.dto.TaskListDto;
import com.sq.tasks.domain.entities.Task;
import com.sq.tasks.domain.entities.TaskList;
import com.sq.tasks.domain.entities.TaskStatus;
import com.sq.tasks.mappers.TaskListMapper;
import com.sq.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null)
        );
    }
    public double calculateTaskListProgress(List<Task>  tasks) {
        if(null == tasks)
            return 0;

        long count = tasks.stream().filter(task -> task.getStatus() == TaskStatus.CLOSED)
                .count();
        return (double)count / (double)tasks.size();
    }
}
