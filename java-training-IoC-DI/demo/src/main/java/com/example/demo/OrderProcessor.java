package com.example.demo;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderProcessor{
    private final NotificationService servic;

    // @Autowired
    // public OrderProcessor(NotificationService servic){
    //     this.servic = servic;
    //     this.test = "Difult";
    // }

    void msgSend(String msg){
        servic.send(msg);
        System.out.println("message is send");
    }
}