package com.aniket.StudentManagementSystem.repository;

import com.aniket.StudentManagementSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Student student) {
        try {
            //SQL query to add the student into the table
            String sql = "INSERT INTO studentInfo(studentId, studentName, " +
                    "dateOfBirth, gender, contactInformation, courseName, grades, comments) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

            //return the rows affected count
            return template.update(sql,
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getDateOfBirth(),
                    student.getGender(),
                    student.getContactInformation(),
                    student.getCourseName(),
                    student.getGrades(),
                    student.getComments());
        } catch (DataAccessException e) {
            System.out.println("\nA data access error occurred while saving student information: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
        //indicates the error is occurred
        return 0;
    }

    public int delete(Integer studentId, String studentName) {
        try {
            String sql = "DELETE FROM studentInfo WHERE studentId = ? AND studentName = ?";

            //after deleting some rows is affected that count is return
            return template.update(sql, studentId, studentName);
        } catch (DataAccessException e) {
            System.out.println("\nA data access error occurred while saving student information: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
        //indicate error occurred
        return 0;
    }

    public List<Student> read(Integer studentId) {
        try {
            RowMapper<Student> rowMapper = (rs, rowNum) -> {
                System.out.println("Student ID: " + rs.getInt("studentId") + "\n" +
                        "Student name: " + rs.getString("studentName") + "\n" +
                        "Student date of birth: " + rs.getString("dateOfBirth") + "\n" +
                        "Student gender: " + rs.getString("gender") + "\n" +
                        "Student contact information: " + rs.getString("contactInformation") + "\n" +
                        "Student course name: " + rs.getString("courseName") + "\n" +
                        "Student grades: " + rs.getString("grades") + "\n" +
                        "Student comments: " + rs.getString("comments"));
                return new Student();
            };


            String sql = "SELECT * FROM studentinfo WHERE studentId = ?";
            Object[] paraValues = {studentId};

//        template.query(sql, new Object[]{studentId}, rowMapper);

            return template.query(sql, paraValues, rowMapper);
        } catch (DataAccessException e) {
            System.out.println("\nA data access error occurred while saving student information: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
        //indicate error occurred
        return new ArrayList<>();
    }

    public int update(Integer studentId, String updateColName, String updatedValue) {
        try {
            String sql = "UPDATE studentInfo SET " + updateColName + " = ? WHERE studentId = ?";
            return template.update(sql, updatedValue, studentId);
        } catch (DataAccessException e) {
            System.out.println("\nA data access error occurred while saving student information: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
        //indicates error occurred
        return 0;
    }
}

//this is useful for the read() method
//object[] {} //also write Integer because inside the array only Integer value in present
//class Demo {
//    int[] disp() {
//        return new int[]{1, 2};
//    }
//    public static void main(String[] args) {
//        Demo d=new Demo();
//        int[] num = d.disp();
//        for (int n : num) {
//            System.out.println(n);
//        }
//    }
//}