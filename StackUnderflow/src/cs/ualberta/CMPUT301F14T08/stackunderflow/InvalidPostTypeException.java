package cs.ualberta.CMPUT301F14T08.stackunderflow;

public class InvalidPostTypeException extends RuntimeException {

	public InvalidPostTypeException() {
		super("Post is not a Question or an Answer");
	}
}