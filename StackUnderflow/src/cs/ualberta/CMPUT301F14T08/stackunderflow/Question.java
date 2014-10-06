package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

public class Question extends Post {
	
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

}
