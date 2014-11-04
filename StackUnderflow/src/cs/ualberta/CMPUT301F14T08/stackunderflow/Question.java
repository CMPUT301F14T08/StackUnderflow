/**
 * Question class, extends Post class
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Question extends Post {
	
	private String mTitle;
	private ArrayList<Answer> mAnswers = new ArrayList<Answer>();
	

	public Question(String text, String signature, String title){
		this(text, signature, null, title);
	}
	
	public Question(String text, String signature, String picture, String title){
		super(text, signature, picture);
		mTitle = title;
		mAnswers = new ArrayList<Answer>();
	}
	
	//Constructor for testing only, to properly test SortByDate()
	public Question(String text, String signature, String picture, String title, Date date){
		super(text, signature, picture, date);
		mTitle = title;
		mAnswers = new ArrayList<Answer>();
	}
	
	public void setTitle(String title){
		mTitle = title;
	}
	
	public String getTitle(){
		return mTitle;
	}

	public ArrayList<Answer> getAnswers() {
		return mAnswers;
	}
	
   public Answer getAnswer(UUID uuid) {
        for (Answer answer: mAnswers) {
            if (answer.getID() == uuid)
                return answer;
        }
        return null;
    }
	
	public int getPositionOfAnswer(UUID answerUUID){
		int position = 0;
		for(int i = 0; i < mAnswers.size(); i++){
			if(mAnswers.get(i).getID().equals(answerUUID))
				position = i;
		}
		return position;
	}

	public int countAnswers(){
		return mAnswers.size();
	}
	
	public void addAnswer(Answer newAnswer){
		newAnswer.setParentQuestion(this);
		mAnswers.add(newAnswer);
	}

	public void initializeAnswers(){
		mAnswers = new ArrayList<Answer>();
	}
}
