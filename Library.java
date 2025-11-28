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
    private int bookIDCount = 1;

    public Library(){
        books = new ArrayList<>();
        users = new ArrayList<>();
        borrowQueue  = new LinkedList<>();
        returnQueue  = new LinkedList<>();
        actionStack = new LinkedList<>();
    }

    public void addBook(String title, String author, Integer copies){
        String letter = "B";
        String bookId = letter + String.format("%03d", bookIDCount);
        bookIDCount++;
        Book b = new Book(title, author,bookId,copies);
        books.add(b);
        Action action = new Action(null, b, ActionType.ADD_BOOK);
        actionStack.push(action);
    }

    public void addBook(String title, String author){
        addBook(title,author, 1);
    }

    public void removeBook(String bookId) {
        Book b = findBook(bookId);
        if(b == null){
            System.out.println("Book doesnt exsist");
            return;
        }
        books.remove(b);
        Action action = new Action(null, b, ActionType.REMOVE_BOOK);
        actionStack.push(action);
    }

    public void addUser(User u){
        users.add(u);
    }
    
    // Creating borrow request
    public void borrowRequest(String userID, String bookID){
        BorrowRequest request = new BorrowRequest(bookID, userID);
        borrowQueue.add(request);
    }

    // Performing borrowRequest from queue of borrowRequests
    public void borrowBook(){
        BorrowRequest request = borrowQueue.peek();
        if (request == null){
            System.out.println("No books in queue");
            return;
        }
        String userId = request.getUserID();
        String bookId = request.getBookID();

        User borrower = findUser(userId); 

        if (borrower == null){
            System.out.println("No user found with id: " + userId);
            borrowQueue.poll();
            return;
        }

        Book borrowedBook = findBook(bookId);

        if (borrowedBook == null){
            System.out.println("No book found with id: " + bookId);
            borrowQueue.poll();
            return;
        }

        if (borrowedBook.getAvailable() == false){
            System.out.println("Book is currently unaviable");
            borrowQueue.poll();
            return;
        }

        if (borrower.getBooks().size() >= maxBooks) {
            System.out.println("User has already borrowed the maximum number of books.");
            borrowQueue.poll();
            return;
        }

        if (borrowedBook.getCopies() > 1) {
            borrowedBook.reduceCopies();
        } else if (borrowedBook.getCopies() == 1) {
            borrowedBook.reduceCopies();
            borrowedBook.setAvailable();
        }
        
        
        Action action = new Action(borrower.getUserID(), borrowedBook, ActionType.ISSUE);
        actionStack.push(action);
        borrower.addBook(borrowedBook);
        borrowQueue.poll();
        System.out.println(borrower.getName() + " borrowed " + borrowedBook.getTitle());
    }
    
    // Creating return request
    public void returnRequest(String userId, String bookId){
        ReturnRequest request = new ReturnRequest(bookId, userId);
        returnQueue.add(request);
    }
    
    // Performing returning book from queue of returnRequests
    public void returnBook(){
        ReturnRequest request = returnQueue.peek();
        if (request == null){
            System.out.println("No books in queue");
            return;
        }
        String userId = request.getUserID();
        String bookId = request.getBookID();

        User borrower = findUser(userId); 

        if (borrower == null){
            System.out.println("No user found with id " + userId);
            borrowQueue.poll();
            return;
        }  
        Book borrowedBook = findBook(bookId);

        if (borrowedBook == null){
            System.out.println("No book found with id: " + bookId);
            borrowQueue.poll();
            return;
        }   
        
        if (!borrower.getBooks().contains(bookId)) {
            System.out.println("Book not taken out by this user! ");
            borrowQueue.poll();
            return;
        }

        if (borrowedBook.getCopies() == 0) {
            borrowedBook.setAvailable();
            borrowedBook.increaseCopies();
        } else if (borrowedBook.getCopies() > 0) {
            borrowedBook.increaseCopies();
        }
        
        
        Action action = new Action(borrower.getUserID(), borrowedBook, ActionType.RETURN);
        actionStack.push(action);
        borrowQueue.poll();
        System.out.println("Book returned");
        borrower.getBooks().remove(borrowedBook);
    }
    
    // Displaying all books in Library
    public void displayAllBooks(){
        for (Book b:books){
            System.out.println(b);
        }
    }
    
    // Display Users in library
    public void displayAllUsers(){
        for(User u:users){
            u.displayUser();
        }
    }
    
    // Undo actions using FILO logic 
    public void undoAction(){
        if (actionStack.isEmpty()) {
            System.out.println("No actions to be undone.");
            return;
        }
        Action lastAction = actionStack.pop();
        String userId = lastAction.getUserID();
        Book book = lastAction.getBook();
        User borrower = findUser(userId); 

        if (lastAction.getAction() == ActionType.RETURN){
            if (book.getCopies() > 1) {
                book.reduceCopies();
            } else if (book.getCopies() == 1) {
                book.reduceCopies();
                book.setAvailable();
            }
            borrower.addBook(book);
            System.out.println("Action undone - " + book.getTitle() + " added back to users borrow list");
            return;
        }
        if (lastAction.getAction() == ActionType.ISSUE){
            if (book.getCopies() == 0) {
                book.setAvailable();
                book.increaseCopies();
            } else if (book.getCopies() > 0) {
                book.increaseCopies();
            }
            borrower.getBooks().remove(book);
            System.out.println("Action undone - "+ book.getTitle() + " removed from users borrowed list.");
            return;
        }
        if (lastAction.getAction() == ActionType.ADD_BOOK){
            books.remove(book);
            System.out.println("Action undone - "+ book.getTitle() + " removed.");
            return;
        }

        if (lastAction.getAction() == ActionType.REMOVE_BOOK){
            books.add(book);
            System.out.println("Action undone - "+ book.getTitle() + " readded.");
            return;
        }
    }

    // Viewing book feature
    public void viewBook(String bookId, String userId){
        User borrower = findUser(userId);
        Book viewBook = findBook(bookId); 

        if (viewBook == null){
            System.out.println("Book not found!");
        }
        if (borrower.getViewedBooks().size() > 10){
            borrower.getViewedBooks().pollLast();
        }
        System.out.println(viewBook);
        borrower.addViewedBook(viewBook);
    }

    // Show previously viewed book
    public void displayPreviouslyViewed(String userId){
        User borrower = findUser(userId);
        if (borrower.getViewedBooks().isEmpty()){
            System.out.println("No books previously viewed");
            return;
        }
        System.out.println(borrower.peekViewedBook());
    }

    // Helper method to find user
    public User findUser(String userId){
        User borrower = null;
        for (User u: users){
            if(u.getUserID().equals(userId)){
                borrower = u;
                break;
            }
        }
        return borrower;
    }

    // Helper method to find book
    public Book findBook(String bookId){
        Book borrowedBook = null;
        for (Book b: books){
            if(b.getBookID().equals(bookId)){
                borrowedBook = b;
                break;
            }
        }
        return borrowedBook;
    }
}
