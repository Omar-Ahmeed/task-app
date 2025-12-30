package com.sq.tasks.services.Imp;


import com.sq.tasks.domain.entities.TaskList;
import com.sq.tasks.repo.TaskListRepo;
import com.sq.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }

    @Override
    public List<TaskList> listTaskList() {
        return taskListRepo.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null!=taskList.getId()){
            throw new IllegalArgumentException("Task list already exists");
        }
        if(taskList.getTitle()==null|| taskList.getTitle().trim().isEmpty()){
            throw new IllegalArgumentException("Task list title can't be empty");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepo.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
                )
        );
    }

    @Override
    public Optional<TaskList> getTaskList(UUID taskListId) {
        return taskListRepo.findById(taskListId);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null==taskList.getId()){
            throw new IllegalArgumentException("Task list doesn't exists");
        }
        if(!Objects.equals(taskList.getId(),taskListId)){
            throw new IllegalArgumentException("Cannot update task list Id");
        }

        TaskList updatedTaskList = taskListRepo.findById(taskListId).orElseThrow(
                () -> new IllegalArgumentException("Task list id not found")
        );

        updatedTaskList.setTitle(taskList.getTitle());
        updatedTaskList.setDescription(taskList.getDescription());
        updatedTaskList.setUpdated(LocalDateTime.now());
        return taskListRepo.save(updatedTaskList);


    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepo.deleteById(taskListId);
    }
}
