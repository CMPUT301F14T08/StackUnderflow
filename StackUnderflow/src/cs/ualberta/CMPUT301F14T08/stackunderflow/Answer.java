package cs.ualberta.CMPUT301F14T08.stackunderflow;

/**
 * 
 * StackUnderflow application
 * Answer class, extends Post class
 * @author Michael Williams
 * 
 */

public class Answer extends Post {
	
	private Question mQuestion;
	
	/*
	public Answer(Question mQuestion) {
		super();
		this.mQuestion = mQuestion.;
	}
	*/

	public Answer() {
		super();
	}

	public Question getmQuestion() {
		return mQuestion;
	}

	public void setmQuestion(Question mQuestion) {
		this.mQuestion = mQuestion;
	}
	
}
