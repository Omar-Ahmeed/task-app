package com.sq.tasks.services;

import com.sq.tasks.domain.entities.TaskList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskList();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID taskListId);
    TaskList updateTaskList(UUID taskListId, TaskList taskList);
    void deleteTaskList(UUID taskListId);
}
