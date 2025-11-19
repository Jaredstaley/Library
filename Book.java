public class Book {
    private String title;
    private String author;
    private String bookID;
    private Boolean available;


    public Book(String title, String author, String bookID){
        this.title = title;
        this.author = author;
        this.bookID = bookID;
        this.available = true;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getBookID(){
        return bookID;
    }

    public boolean getAvailable(){
        return available;
    }

    public void setAvailable(){
        available = !available;
    }

    public String toString(){
        return title + " Is authored by " + author;
    }
    
}