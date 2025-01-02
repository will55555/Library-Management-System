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

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
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


        System.out.println("Welcome to the library Management System");
        System.out.println("Make a selection:");
        System.out.println("1-Book operations");
        System.out.println("2-Member operations");
        short operation = input.nextShort();
        if (operation == 1) {

            while (true) {
                System.out.println("""
                        *=======Nurse-patient-management-app===========*
                        |   Welcome to the library Management System   |
                        |    The following options are available       |
                        |                                              |
                        | 1) Add book                                  |
                        | 2) Search book by ISBN                       |
                        | 3) Display all books                         |
                        | 4) Borrow a book                             |
                        | 5) Update a book                             |
                        | 6) Return a book                             |
                        | 7) Exit the program                          |
                        *==============================================*
                        """);
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
                        System.out.println("Enter isbn of book you want to find:");
                        books.setIsbn(input.nextLong());

                        bookRepository.searchBooksById(books.getIsbn());


                        break;

                    case 3:
                        //bookRepository.displayAllBooks().forEach(System.out::println);
                        bookRepository.displayAllBooks();
                        break;

                    case 4:
                        // borrow a book


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


                        /*if (LocalDate.parse(borrowingOperations.getDueDate()).isBefore(LocalDate.now())) {
                            borrowingOperations.setStatus("Overdue");
                        } else*/
                        borrowingOperations.setStatus("Borrowed");


                        System.out.println("status set to status: " + borrowingOperations.getStatus());

                        borrowingOpsRepository.borrowBook(borrowingOperations);


                        break;
                    case 5:
                        //update book info
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
                    case 6:
                        // return book changes status and borrow count

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
                    case 7:
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
        if(operation==2)
        {

            while (true) {
                System.out.println("""
                    *=======Nurse-patient-management-app===========*
                    |   Welcome to the library Management System   |
                    |    The following options are available       |
                    | 1) Add new member                            |
                    | 2) Update existing member                    |
                    | 3) Remove member                             |
                    | 4) find member by id                         |
                    | 5) Display all members                       |
                    | 6) Exit the program                          |
                    *==============================================*
                    """);
                int answer = input.nextInt();
                switch (answer) {

                    case 1:
                        //add new member
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
                        member = new MembersManagement(member.getfName(), member.getlName(), member.getMemberId(),
                                member.isStatus(),member.getBooksBorrowed(),member.getEmail());
                        member.setBooksBorrowed(input.nextInt());


                        break;
                    case 2:
                        //update existing member
                        //better way to do would be to ask which info needs to be updated
                        //
                        System.out.println("Update member info");
                        System.out.println("Enter member id:");
                        member.setMemberId(input.nextInt());
                        input.nextLine();

                        System.out.println("Current member info:");
                        membersRepository.findMemberById(member.getMemberId());

                        System.out.println("Update member first name (press enter to skip): ");
                        member.setfName(input.nextLine());
                        if (member.getfName().isEmpty()) {
                            System.out.println("Input was empty. Skipping...");
                        } else {
                            System.out.println("You entered: " + member.getfName());
                        }
                        System.out.println("Update member last name (press enter to skip): ");
                        member.setlName(input.nextLine());
                        if (member.getlName().isEmpty()) {
                            System.out.println("Input was empty. Skipping...");
                        } else {
                            System.out.println("You entered: " + member.getlName());
                        }
                        System.out.println("Update member email (press enter to skip): ");
                        member.setEmail(input.nextLine());
                        if (member.getEmail().isEmpty()) {
                            System.out.println("Input was empty. Skipping...");
                        } else {
                            System.out.println("You entered: " + member.getEmail());
                        }

                        System.out.println("Update member status");
                        member.setStatus(input.nextLine());
                        if (member.isStatus().isEmpty()) {
                            System.out.println("Input was empty. Skipping...");
                        } else {
                            System.out.println("You entered: " + member.isStatus());
                        }

                        System.out.println("Amount of books borrowed:");
                        member.setBooksBorrowed(input.nextInt());
                        if (member.getBooksBorrowed()==0) {
                            System.out.println("Input was empty. Skipping...");
                        } else {
                            System.out.println("You entered: " + member.getBooksBorrowed());
                        }

                        membersRepository.memberInfoUpdate(member);

                        System.out.println("Updated Member Info :");
                        membersRepository.findMemberById(member.getMemberId());


                        break;
                    case 3:
                        //remove existing member
                        System.out.println("Enter the ID of the member you want to remove:");
                        member.setMemberId(input.nextInt());

                        membersRepository.deleteMemberById(member.getMemberId());

                        break;
                    case 4:
                        //find member by id
                        System.out.println("Enter the ID of the member you want to find:");
                        member.setMemberId(input.nextInt());

                        membersRepository.findMemberById(member.getMemberId());

                        break;

                    case 5:
                        membersRepository.showAllMembers();
                        break;
                    case 7:

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

                    case 6:
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
    }