package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

public class QuestionList { //this could also be renamed PostList?
	
	private ArrayList<Question> questions;

	public QuestionList() {
		super();
		questions = new ArrayList<Question>();
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		questions.add(question);
	}
		
}
