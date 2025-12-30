package com.sq.tasks.mappers;

import com.sq.tasks.domain.dto.TaskDto;
import com.sq.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
