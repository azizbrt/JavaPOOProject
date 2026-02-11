package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jframe.DBconnection;
import model.User;

public class UserDAO {

    // Insert new user
    public boolean insertUser(User user) {

        boolean isInserted = false;

        try {
            Connection con = DBconnection.getConnection();

            String sql = "INSERT INTO users(name, email, password, contact) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getPhone());

            int rowCount = pst.executeUpdate();
            isInserted = rowCount > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }

    // Check if email already exists
    public boolean isEmailExists(String email) {

        boolean exists = false;

        try {
            Connection con = DBconnection.getConnection();

            String sql = "SELECT id FROM users WHERE email = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();
            exists = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
    public boolean login(User user){
        Boolean isValid= false;
        try {
            Connection con= DBconnection.getConnection();
            String sql = "SELECT id from users where name = ? and password = ?";
            PreparedStatement pst = con.prepareCall(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getPassword());
            
            ResultSet rs = pst.executeQuery();
            isValid = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

}
