package com.sq.tasks.services.Imp;

import com.sq.tasks.domain.entities.Task;
import com.sq.tasks.domain.entities.TaskList;
import com.sq.tasks.domain.entities.TaskStatus;
import com.sq.tasks.domain.entities.Taskpriority;
import com.sq.tasks.mappers.TaskMapper;
import com.sq.tasks.repo.TaskListRepo;
import com.sq.tasks.repo.TaskRepo;
import com.sq.tasks.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final TaskListRepo taskListRepo;

    public TaskServiceImpl(TaskRepo taskRepo, TaskListRepo taskListRepo) {
        this.taskRepo = taskRepo;
        this.taskListRepo = taskListRepo;
    }

    @Override
    public List<Task> listAllTasks(UUID taskListId) {
        return taskRepo.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task can't have ID");
        }
        if(task.getTitle() == null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have title");
        }

        TaskList taskList = taskListRepo.findById(taskListId)
                .orElseThrow(
                ()-> new IllegalArgumentException("Task with id " + taskListId + " does not exist"));

        TaskStatus taskStatus = TaskStatus.OPEN;
        Taskpriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(Taskpriority.LOW);
        LocalDateTime now = LocalDateTime.now();

        Task newTask = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );
       return taskRepo.save(newTask);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepo.findByTaskListIdAndId(taskListId,taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null ==  task.getId()){
            throw new IllegalArgumentException("Task must have ID");
        }
        if(!Objects.equals(task.getId(),taskId)){
            throw new IllegalArgumentException("IDs don't match");
        }
        if(task.getStatus() == null){
            throw new IllegalArgumentException("Task must have status");
        }
        if(task.getPriority() == null){
            throw new IllegalArgumentException("Task must have priority");
        }

        Task updatedTask = taskRepo.findByTaskListIdAndId(taskListId,taskId)
                .orElseThrow(()->new IllegalArgumentException("Did not found the Task") );

        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setDueDate(task.getDueDate());
        updatedTask.setStatus(task.getStatus());
        updatedTask.setPriority(task.getPriority());
        updatedTask.setUpdated(LocalDateTime.now());
        return taskRepo.save(updatedTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepo.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
