package com.example.demo;

import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import java.lang.IndexOutOfBoundsException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
//import java.util.Map;


@RestController
@RequestMapping
public class UserController{

    private ArrayList<String> users = new ArrayList<>(List.of("Bob", "Alica", "Fred"));

    public String getUserById(int id){
        return users.get(id);
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<String> getUser(@PathVariable int id){
        try{
            String user = getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch(Exception e){
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }
    public String findByName(String name){
        String user = users.stream().filter(x -> x.equals(name)).collect(Collectors.joining());
        return user;
    }

    @GetMapping("/get/name")
    public String getUser(@RequestParam String name){
        return findByName(name);
    }
}