package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/users")
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public TaskController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @PostMapping("/{userId}/tasks")
    public Task createTaskFromUser(@PathVariable Long userId, @RequestBody Task task) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.addTask(task);
        return taskService.createTask(task);
    }

    @GetMapping("/{userId}/tasks")
    public Iterable<Task> getTasksByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user.getTasks();
    }
    @PutMapping("/{userId}/tasks/{taskId}")
    public Task updateTaskForUser(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Task updatedTask) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return taskService.updateTask(userId, taskId, updatedTask);
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public void deleteTaskForUser(@PathVariable Long userId, @PathVariable Long taskId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        taskService.deleteTask(userId, taskId);
    }
}
