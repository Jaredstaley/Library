public class BorrowRequest {
    private String userID;
    private String bookID;

    public BorrowRequest(String bookID,String userID){
        this.bookID = bookID;
        this.userID = userID;
    }

    public String getBookID(){
        return bookID;
    }

    public String getUserID(){
        return userID;
    }

    public String toString(){
        return userID + " has requested to borrow bookID " + bookID;
    }

}
