package com.example.demo;

import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public @Valid User createUser(String name, String email, int age) {
        User user = new User(name, email, age);
        return userRepository.save(user);
    }

    public @Valid User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public @Valid Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public @Valid User updateUser(Long id, String name, String email, int age) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setAge(age);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
