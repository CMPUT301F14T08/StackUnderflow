package cs.ualberta.CMPUT301F14T08.stackunderflow.exception;

public class InvalidParentException extends RuntimeException {
/**
 * simply output No Parent Question Found when there is no parent question to an answer. 
 * @author Cmput301 Winter 2014 Group 8
 */
    public InvalidParentException() {
        super("No Parent Question Found.");
    }
}
