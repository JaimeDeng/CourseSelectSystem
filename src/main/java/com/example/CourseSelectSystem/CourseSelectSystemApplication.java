package com.example.CourseSelectSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CourseSelectSystem API", version = "1.0", description = "Course Select System"))
public class CourseSelectSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseSelectSystemApplication.class, args);
	}

}
