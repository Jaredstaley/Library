import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create Library
        Library library = new Library();

        // creating and adding Books
        library.addBook("The Hobbit", "J.R.R. Tolkien", 2);
        library.addBook("1984", "George Orwell");
        library.addBook("Clean Code", "Robert C. Martin");
        library.addBook("Java Programming", "Author X");


        // Create Users
        User user1 = new User("Jared", "U001");
        User user2 = new User("Piyush", "U002");

        // Add users to library
        library.addUser(user1);
        library.addUser(user2);

        // Menu
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1) Display all books ");
            System.out.println("2) Display all users  ");
            System.out.println("3) Request to borrow a book ");
            System.out.println("4) Request to Return a book ");
            System.out.println("5) Perform borrow");
            System.out.println("6) Perform Return");
            System.out.println("7) Undo action ");
            System.out.println("8) Add book from catalog");
            System.out.println("9) Remove book from catalog");
            System.out.println("10) View book ");
            System.out.println("11) Show previously viewed books");
            System.out.println("12) Exit");
            System.out.print("Choose one of the above: ");
            int userChoice = sc.nextInt();
            sc.nextLine();
            if (userChoice == 1) {
                library.displayAllBooks();
            } else if (userChoice == 2) {
                library.displayAllUsers();
            } else if (userChoice == 3) {
                System.out.print("Enter usersID: ");
                String userID = sc.nextLine();
                System.out.print("Enter bookID: ");
                String bookID = sc.nextLine();
                library.borrowRequest(userID, bookID);
            } else if (userChoice == 4) {
                System.out.print("Enter usersID: ");
                String userID = sc.nextLine();
                System.out.print("Enter bookID: ");
                String bookID = sc.nextLine();
                library.returnRequest(userID, bookID);
            } else if (userChoice == 5) {
                library.borrowBook();
            } else if (userChoice == 6) {
                library.returnBook();
            } else if (userChoice == 7) {
                library.undoAction();
            } else if (userChoice == 8) {
                System.out.print("Enter title: ");
                String title = sc.nextLine();
                System.out.print("Enter author: ");
                String author = sc.nextLine();
                System.out.print("Enter copies: ");
                Integer copies = sc.nextInt();
                library.addBook(title, author,copies);
            } else if (userChoice == 9) {
                System.out.print("Enter bookID: ");
                String bookID = sc.nextLine();
                library.removeBook(bookID);
            } else if (userChoice == 10) {
                System.out.print("Enter usersID: ");
                String userID = sc.nextLine();
                System.out.print("Enter bookID: ");
                String bookID = sc.nextLine();
                library.viewBook(bookID,userID);
            } else if (userChoice == 11) {
                System.out.print("Enter usersID: ");
                String userID = sc.nextLine();
                library.displayPreviouslyViewed(userID);
            } else if (userChoice == 12) {
                break;
            }
        }

    }
}