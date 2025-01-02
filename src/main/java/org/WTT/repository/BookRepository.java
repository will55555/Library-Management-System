package org.WTT.repository;

import org.WTT.configuration.DatabaseConnection;
import org.WTT.service.BookManagement;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookRepository {
    private static Connection connection;

    public BookRepository() {
         connection = DatabaseConnection.getConnection();
    }

    public boolean addNewbook(BookManagement book) // add new book to the library with details like ISBN, title, author, and quantity
    {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO books (ISBN, author, title, quantity) VALUES (?, ?, ?, ?)")) {
            System.out.println("Setting Book ID: " + book.getIsbn());
            statement.setLong(1, book.getIsbn());
            System.out.println("Setting book author name: " + book.getAuthor());//
            statement.setString(2, book.getAuthor());
            System.out.println("Setting book title: " + book.getTitle());
            statement.setString(3, book.getTitle());
            System.out.println("Setting book quantity in stock: " + book.getQuantity());
            statement.setInt(4, book.getQuantity());
            statement.executeUpdate();
            System.out.println("book added successfully!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }

    public void updateExistingBookInfo(BookManagement book){
        try {
            String sql = "UPDATE books SET author = ?, title = ?, quantity = ? WHERE ISBN = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getQuantity());
            statement.setLong(4, book.getIsbn());


            // Execute the update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Display book details in the console
                System.out.println("book Details Updated:");
                System.out.println("Book ID/ISBN: " + book.getIsbn());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Quantity: " + book.getQuantity());
            } else {
                System.out.println("No record found with ISBN: " + book.getIsbn());
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }

    }
    public void removeBooks(int isbn) //removes books from the system
    {
        try {
            String sql = "DELETE FROM books WHERE ISBN = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, isbn);

            int rowDeleted = statement.executeUpdate();
            if(rowDeleted>0) {
                System.out.println("book record with User isbn " + isbn + " has been deleted successfully.");
            } else {
                System.out.println("No record found with User isbn: " + isbn);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
    public BookManagement searchBooksById(long isbn){

        BookManagement book = null;
        String sql = "SELECT * FROM books WHERE ISBN = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, isbn );
            try(ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Enter User id: " + resultSet.next());
                book = new BookManagement();
                book.setIsbn(resultSet.getLong("ISBN"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));
                book.setQuantity(resultSet.getInt("quantity"));
                // Displaying book details in the console
                System.out.println("Book Details:");
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Quantity: " + book.getQuantity());

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }
    //Search books by various criteria (ISBN, title, author)
    public List<BookManagement> displayAllBooks()
    {  List<BookManagement> books = new LinkedList<>();

        String sql = "SELECT * FROM books";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()) {
                BookManagement book = new BookManagement();
                book.setIsbn(resultSet.getLong("ISBN"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));
                book.setQuantity(resultSet.getInt("quantity"));
                books.add(book);
                //int columnsNumber = resultSet.getColumnCount();
                //book = null;
                // Display each record
                System.out.println("Book Details:");
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Quantity: " + book.getQuantity());
                System.out.println("---------------------------");
            }
            // Close the resources
            //con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }//Display available copies of each book

    public void GenerateReport(){

    } //Generate reports of all books or filtered by availability

}