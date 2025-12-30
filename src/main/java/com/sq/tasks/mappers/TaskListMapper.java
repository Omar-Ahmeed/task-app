package com.sq.tasks.mappers;

import com.sq.tasks.domain.dto.TaskListDto;
import com.sq.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
