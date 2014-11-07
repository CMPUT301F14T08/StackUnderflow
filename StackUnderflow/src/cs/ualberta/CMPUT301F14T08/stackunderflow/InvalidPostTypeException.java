/*
 * InvalidPostTypeExepction simply a error if anything is not assigned as a question or an answer.
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

public class InvalidPostTypeException extends RuntimeException {

	public InvalidPostTypeException() {
		super("Post is not a Question or an Answer");
	}
}