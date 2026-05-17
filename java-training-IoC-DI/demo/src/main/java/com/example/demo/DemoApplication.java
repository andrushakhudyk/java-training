package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DemoApplication.class, args);
		OrderProcessor proc = context.getBean(OrderProcessor.class);

		proc.msgSend("HI Hitler!");
	}

}
