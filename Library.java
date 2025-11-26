import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Library {
    private int maxBooks = 3;
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private Queue<BorrowRequest> borrowQueue;
    private Queue<ReturnRequest> returnQueue;
    private Deque<Action> actionStack;

    public Library(){
        books = new ArrayList<>();
        users = new ArrayList<>();
        borrowQueue  = new LinkedList<>();
        returnQueue  = new LinkedList<>();
        actionStack = new LinkedList<>();
    }

    public void addBook(Book b){
        books.add(b);
    }

    public void addUser(User u){
        users.add(u);
    }

    public void borrowRequest(String userID, String bookID){
        BorrowRequest request = new BorrowRequest(bookID, userID);
        borrowQueue.add(request);
    }


    public void borrowBook(){
        BorrowRequest request = borrowQueue.peek();
        if (request == null){
            System.out.println("No books in queue");
            return;
        }
        String userId = request.getUserID();
        String bookId = request.getBookID();

        User borrower = null;
        for (User u: users){
            if(u.getUserID().equals(userId)){
                borrower = u;
                break;
            }
        }
        if (borrower == null){
            System.out.println("No user found with id: " + userId);
            return;
        }

        Book borrowedBook = null;
        for (Book b: books){
            if(b.getBookID().equals(bookId)){
                borrowedBook = b;
                break;
            }
        }
        if (borrowedBook == null){
            System.out.println("No book found with id: " + bookId);
            return;
        }

        if (borrowedBook.getAvailable() == false){
            System.out.println("Book is currently unaviable");
            return;
        }

        if (borrower.getBooks().size() >= maxBooks) {
            System.out.println("User has already borrowed the maximum number of books.");
            return;
        }

        borrower.addBook(borrowedBook);
        borrowQueue.poll();
        borrowedBook.setAvailable();
        System.out.println(borrower.getName() + " borrowed " + borrowedBook.getTitle());
    }

    public void returnRequest(String userId, String bookId){
        ReturnRequest request = new ReturnRequest(bookId, userId);
        returnQueue.add(request);
    }

    public void returnBook(){
        ReturnRequest request = returnQueue.peek();
        if (request == null){
            System.out.println("No books in queue");
            return;
        }
        String userId = request.getUserID();
        String bookId = request.getBookID();

        User borrower = null;
        for (User u: users){
            if(u.getUserID().equals(userId)){
                borrower = u;
                break;
            }
        }
        if (borrower == null){
            System.out.println("No user found with id " + userId);
            return;
        }  
        Book borrowedBook = null;
        for (Book b: books){
            if(b.getBookID().equals(bookId)){
                borrowedBook = b;
                break;
            }
        }
        if (borrowedBook == null){
            System.out.println("No book found with id: " + bookId);
            return;
        }   
        
        if (!borrower.getBooks().contains(bookId)) {
            System.out.println("Book not taken out by this user! ");
            return;
        }

        borrowedBook.setAvailable();
        borrowQueue.poll();
        System.out.println("Book returned");
        for(int i = 0; i < borrower.getBooks().size(); i++){
            if (borrower.getBooks().get(i) == bookId){
                    borrower.getBooks().remove(i);
            }
        }
    }

    public void displayAllBooks(){
        for (Book b:books){
            System.out.println(b + " Avalaible: "+ b.getAvailable());
        }
    }

    public void displayAllUsers(){
        for(User u:users){
            u.displayUser();
        }
    }

}
