package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

/**
 * 
 * StackUnderflow application
 * Question class, extends Post class
 * @author Michael Williams
 * 
 */


public class Question extends Post {
	
	private boolean mIsFavorite;
	private boolean mIsRead;
	
	private ArrayList<Answer> answers;

	public Question() {
		super();
		answers = new ArrayList<Answer>();
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}
	
	public void addAnswer(Answer answer){
		answers.add(answer);
	}

	public boolean getIsFavorite() {
		return mIsFavorite;
	}

	public void setIsFavorite(boolean mIsFavorite) {
		this.mIsFavorite = mIsFavorite;
	}

	public boolean getmIsRead() {
		return mIsRead;
	}

	public void setmIsRead(boolean mIsRead) {
		this.mIsRead = mIsRead;
	}
	

}
