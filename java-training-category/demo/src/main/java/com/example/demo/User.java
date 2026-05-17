package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.ArrayList;

@Service
@RequestMapping("/users")
public class User {
    List<String> users = new ArrayList<>();

    public User() {
    }

    @GetMapping("/{id}")
    public List<String> getUser(@PathVariable int id) {
        return users.subList(0, id);
    }

    @GetMapping("/add/{name}")
    public String addUser(@PathVariable String name) {
        users.add(name);
        return "User added: " + name;
    }
}
