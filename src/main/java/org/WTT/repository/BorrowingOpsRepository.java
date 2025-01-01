package org.WTT.repository;

import org.WTT.configuration.DatabaseConnection;
import org.WTT.service.BookManagement;
import org.WTT.service.BorrowingOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BorrowingOpsRepository {
    private  static Connection connection;

    public BorrowingOpsRepository() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }
    /*
        Members can borrow up to 3 borrows simultaneously

        Members cannot borrow borrows if they have overdue items
        borrows must have at least one copy available to be borrowed
        The system should prevent deletion of borrows that are currently borrowed
        Members with expired membership cannot borrow borrows
         */
    public void borrowBook(BorrowingOperations borrow){

        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO borrow_records (user_id, isbn, borrow_date, due_date, status) VALUES (?, ?, ?, ?, ?)")) {
            System.out.println("Setting User ID: " + borrow.getUserId());
            statement.setLong(1, borrow.getUserId());
            System.out.println("Setting book isbn: " + borrow.getIsbn());//
            statement.setLong(2, borrow.getIsbn());
            System.out.println("Setting borrow date: " + borrow.getBorrowedDate());
            statement.setString(3, borrow.getBorrowedDate());

            System.out.println("Setting due date: " + borrow.getDueDate());
            statement.setString(4, borrow.getDueDate());

            System.out.println("Setting status: " + borrow.getStatus());
            statement.setString(5, borrow.getStatus());
            statement.executeUpdate();
            System.out.println("borrow added successfully!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void returnBook(BorrowingOperations book){
        try {
            String sql = "UPDATE borrow_records SET return_date=?, status=? WHERE user_id = ? AND isbn=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, book.getReturnedDate());
            //statement.setString(4, book.getDueDate());
            statement.setString(2, book.getStatus());
            statement.setInt(3, book.getUserId());
            statement.setLong(4, book.getIsbn());



            // Execute the update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Display book details in the console
                System.out.println("book Details Updated:");
                System.out.println("User ID: " + book.getUserId());
                System.out.println("Book ID/ISBN: " + book.getIsbn());
                System.out.println("returned date: " + book.getReturnedDate());
                System.out.println("Status: " + book.getStatus());

            } else {
                System.out.println("No record found with user id: " + book.getUserId());
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }

    }
    public BorrowingOperations searchByUserId(int id){

        BorrowingOperations find = null;
        String sql = "SELECT * FROM borrow_records WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id );
            try(ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Enter User id: " + resultSet.next());
                find = new BorrowingOperations();
                find.setUserId(resultSet.getInt("user_id"));
                find.setIsbn(resultSet.getLong("isbn"));
                find.setBorrowedDate(resultSet.getString("borrow_date"));
                find.setDueDate(resultSet.getString("due_date"));
                find.setStatus(resultSet.getString("status"));

                // Displaying book details in the console
                System.out.println("Borrow Details:");
                System.out.println("User Id: " + find.getUserId());
                System.out.println("ISBN: " + find.getIsbn());
                System.out.println("Borrowed date: " + find.getBorrowedDate());
                System.out.println("Due date: " + find.getDueDate());
                System.out.println("status: " + find.getStatus());

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return find;
    }

}