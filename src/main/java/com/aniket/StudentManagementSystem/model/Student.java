package com.aniket.StudentManagementSystem.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Student {
    //A unique identifier for each student.
    private Integer studentId;
    //The student's full name.
    private String studentName;
    //The student's date of birth.
    private String dateOfBirth;
    //The student's gender.
    private String gender;
    //This could include fields for phone number, email address, and home address.
    private String contactInformation;
    //The academic course the student is enrolled in.
    private String courseName;
    //Store information about the student's grades
    private String grades;
    //Any additional comments about the student that might be relevant for administrators or teachers.
    private String comments;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", courseName='" + courseName + '\'' +
                ", grades='" + grades + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}