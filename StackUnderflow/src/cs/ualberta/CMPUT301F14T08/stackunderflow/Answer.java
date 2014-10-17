package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * 
 * StackUnderflow application
 * Answer class, extends Post class
 * @author Michael Williams
 * 
 */

public class Answer extends Post {
	
	private Question mQuestion;
	
	
	public Answer(Question mQuestion) {
		super();
		this.mQuestion = mQuestion;
	}
	
	public Answer() {
		super();
	}
	
	public Question getQuestion() {
		return mQuestion;
	}

	public void setQuestion(Question mQuestion) {
		this.mQuestion = mQuestion;
	}
	
	
}
