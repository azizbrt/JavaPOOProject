/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jframe.DBconnection;
import model.Student;

/**
 *
 * @author azizb
 */
public class StudentDAO {
    public List<Student> getAllStudents() {

        List<Student> studentList = new ArrayList<>();

        String sql = "SELECT * FROM student_details";

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("course"),
                        rs.getString("branch")
                );

                studentList.add(student);
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }
    public boolean isStudentExists(String studentName, String course) {

    boolean exists = false;

    try (Connection con = DBconnection.getConnection()) {

        String sql = "SELECT student_id FROM student_details WHERE studentName = ? AND course = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, studentName);
        pst.setString(2, course);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            exists = true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return exists;
}
    public boolean addStudent(Student student) {
     if (isStudentExists(student.getStudentName(), student.getCourse())) {
         JOptionPane.showMessageDialog(null, "This book already exists!");
        return false;
    }
    try (Connection con = DBconnection.getConnection()) {
        String sql = "INSERT INTO student_details(student_id,student_name,course ,branch ) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, student.getStudentId());
        pst.setString(2, student.getStudentName());
        pst.setString(3, student.getCourse());
        pst.setString(4, student.getBranch());
        return pst.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean updateStudent(Student student) {

    String sql = "UPDATE student_details  SET student_name=?, course=?, branch=? WHERE student_id=?";

    try (Connection con = DBconnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, student.getStudentName());
        pst.setString(2, student.getCourse());
        pst.setString(3, student.getBranch());
        pst.setInt(4, student.getStudentId());   

        return pst.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public boolean deleteStudent(int id){
    String sql = "DELETE FROM student_details  where student_id =?";
    try {
        Connection con = DBconnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        return pst.executeUpdate()>0;
        
        
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    
}
public Student getStudentById(int id) {

    String sql = "SELECT * FROM student_details WHERE student_id = ?";

    try (Connection con = DBconnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            return new Student(
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("course"),
                    rs.getString("branch")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;  // If not found
}
    
}
