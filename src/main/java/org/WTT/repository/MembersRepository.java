package org.WTT.repository;

import org.WTT.configuration.DatabaseConnection;
import org.WTT.service.MembersManagement;
import org.WTT.service.MembersManagement;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MembersRepository {
    private  static Connection connection;

    public MembersRepository() {
        connection = DatabaseConnection.getConnection();
    }

    //Register new library members
    public boolean newMember(MembersManagement member)
    {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO members (member_id, first_name, last_name, email, member_status, members_borrowed) VALUES (?, ?, ?, ?, ?,?)")) {
            System.out.println("Setting member ID: " + member.getMemberId());
            statement.setLong(1, member.getMemberId());
            System.out.println("Setting member first name: " + member.getfName());//
            statement.setString(2, member.getfName());
            System.out.println("Setting member last name: " + member.getlName());
            statement.setString(3, member.getlName());
            System.out.println("Setting member email: " + member.getEmail());
            statement.setString(4, member.getEmail());
            System.out.println("Setting member status: " + member.isStatus());
            statement.setString(5, String.valueOf(member.isStatus()));
            System.out.println("Setting amount of members borrowed: " + member.getBooksBorrowed());
            statement.setInt(6, member.getBooksBorrowed());
            statement.executeUpdate();
            System.out.println("member added successfully!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }
    //Update member information
    public void  memberInfoUpdate(MembersManagement member){
        try {
            String sql = "UPDATE members SET first_name, last_name, email, member_status, members_borrowed? WHERE member_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getfName());
            statement.setString(2, member.getlName());
            statement.setString(3, member.getEmail());
            statement.setString(4, member.isStatus());
            statement.setInt(5, member.getBooksBorrowed());
            
            // Execute the update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Display member details in the console
                System.out.println("member Details Updated:");
                System.out.println("member ID: " + member.getMemberId());
                System.out.println("First name: " + member.getfName());
                System.out.println("Last name: " + member.getlName());
                System.out.println("email: " + member.getEmail());
                System.out.println("Member status: " + member.isStatus());
                System.out.println("Amount of books borrowed: " + member.getBooksBorrowed());
            } else {
                System.out.println("No record found with id: " + member.getMemberId());
            }
        } catch (SQLException e) {
            System.out.println("Error updating member: " + e.getMessage());
        }

    }
    //Deactivate/reactivate member accounts
    public void deleteMemberById(int id)
    {
        try {
            String sql = "DELETE FROM members WHERE member_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowDeleted = statement.executeUpdate();
            if(rowDeleted>0) {
                System.out.println("member record with User id " + id + " has been deleted successfully.");
            } else {
                System.out.println("No record found with User id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }
    //Search for members by ID
    public MembersManagement findMemberById(int id){
        MembersManagement member = null;
        String sql = "SELECT * FROM members WHERE member_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id );
            try(ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Enter User id: " + resultSet.next());
                member = new MembersManagement();
                member.setMemberId(resultSet.getInt("member_id"));
                member.setfName(resultSet.getString("first_name"));
                member.setlName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setStatus(resultSet.getString("member_status"));
                member.setBooksBorrowed(resultSet.getInt("books_borrowed"));
                // Displaying member details in the console
                System.out.println("member Details:");
                System.out.println("Member id: " + member.getMemberId());
                System.out.println("first name: " + member.getfName());
                System.out.println("last name: " + member.getlName());
                System.out.println("email : " + member.getEmail());
                System.out.println("member status: " + member.isStatus());
                System.out.println("Amount borrowed: " + member.getBooksBorrowed());

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return member;
    }

    //nameTrack membership status
    public void membershipStatus(){

    }
    public List<MembersManagement> showAllMembers(){

        List<MembersManagement> members = new LinkedList<>();

        String sql = "SELECT * FROM members";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()) {
                MembersManagement member = new MembersManagement();
                member.setMemberId(resultSet.getInt("member_id"));
                member.setfName(resultSet.getString("first_name"));
                member.setlName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setStatus(resultSet.getString("member_status"));
                member.setBooksBorrowed(resultSet.getInt("books_borrowed"));
                members.add(member);
                //int columnsNumber = resultSet.getColumnCount();
                //member = null;
                // Display each record
                System.out.println("member Details:");
                System.out.println("Member id: " + member.getMemberId());
                System.out.println("first name: " + member.getfName());
                System.out.println("last name: " + member.getlName());
                System.out.println("email : " + member.getEmail());
                System.out.println("member status: " + member.isStatus());
                System.out.println("Amount borrowed: " + member.getBooksBorrowed());
                System.out.println("---------------------------");
            }
            // Close the resources
            //con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return members;
    }

    //Generate member activity reports
    public void memberActivity(){
        //shows how many members a member has borrow with other info
        //format:
        // member name
        //members borrowed amount lifetime
        //current count of members borrowed
        //due dates
        // any overdue items
    }



}