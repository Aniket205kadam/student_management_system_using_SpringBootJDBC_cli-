package com.aniket.StudentManagementSystem.service;

import com.aniket.StudentManagementSystem.model.Student;
import com.aniket.StudentManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;
    @Autowired
    private Student student;

    //take input using the Scanner class
    private final Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        try {
            //get some inputs form the administrators
            System.out.print("Enter student ID: ");
            Integer studentId = scanner.nextInt();

            //Consume newline character
            scanner.nextLine();

            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            System.out.print("Enter student DOB: ");
            String dob = scanner.next();

            System.out.print("Enter student gender: ");
            String gender = scanner.next();

            System.out.print("Enter student contact information either mobile number or gmail id: ");
            String contactInfo = scanner.next();

            //Consume newline character
            scanner.nextLine();

            //yes or no
            System.out.print("You want to add the course name of the student: ");

            String courseName = null;
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.print("Enter student course name: ");
                courseName = scanner.nextLine();
            }

            System.out.print("You want to add the grades of the student: ");

            String grades = null;
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.print("Enter student grades: ");
                grades = scanner.next();
            }

            //Consume newline character
            scanner.nextLine();

            System.out.print("You want to add the comments for the students: ");

            String comments = null;
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.print("Enter comments or notes for the student: ");
                comments = scanner.nextLine();
            }

            //set the values to the student entity
            student.setStudentId(studentId);
            student.setStudentName(studentName);
            student.setDateOfBirth(dob);
            student.setGender(gender);
            student.setContactInformation(contactInfo);
            student.setCourseName(courseName);
            student.setGrades(grades);
            student.setComments(comments);

            int rowsAffected = repository.save(student);

            //print the successes or failed message
            if (rowsAffected == 0) {
                //Failed to save student info
                System.out.println("\nFailed to save the student information.");
            } else {
                //the rowAffected is < then 0 means success
                System.out.println("\nStudent information saved successfully..!");
            }
        } catch (InputMismatchException e) {
            //handling the input mismatch exception
            System.out.println("\nInvalid input format. Please enter the correct information.");
        }  catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
    }

    public void deleteStudent() {
        try {
            System.out.print("Please enter the ID of the student you wish to delete: ");
            Integer studentId = scanner.nextInt();

            //Consume newline character
            scanner.nextLine();

            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            int rowsAffected = repository.delete(studentId, studentName);

            if (rowsAffected == 0) {
                //failed to delete this student
                System.out.println("\nDeletion failed: Unable to find a student with the provided ID.");
            } else {
                System.out.println("\nStudent successfully deleted..!");
            }
        }catch (InputMismatchException e) {
            //handling the input mismatch exception
            System.out.println("\nInvalid input format. Please enter the correct information.");
        }  catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
    }

    public void showStudentDetails() {
        try {
            System.out.print("Please enter the ID of the student to view details: ");
            Integer studentId = scanner.nextInt();

            List<Student> idExistOrNot = repository.read(studentId);

            if (idExistOrNot.isEmpty()) {
                System.out.println("Reading failed: Unable to find a student with the provided ID.");
            }
        } catch (InputMismatchException e) {
            //handling the input mismatch exception
            System.out.println("\nInvalid input format. Please enter the correct information.");
        }  catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
    }

    public void updateStudentDetails() {
        try {
            System.out.print("Please enter the ID of the student whose details you want to update: ");
            Integer studentId = scanner.nextInt();

            //Consume newline character
            scanner.nextLine();

            System.out.println("What information would you like to update?");
            System.out.println("(Name, Date of Birth (DOB), Gender, Contact, Course, Grades, Comments): ");

            String choice = scanner.nextLine();

            String updateColName = null;

            if (choice.equalsIgnoreCase("name")) updateColName = "studentName";
            else if (choice.equalsIgnoreCase("data of birth") || choice.equalsIgnoreCase("dob"))
                updateColName = "dateOfBirth";
            else if (choice.equalsIgnoreCase("gender")) updateColName = "gender";
            else if (choice.equalsIgnoreCase("contact")) updateColName = "contactInformation";
            else if (choice.equalsIgnoreCase("course")) updateColName = "courseName";
            else if (choice.equalsIgnoreCase("grades")) updateColName = "grades";
            else if (choice.equalsIgnoreCase("comments")) updateColName = "comments";
            else {
                System.out.println("\nEnter wrong keyWord..!");
                return;
            }


            System.out.print("\nEnter updated " + updateColName + ": ");
            String updatedValue = scanner.nextLine();

            int rowsAffected = repository.update(studentId, updateColName, updatedValue);

            if (rowsAffected == 0) {
                //update the student details is failed
                System.out.println("\nFailed to update student details.");
            } else {
                //update success
                System.out.println("\nStudent details successfully updated..!");
            }
        } catch (InputMismatchException e) {
            //handling the input mismatch exception
            System.out.println("\nInvalid input format. Please enter the correct information.");
        }  catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
    }

    //close the recourse
    public void closeRecourse() {
        scanner.close();
    }
}