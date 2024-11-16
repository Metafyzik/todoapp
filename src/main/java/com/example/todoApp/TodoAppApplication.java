package com.example.todoApp;

import com.example.todoApp.todo.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Item item = new Item(1,"test", LocalDateTime.now(),null,"testing stuff out");
			System.out.println(item);
		};
	}
}
