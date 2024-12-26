package org.WTT.service;

public class MembersManagement {
    private String fName;
    private String lName;//lastname
    private int memberId;
    private String status;
    private int booksBorrowed;
    private String email;

    public MembersManagement() {
    }

    public MembersManagement(String fName, String lName, int memberId, String status, int booksBorrowed, String email) {
        this.fName = fName;
        this.lName = lName;
        this.memberId = memberId;
        this.status = status;
        this.booksBorrowed = booksBorrowed;
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(int booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}