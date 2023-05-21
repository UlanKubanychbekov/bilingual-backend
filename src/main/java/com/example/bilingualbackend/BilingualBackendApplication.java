package com.example.bilingualbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class BilingualBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilingualBackendApplication.class, args);
	}

	@GetMapping("/")
	public String greetings(){
		return "index";
	}
}
