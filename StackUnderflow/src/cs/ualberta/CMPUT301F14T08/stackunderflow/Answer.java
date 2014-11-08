/*
* Answer class, extends Post class. Used to hold answers to questions. mPartent ID will like an answer to a question. 
* Answers should never be called with out calling setparentQuestion or it will cause an answer that is not answering anything
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;


import java.util.UUID;
import java.util.Date;



public class Answer extends Post {
    
	private UUID mParentID;
	
	public Answer(String text, String signature) {
		super(text, signature);
		mParentID = null;
	}
	
	public Answer(String text, String signature, String photo) {
		super(text, signature, photo);
		mParentID = null;
	}
	
	//Constructor for testing, to be able to properly test SortByDate()
	public Answer(String text, String signature, String photo, Date date) {
		super(text, signature, photo, date);
		mParentID = null;
	}
	
	public void setParentQuestion(Question parentQuestion){
		mParentID = parentQuestion.getID();
	}
	
	public UUID getParentID() {
		return mParentID;
	}	
}
