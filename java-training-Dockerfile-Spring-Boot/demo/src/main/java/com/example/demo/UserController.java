package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getUsers() {
        return userService.getAllUsers().toString();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return userService.getUserById(id).toString();
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String name, @RequestParam String email, @RequestParam int age) {
        return userService.createUser(name, email, age).toString();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String email, @RequestParam int age) {
        return userService.updateUser(id, name, email, age).toString();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
