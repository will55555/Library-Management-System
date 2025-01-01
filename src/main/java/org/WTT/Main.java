package org.WTT;

import org.WTT.configuration.DatabaseConnection;
import org.WTT.repository.BookRepository;
import org.WTT.repository.BorrowingOpsRepository;
import org.WTT.repository.MembersRepository;
import org.WTT.service.BookManagement;
import org.WTT.service.BorrowingOperations;
import org.WTT.service.MembersManagement;

import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
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
        BorrowingOperations borrowingOperations = new BorrowingOperations();
        BorrowingOpsRepository borrowingOpsRepository = new BorrowingOpsRepository();


        //welcome are you a member?(y or n) if n go to member ops
        //if y go to borrow ops

        while (true) {
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
                | 12) Borrow a book                            |
                | 13) Return a book                            |
                | 14) Exit the program                         |
                *==============================================*
                """);
            Scanner input = new Scanner(System.in);
            int answer = input.nextInt();
            switch (answer) {
                case 1:
                    input.nextLine();
                    System.out.println("Setting ISBN: ");

                    books.setIsbn(input.nextLong());

                    System.out.println("Enter author: ");
                    books.setAuthor(input.nextLine());

                    System.out.println("Enter title: ");
                    books.setTitle(input.nextLine());

                    System.out.println("Enter amount in stock: ");
                    books.setQuantity(input.nextInt());

                    bookRepository.addNewbook(books);

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
                    //break;
                case 5:
                    //bookRepository.displayAllBooks().forEach(System.out::println);
                    bookRepository.displayAllBooks();
                    break;
                case 6:
                    //break;
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
                    //break;
                case 10:
                    //break;
                case 11:
                    membersRepository.showAllMembers();
                    break;
                case 12:


                    System.out.println("Enter user id: ");

                    borrowingOperations.setUserId(input.nextInt());
                    input.nextLine();
                    System.out.println("Enter book isbn: ");
                    borrowingOperations.setIsbn(input.nextLong());
                    input.nextLine();
                    System.out.println("Enter borrowed date (yyyy-MM-dd): ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    borrowingOperations.setBorrowedDate(input.nextLine());

                    LocalDate customDate = LocalDate.parse(borrowingOperations.getBorrowedDate(), formatter);


                    // Example of adding days to the input date to set due date
                    int daysToAdd = 14;

                    LocalDate dueDate = customDate.plusDays(daysToAdd);
                    borrowingOperations.setDueDate(String.valueOf(dueDate));
                    System.out.println("Due date set to : " + borrowingOperations.getDueDate());


                    if (LocalDate.parse(borrowingOperations.getDueDate()).isBefore(LocalDate.now())) {
                        borrowingOperations.setStatus("Overdue");
                    } else
                        borrowingOperations.setStatus("Borrowed");


                    System.out.println("status set to status: " + borrowingOperations.getStatus());

                    borrowingOpsRepository.borrowBook(borrowingOperations);


                    break;
                case 13:

                    System.out.println("Enter user ID");
                    borrowingOperations.setUserId(input.nextInt());
                    input.nextLine();
                    borrowingOpsRepository.searchByUserId(borrowingOperations.getUserId());
                    System.out.println("Enter ISBN:");
                    borrowingOperations.setIsbn(input.nextLong());
                    input.nextLine();

                    //DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    System.out.print("Enter returned date (yyyy-MM-dd): ");

                    borrowingOperations.setReturnedDate(input.nextLine());

                    borrowingOperations.setStatus("Returned");

                    borrowingOpsRepository.returnBook(borrowingOperations);
                    // count -- for userid borrows

                    break;
                case 14:
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
    }