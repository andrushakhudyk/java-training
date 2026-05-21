package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/users")
public class TaskController {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/{userId}/tasks")
    public Tasks createTaskFromUser(@PathVariable Long userId, @RequestBody Tasks task) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        user.addTask(task);
        return taskRepository.save(task);   
    }

    @GetMapping("/{userId}/tasks")
    public Iterable<Tasks> getTasksByUserId(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getTasks();
    }

    @PutMapping("/{userId}/tasks/{taskId}")
    public Tasks updateTaskForUser(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Tasks updatedTask) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findById(taskId).map(task -> {
            if (!task.getUser().getId().equals(userId)) {
                throw new RuntimeException("Task does not belong to the user");
            }
            task.setTaskName(updatedTask.getTaskName());
            task.setDeadline(updatedTask.getDeadline());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public void deleteTaskForUser(@PathVariable Long userId, @PathVariable Long taskId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        Tasks task = taskRepository.findById(taskId)
        .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Task does not belong to the user");
        }

        taskRepository.delete(task);
    }
}
