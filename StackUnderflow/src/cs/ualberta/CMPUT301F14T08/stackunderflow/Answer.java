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
	
	/*
	public Answer(Question mQuestion) {
		super();
		this.mQuestion = mQuestion.;
	}
	*/

	public Answer() {
		super();
	}

	public Answer(ArrayList<Reply> mReplies, UUID mID, String mText,
			String mAuthor, int mUpvotes, Date mDate, String mPhoto,
			Question mQuestion) {
		super();
		this.mQuestion = mQuestion;
	}

	public Question getmQuestion() {
		return mQuestion;
	}

	public void setmQuestion(Question mQuestion) {
		this.mQuestion = mQuestion;
	}
	
	
}
