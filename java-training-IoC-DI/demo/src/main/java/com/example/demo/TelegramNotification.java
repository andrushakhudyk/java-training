package com.example.demo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
class TelegramNotification implements NotificationService{
    public void send(String message){
        System.out.println("Telegram is send: " + message);
    }
}