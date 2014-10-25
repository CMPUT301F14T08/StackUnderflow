/**
 * Question class, extends Post class
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

public class Question extends Post {
	
	private String mTitle;
	private ArrayList<Answer> mAnswers = new ArrayList<Answer>();

	public Question(String text, String signature, String title){
		this(text, signature, null, title);
	}
	
	public Question(String text, String signature, String picture, String title){
		super(text, signature, picture);
		mTitle = title;	
	}
	
	public String getTitle(){
		return mTitle;
	}

	public ArrayList<Answer> getAnswers() {
		return mAnswers;
	}

	public int countAnswers(){
		return mAnswers.size();
	}
	
	public void addAnswer(Answer newAnswer){
		newAnswer.setParentQuestion(this);
		mAnswers.add(newAnswer);
	}

}
