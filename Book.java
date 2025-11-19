public class Book {
    private String title;
    private String author;
    private String bookID;


    public Book(String title, String author, String bookID){
        this.title = title;
        this.author = author;
        this.bookID = bookID;
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

    public String toString(){
        return title + " Is authored by " + author;
    }

}