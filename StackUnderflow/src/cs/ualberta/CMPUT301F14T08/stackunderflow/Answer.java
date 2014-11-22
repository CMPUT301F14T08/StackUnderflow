

package cs.ualberta.CMPUT301F14T08.stackunderflow;


import java.util.UUID;
import java.util.Date;


/**
* Answer class, extends Post class. Used to hold answers to questions. mPartent ID will like an answer to a question. 
* Answers should never be called with out calling setparentQuestion or it will cause an answer that is not answering anything
* @author Cmput301 Winter 2014 Group 8
 */
public class Answer extends Post {
    
	private UUID mParentID;
	/**
	 * Constructor creates an answer for a question without a picture or a date, Default parent ID is set to null.
	 * @param text main text of the answer. (The Answer part of the Answer)
	 * @param signature The user name of the user who submitted the answer.
	 */
	public Answer(String text, String signature) {
		super(text, signature);
		mParentID = null;
	}
	/**
	 * Constructor creates an answer for a question without a date, Default parent ID is set to null.
	 * @param text main text of the answer. (The Answer part of the Answer)
	 * @param signature The user name of the user who submitted the answer.
	 */
	public Answer(String text, String signature, byte[] photo) {
		super(text, signature, photo);
		mParentID = null;
	}
	/**
	 * Constructor creates an answer for a question. Default parent ID is set to null.
	 * @param text main text of the answer. (The Answer part of the Answer)
	 * @param signature The user name of the user who submitted the answer.
	 */
	public Answer(String text, String signature, byte[] photo, Date date) {
		super(text, signature, photo, date);
		mParentID = null;
	}
	/**
	 * Called on a answer to set the inner variable mParentID to the ID of the question
	 * @param parentQuestion the question that is the parent of the question.
	 */
	public void setParentQuestion(Question parentQuestion){
		mParentID = parentQuestion.getID();
	}
	/**
	 * Called on a answer to get the mParentID of the question that is the parent of that answer. Multiple answers can have a single question but
	 * but one answer cannot have multiple questions
	 * @return a Parent UUID of a question that his answer is the parent of.
	 */
	public UUID getParentID() {
		return mParentID;
	}	
}
