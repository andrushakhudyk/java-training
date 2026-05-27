package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskById(Long userId, Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null && !task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Task does not belong to the user");
        }
        return task;
    }

    public Iterable<Task> getAllTasks(Long userId) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getUser().getId().equals(userId))
                .toList();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long userId, Long taskId) {
        Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Task does not belong to the user");
        }

        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long userId, Long taskId, Task updatedTask) {
        return taskRepository.findById(taskId).map(task -> {
            if (!task.getUser().getId().equals(userId)) {
                throw new RuntimeException("Task does not belong to the user");
            }
            task.setTaskName(updatedTask.getTaskName());
            task.setDeadline(updatedTask.getDeadline());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);
        }).orElse(null);
    }
}
