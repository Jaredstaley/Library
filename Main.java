import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create Library
        Library library = new Library();

        // Create Books
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", "B001");
        Book book2 = new Book("1984", "George Orwell", "B002");
        Book book3 = new Book("Clean Code", "Robert C. Martin", "B003");
        Book book4 = new Book("Java Programming", "Author X", "B004");

        // Add books to library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

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
            System.out.println("3) Borrow a book ");
            System.out.println("4) Return a book ");
            System.out.println("5) Exit");
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
                System.out.print("Enter usersID: ");
                String bookID = sc.nextLine();
                library.borrowBook(userID, bookID);
            } else if (userChoice == 4) {
                System.out.print("Enter usersID: ");
                String userID = sc.nextLine();
                System.out.print("Enter usersID: ");
                String bookID = sc.nextLine();
                library.returnBook(userID, bookID);
            } else if (userChoice == 5) {
                break;
            }
        }

    }
}