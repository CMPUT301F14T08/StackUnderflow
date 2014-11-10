
package cs.ualberta.CMPUT301F14T08.stackunderflow;
/**
 * InvalidPostTypeExepction simply a error if anything is not assigned as a question or an answer.
 * @author Cmput301 Winter 2014 Group 8
 */
public class InvalidPostTypeException extends RuntimeException {

	public InvalidPostTypeException() {
		super("Post is not a Question or an Answer");
	}
}