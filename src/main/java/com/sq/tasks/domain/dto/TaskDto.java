package com.sq.tasks.domain.dto;

import com.sq.tasks.domain.entities.TaskStatus;
import com.sq.tasks.domain.entities.Taskpriority;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        Taskpriority priority,
        TaskStatus status


) {
}
