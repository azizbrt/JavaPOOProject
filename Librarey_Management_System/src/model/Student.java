/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author azizb
 */
public class Student {

    
    private int studentId;
    private String studentName;
    private String course;
    private String branch;
    
    
    public Student(String studentName,String course,String branch){
        this.studentName= studentName;
        this.course= course;
        this.branch=branch;
    }
    public Student(int studentId, String studentName,String course,String branch){
        this.studentId= studentId;
        this.studentName= studentName;
        this.course= course;
        this.branch=branch;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    
    
}
