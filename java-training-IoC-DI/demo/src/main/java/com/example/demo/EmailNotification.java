package com.example.demo;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
class EmailNotification implements NotificationService{
    public void send(String message){
        System.out.println("Email is send: " + message);
    }
}