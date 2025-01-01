package org.WTT.service;


import java.time.LocalDate;

public class BorrowingOperations {
    private String borrowedDate;
    private String returnedDate;
    private long isbn;
    private String dueDate;
    private String status;
    private int userId;

    public BorrowingOperations() {
    }

    public BorrowingOperations(int userId, long isbn, String borrowedDate, String returnedDate, String dueDate, String status ) {
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
        this.isbn = isbn;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getDueDate() {
        return dueDate;
    }

    public LocalDate setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}