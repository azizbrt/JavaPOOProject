package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jframe.DBconnection;

import model.Book;

public class BookDAO {

    public List<Book> getAllBooks() {

        List<Book> bookList = new ArrayList<>();

        String sql = "SELECT * FROM books";

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                );

                bookList.add(book);
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }
    public boolean isBookExists(String title, String author) {

    boolean exists = false;

    try (Connection con = DBconnection.getConnection()) {

        String sql = "SELECT id FROM books WHERE title = ? AND author = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, title);
        pst.setString(2, author);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            exists = true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return exists;
}

public boolean addBook(Book book) {
     if (isBookExists(book.getTitle(), book.getAuthor())) {
         JOptionPane.showMessageDialog(null, "This book already exists!");
        return false;
    }
    try (Connection con = DBconnection.getConnection()) {
        String sql = "INSERT INTO books(id,title, author, quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, book.getId());
        pst.setString(2, book.getTitle());
        pst.setString(3, book.getAuthor());
        pst.setInt(4, book.getQuantity());
        return pst.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public boolean updateBook(Book book) {

    String sql = "UPDATE books SET title=?, author=?, quantity=? WHERE id=?";

    try (Connection con = DBconnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, book.getTitle());
        pst.setString(2, book.getAuthor());
        pst.setInt(3, book.getQuantity());
        pst.setInt(4, book.getId());   

        return pst.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public boolean deleteBook(int id){
    String sql = "DELETE FROM books where id =?";
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



}
