import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class User {
    private String name;
    private String userID;
    private ArrayList<String> borrowedBooks = new ArrayList<>();
    private Deque<Book> viewedBooks = new LinkedList<>();

    public User(String name, String userID){
        this.name = name;
        this.userID = userID;
    }

    public String getName(){
        return name;
    }

    public String getUserID(){
        return userID;
    }

    public ArrayList<String> getBooks(){
        return borrowedBooks;
    }

    public void addBook(Book book){
        borrowedBooks.add(book.getBookID());
    }

    public void removeBook(Book book){
        for(int i = 0; i < borrowedBooks.size(); i++){
            if (borrowedBooks.get(i) == book.getBookID()){
                borrowedBooks.remove(i);
            } 
        }
    }

    public void displayUser(){
        System.out.println(name + " has the UserID " + userID);
        if (!borrowedBooks.isEmpty()){
            System.out.println("Currently borrowing: ");
            for (String bookID : borrowedBooks){
                System.out.println(bookID);
            }
        } else {
            System.out.println(name + " is not borrowing books currently");
        }
    }

    public Deque<Book> getViewedBooks(){
        return viewedBooks;
    }

    public void addViewedBook(Book book){
        viewedBooks.addFirst(book);
    }

    public Book peekViewedBook(){
        return viewedBooks.peek();
    }
}
