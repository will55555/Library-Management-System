package org.WTT.repository;

import org.WTT.configuration.DatabaseConnection;

import java.sql.Connection;

public class BorrowingOpsRepository {
    //private  static Connection connection;
    public BorrowingOpsRepository() {
        Connection connection = DatabaseConnection.getConnection();
    }

    public void borrow(){


    }
     /*
    Members can borrow up to 3 books simultaneously
    The standard borrowing period is 14 days
    Members cannot borrow books if they have overdue items
    Books must have at least one copy available to be borrowed
    The system should prevent deletion of books that are currently borrowed
    Members with expired membership cannot borrow books
     */
}