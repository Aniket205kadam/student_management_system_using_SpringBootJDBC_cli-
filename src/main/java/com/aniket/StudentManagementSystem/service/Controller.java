package com.aniket.StudentManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class Controller {

    private StudentService service;

    @Autowired
    public void setService(StudentService service) {
        this.service = service;
    }

    private Scanner scanner = new Scanner(System.in);

    public void mainLoop() {
        startApp();

        String command = "START";

        try {
            while (!command.equals("STOP")) {
                printOptions();
                System.out.print("Enter your command: ");
                command = scanner.nextLine();

                //toUpperCase() added because some user entering the lower-case command then it is also work
                command = command.toUpperCase();

                switch (command) {
                    case "CREATE" -> service.addStudent();

                    case "UPDATE" -> service.updateStudentDetails();

                    case "READ" -> service.showStudentDetails();

                    case "DELETE" -> service.deleteStudent();

                    case "STOP" -> closeApp();

                    default -> wrongCommand();
                }
            }
        } catch (InputMismatchException e) {
            //handling the input mismatch exception
            System.out.println("\nInvalid input format. Please enter the correct information.");
        }  catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }

    }

    private void closeApp() {
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("||  Exiting the application. Goodbye!  ||");
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        service.closeRecourse();
    }

    private void startApp() {
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("||  Application started successfully   ||");
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
    }
    private void wrongCommand() {
        System.out.println("Oops! That's not a valid command. Please enter one of the following:" +
                " CREATE, UPDATE, READ, DELETE.");
    }

    private void printOptions() {
        //CURD
        System.out.println("\nCREATE -> add the student in the database");
        System.out.println("UPDATE -> update the student information");
        System.out.println("READ -> view the information of the student");
        System.out.println("DELETE -> delete the student information");
        System.out.println("STOP -> stop the application");
    }

}
