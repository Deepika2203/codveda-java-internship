
import java.sql.*;
import java.util.Scanner;

public class LibraryManagement {

    static final String URL = "jdbc:mysql://localhost:3306/library_db";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            while (true) {
                System.out.println("\n1. Add Book");
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
                System.out.println("4. Exit");
                System.out.print("Choose: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addBook(con, sc);
                    case 2 -> borrowBook(con, sc);
                    case 3 -> returnBook(con, sc);
                    case 4 -> {
                        con.close();
                        System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addBook(Connection con, Scanner sc) throws Exception {
        System.out.print("Title: ");
        sc.nextLine();
        String title = sc.nextLine();

        System.out.print("Author: ");
        String author = sc.nextLine();

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO books(title, author, available) VALUES (?, ?, true)"
        );
        ps.setString(1, title);
        ps.setString(2, author);
        ps.executeUpdate();

        System.out.println("Book added successfully");
    }

    static void borrowBook(Connection con, Scanner sc) throws Exception {
    System.out.print("Book ID: ");
    int bookId = sc.nextInt();

    // Check if book exists
    PreparedStatement check = con.prepareStatement(
            "SELECT available FROM books WHERE book_id=?"
    );
    check.setInt(1, bookId);
    ResultSet rs = check.executeQuery();

    // Book ID does not exist
    if (!rs.next()) {
        System.out.println("Invalid Book ID");
        return;
    }

    // Book exists but already borrowed
    if (!rs.getBoolean("available")) {
        System.out.println("Book not available");
        return;
    }

    // Borrow the book
    PreparedStatement update = con.prepareStatement(
            "UPDATE books SET available=false WHERE book_id=?"
    );
    update.setInt(1, bookId);
    update.executeUpdate();

    PreparedStatement txn = con.prepareStatement(
            "INSERT INTO transactions(book_id, issue_date) VALUES (?, CURDATE())"
    );
    txn.setInt(1, bookId);
    txn.executeUpdate();

    System.out.println("Book borrowed successfully");
}


    static void returnBook(Connection con, Scanner sc) throws Exception {
    System.out.print("Book ID: ");
    int bookId = sc.nextInt();

    PreparedStatement check = con.prepareStatement(
            "SELECT available FROM books WHERE book_id=?"
    );
    check.setInt(1, bookId);
    ResultSet rs = check.executeQuery();

    if (!rs.next()) {
        System.out.println("Invalid Book ID");
        return;
    }

    if (rs.getBoolean("available")) {
        System.out.println("Book was not borrowed");
        return;
    }

    PreparedStatement update = con.prepareStatement(
            "UPDATE books SET available=true WHERE book_id=?"
    );
    update.setInt(1, bookId);
    update.executeUpdate();

    PreparedStatement txn = con.prepareStatement(
            "UPDATE transactions SET return_date=CURDATE() WHERE book_id=? AND return_date IS NULL"
    );
    txn.setInt(1, bookId);
    txn.executeUpdate();

    System.out.println("Book returned successfully");
}
}
