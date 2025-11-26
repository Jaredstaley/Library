public class Book {
    private String title;
    private String author;
    private String bookID;
    private Boolean available;
    private Integer copies;


    public Book(String title, String author, String bookID, Integer copies){
        this.title = title;
        this.author = author;
        this.bookID = bookID;
        this.copies = copies;
        this.available = true;
    }

    public Book(String title, String author, String bookID){
        this(title,author,bookID,1);
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

    public Integer getCopies(){
        return copies;
    }

    public void setAvailable(){
        available = !available;
    }

    public String toString(){
        return title + " Is authored by " + author;
    }
    
}