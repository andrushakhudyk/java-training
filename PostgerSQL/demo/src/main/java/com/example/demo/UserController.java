package com.example.demo;

//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public User addUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/find/{Id}")
    public User getById(@PathVariable("Id") Long Id){
        return userRepository.findById(Id).orElse(null);
    }

    @DeleteMapping("/delete/{email}")
    public String deleteUserByEmail(@PathVariable("email") String email){
        userRepository.deleteByEmail(email);
        return "User with email " + email + "deleted";
    }
}
