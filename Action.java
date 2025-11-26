enum ActionType {
    ISSUE,
    RETURN,
    ADD_BOOK,
    REMOVE_BOOK
    }

public class Action {
    private Book book;
    private String userID;
    private ActionType action;

    public Action(String userID, Book book, ActionType action){
        this.action = action;
        this.userID = userID;
        this.book = book;
    }
}
