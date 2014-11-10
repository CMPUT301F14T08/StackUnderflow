/**
 * Question class, extends Post class This is where the differences between post and question can be found. This will show a title, amount of answers 
 * it can also get the position of answers. and the answers that are linked to it.
 * @author Cmput301 Winter 2014 Group 8
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Question extends Post {
	
	private String mTitle;
	private ArrayList<Answer> mAnswers = new ArrayList<Answer>();
	/**
	 * Constructor for questions which is a child of post. This version holds questions that user may post and read. This version is
	 * used when a user attempts to make a post without a picture or date. 
	 * @param text This String is the main text of the users post. The Question of the Question 
	 * @param signature This String is the user name of the user who submitted the question
	 * @param title This String is a short name used to explain a basic idea of the story that other user will read before choosing to read the body text
	 */
	public Question(String text, String signature, String title){
		this(text, signature, null, title);
	}
	/**
	 * Constructor for questions which is a child of post. This version holds questions that user may post and read. This version is
	 * used when a user attempts to make a post without a date. 
	 * @param text This String is the main text of the users post. The Question of the Question 
	 * @param signature This String is the user name of the user who submitted the question
	 * @param title This String is a short name used to explain a basic idea of the story that other user will read before choosing to read the body text
	 * @param picture This String is an image that the user may input to give details about them problem (without an image another constructor is used)
	 */
	public Question(String text, String signature, String picture, String title){
		super(text, signature, picture);
		mTitle = title;
		mAnswers = new ArrayList<Answer>();
	}
	
	// TODO: Use java reflection in tests to set date, remove this
	/**
	 * Constructor for questions which is a child of post. This version holds questions that user may post and read. This version is
	 * used when a user attempts to make a post with both a picture and a date. 
	 * @param text This String is the main text of the users post. The Question of the Question 
	 * @param signature This String is the user name of the user who submitted the question
	 * @param title This String is a short name used to explain a basic idea of the story that other user will read before choosing to read the body text
	 * @param picture This String is an image that the user may input to give details about them problem (without an image another constructor is used)
	 * @param Date This Date object takes the time that the question is posted so that it may be displayed and sorted by.
	 */
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
            if (answer.getID().equals(uuid))
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
	/**
	 * Counts how many answers a specific question has.
	 * @return a Interger of the number of answers that the Question has
	 */
	public int countAnswers(){
		return mAnswers.size();
	}
	/**
	 * Adds an answer to the array list of answers that each question has. Use this instead of manually adding it with .add()
	 * @param newAnswer This is the answer object that will be added to the question/
	 */
	public void addAnswer(Answer newAnswer){
		newAnswer.setParentQuestion(this);
		mAnswers.add(newAnswer);
	}

	public void initializeAnswers(){
		mAnswers = new ArrayList<Answer>();
	}
	/**
	 * Changes the mExistsOnline object used to decide if something has changed while the device have been off line.
	 * @param exisitsOnline passes a boolean if to change the state of the question if it exists online or not.
	 */
	@Override
    public void setExistsOnline(boolean existsOnline) {
        this.mExistsOnline = existsOnline;
        for(int i = 0; i < mAnswers.size(); i++){
            mAnswers.get(i).setExistsOnline(existsOnline);
        }
    }
}
