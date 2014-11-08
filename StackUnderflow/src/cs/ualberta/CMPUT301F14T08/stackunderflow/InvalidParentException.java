package cs.ualberta.CMPUT301F14T08.stackunderflow;

public class InvalidParentException extends RuntimeException {

    public InvalidParentException() {
        super("No Parent Question Found.");
    }
}
