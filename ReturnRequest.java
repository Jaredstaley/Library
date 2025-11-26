public class ReturnRequest {
    private String userID;
    private String bookID;

    public ReturnRequest(String bookID,String userID){
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
        return userID + " has requested to return bookID " + bookID;
    }
}
