import java.util.ArrayList;

public class User {
    private String name;
    private String userID;
    private ArrayList<String> books = new ArrayList<>();

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

    public ArrayList getBooks(){
        return books;
    }

    public void addBook(Book book){
        books.add(book.getBookID());
    }

    public void removeBook(Book book){
        for(int i = 0; i < books.size(); i++){
            if (books.get(i) == book.getBookID()){
                books.remove(i);
            } 
        }
    }
    public void displayUser(){
        System.out.println(name + " has the UserID " + userID);
        System.out.println("Currently borrowing: ");
        for (String bookID : books){
            System.out.println(bookID);
        }
    }
}
