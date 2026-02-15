package model;

import java.util.Date;

public class IssueBook {
    private int issueId;
    private int bookId;
    private int studentId;
    private Date issueDate;
    private Date dueDate;
    private String status;

    // Constructor for issuing a new book
    public IssueBook(int bookId, int studentId, Date issueDate, Date dueDate, String status) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Constructor for fetching from DB (all fields)
    public IssueBook(int issueId, int bookId, int studentId, Date issueDate, Date dueDate, String status) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and setters
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
