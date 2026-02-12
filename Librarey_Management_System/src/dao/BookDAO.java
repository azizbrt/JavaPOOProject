package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
public boolean addBook(Book book) {
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

}
