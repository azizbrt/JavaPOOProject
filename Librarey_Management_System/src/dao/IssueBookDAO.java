package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;
import jframe.DBconnection;
import model.IssueBook;

public class IssueBookDAO {

    public boolean addIssue(IssueBook issue) {
        String sql = "INSERT INTO issue_details(book_id, student_id, issue_date, return_date, status) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, issue.getBookId());
            pst.setInt(2, issue.getStudentId());

            // Convert java.util.Date to java.sql.Date
            pst.setDate(3, new java.sql.Date(issue.getIssueDate().getTime()));

            if (issue.getDueDate()!= null) {
                pst.setDate(4, new java.sql.Date(issue.getDueDate().getTime()));
            } else {
                pst.setDate(4, null);
            }

            pst.setString(5, issue.getStatus());

            return pst.executeUpdate() > 0; 

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
