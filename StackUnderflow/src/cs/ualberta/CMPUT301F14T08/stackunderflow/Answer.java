/*
* Answer class, extends Post class
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.UUID;


public class Answer extends Post {
    
	private UUID mParentID;
	
	public Answer(String text, String signature) {
		this(text, signature, null);
	}
	
	public Answer(String text, String signature, String photo) {
		super(text, signature, photo);
		mParentID = null;
	}
	
	public void setParentQuestion(Question parentQuestion){
		mParentID = parentQuestion.getID();
	}
	
	public UUID getParentID() {
		return mParentID;
	}	
}
