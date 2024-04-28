package com.aniket.StudentManagementSystem;

import com.aniket.StudentManagementSystem.service.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StudentManagementSystemUsingSpringJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StudentManagementSystemUsingSpringJdbcApplication.class, args);

		Controller controller = context.getBean(Controller.class);

		controller.mainLoop();

	}

}
