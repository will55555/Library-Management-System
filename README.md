# **Project Title: Library Management System**

## Project Overview:
The system should allow librarians to manage books, library members, and the book borrowing process through a console-based interface. 
This project emphasizes database operations, object-oriented design, and proper error handling.
Core Technical Requirements:Use Java 8 features including Stream API, Lambda expressions, and the new Date/Time API
Implement JDBC for database connectivity without any ORM frameworks = no spring boot
Apply proper exception handling throughout the application 
Include logging functionality to track operations and errors
Create a clean, menu-driven console interface
Follow object-oriented principles (inheritance, encapsulation, polymorphism)

## Database Requirements:
three primary tables: books, members, and borrow_records. The database design should enforce referential integrity through foreign keys and include appropriate constraints for data validation.


## Functional Requirements:

### Book Management class:

AddNewbook(int ISBN, string author, string title, int quantity) // add new book to the library with details like ISBN, title, author, and quantity
UpdateExistingBookInfo()
 RemoveBooks() //removes books from the system
searchBooksById(int ISBN)//Search books by various criteria (ISBN, title, author)
displayAllBooks()//Display available copies of each book
GenerateReport() //Generate reports of all books or filtered by availability

### Member Management Class:
#### Methods:
Register new library members
Update member information
Deactivate/reactivate member accounts
Search for members by ID 
nameTrack membership status
Generate member activity reports

### Borrowing Operations class:
#### Methods:
Check out books to members 
Process book returns 
Calculate and track due dates 
Handle overdue scenarios
Generate borrowing history for specific members
List all currently borrowed books

### Exception handling:
Members can borrow up to 3 books simultaneously
The standard borrowing period is 14 days
Members cannot borrow books if they have overdue items
Books must have at least one copy available to be borrowed
The system should prevent deletion of books that are currently borrowed
Members with expired membership cannot borrow books

### Technical Implementation Guidelines:
Architecture:Implement using a layered architecture (DAO, Service, and Presentation layers)
Use the DAO pattern for database operations
Implement service classes for business logic
Create utility classes for common operations
Error Handling:Custom exceptions for business rule violations
Proper JDBC exception handling
Input validation at all levels
Graceful error messages for users

### Data Validation:
ISBN format validation
Email format validation for members
Date validation for borrowing operations
Quantity validation for books

### Optional Enhancement Suggestions: 
Implement a fine calculation system for overdue books
Add a reservation system for books
Create a simple authentication system for librarians
Generate PDF reports for borrowing history
Implement a simple backup system for the database
Add email notification functionality for due dates

### Testing Requirements:
Unit tests for core business logic
Database operation tests
Input validation tests
Integration tests for key workflows

### Documentation Requirements:
Java documentation (Javadoc) for all classes and methods
README file with setup instructions
Database schema documentation
User manual for the console interface

