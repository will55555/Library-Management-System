package org.WTT;

import org.WTT.configuration.DatabaseConnection;
import org.WTT.repository.BookRepository;
import org.WTT.repository.MembersRepository;
import org.WTT.service.BookManagement;
import org.WTT.service.MembersManagement;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            if (DatabaseConnection.getConnection().isValid(30)) {
                System.out.println("Database Connected");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        BookManagement books = new BookManagement();
        BookRepository bookRepository = new BookRepository();
        MembersManagement member = new MembersManagement();
        MembersRepository membersRepository = new MembersRepository();

        System.out.println("""
                        *=======Nurse-patient-management-app===========*
                        |   Welcome to the library Management System   |
                        |    The following options are available       |
                        |                                              |
                        | 1) Add new book                              |
                        | 2) Update existing book                      |
                        | 3) Remove book                               |
                        | 4) Search book by ISBN                       |
                        | 5) Display all books                         |
                        | 6) report                                    |
                        | 7) Add new member                            |
                        | 8) Update existing member                    |
                        | 9) Remove member                             |
                        | 10) find member by id                        |
                        | 11) Display all members                      |
                        | 12) Exit the program                         |
                        *==============================================*
                        """);
        Scanner input = new Scanner(System.in);
        int answer = input.nextInt();
        switch (answer) {
            case 1:
                input.nextLine();
                System.out.println("Setting ISBN: ");

                books.setIsbn(input.nextInt());

                System.out.println("Enter author: ");
                books.setAuthor(input.nextLine());

                System.out.println("Enter title: ");
                books.setTitle(input.nextLine());

                System.out.println("Enter amount in stock: ");
                books.setQuantity(input.nextInt());

                break;
            case 2:
                System.out.println("Enter ISBN of book you want to update: ");
                int isbn = input.nextInt();
                input.nextLine(); // Consume the newline character

                books.setIsbn(isbn);
                System.out.println("Update author: ");
                books.setAuthor(input.nextLine());

                System.out.println("Update title:");
                books.setTitle(input.nextLine());

                System.out.println("Update quantity");
                books.setQuantity(input.nextInt());

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                //bookRepository.displayAllBooks().forEach(System.out::println);
                bookRepository.displayAllBooks();
                break;
            case 6:
                break;
            case 7:
                input.nextLine();
                System.out.println("Setting member id: ");

                member.setMemberId(input.nextInt());

                System.out.println("Enter first name: ");
                member.setfName(input.nextLine());

                System.out.println("Enter last name: ");
                member.setlName(input.nextLine());

                System.out.println("Enter email: ");
                member.setEmail(input.nextLine());

                System.out.println("Enter member status: ");
                member.setStatus(input.nextLine());

                System.out.println("Enter amount of books borrowed: ");
                member.setBooksBorrowed(input.nextInt());


                break;
            case 8:
                System.out.println("Enter ISBN of book you want to update: ");
                int id = input.nextInt();
                input.nextLine();
                member.setMemberId(input.nextInt());

                System.out.println("Enter first name: ");
                member.setfName(input.nextLine());

                System.out.println("Enter last name: ");
                member.setlName(input.nextLine());

                System.out.println("Enter email: ");
                member.setEmail(input.nextLine());

                System.out.println("Enter member status: ");
                member.setStatus(input.nextLine());

                System.out.println("Enter amount of books borrowed: ");
                member.setBooksBorrowed(input.nextInt());
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                membersRepository.showAllMembers();
                break;
            case 12:
                // Display message
                System.out.println(
                        "\nThank you for using the program. Goodbye!\n");
                System.exit(0);
                break;

            default:
                System.out.println("Please select an option from the menu");


        }
    }
}