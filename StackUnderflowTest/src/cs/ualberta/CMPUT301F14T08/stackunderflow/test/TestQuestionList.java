package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.QuestionList;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;

import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * Class created for testing QuestionList model
 * 
 * 
 * @author Michael Williams
 *
 */
public class TestQuestionList extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public TestQuestionList() {
		super(StackUnderflowActivity.class);

	}
	
	public void testQuestionList(){
		QuestionList qmodel = new QuestionList();
	}
	
	/**
	 *  Test adding a new question to the question list
	 *
	 */
	public void testAddQuestion(){
		QuestionList qlist = new QuestionList();
		Question q1 = new Question();
		
		//assign arbitrary Post attributes		
		String text = "sample question 1";
		String author = "author 1";
		int upvotes = 0;
		Date date = new Date();
		String image = "placeholder"; //team to determine image format
		
		q1.setText(text);
		q1.setAuthor(author);
		q1.setVotes(upvotes);
		q1.setDate(date); 
		q1.setPicture(image);
		
		qlist.addQuestion(q1);
		
		assertTrue(qlist.getQuestions().contains(q1));
		
	}
	
	/**
	 *  Test viewing of favorite posts
	 *  
	 */
	public void testViewFavoritedPosts(){
		QuestionList qlist = new QuestionList();
		Question q1 = new Question();
		Question q2 = new Question();
		Question q3 = new Question();
		q1.setIsFavorite(true);
		q3.setIsFavorite(true);
		qlist.addQuestion(q1);
		qlist.addQuestion(q2);
		qlist.addQuestion(q3);
		ArrayList<Question> qfavoritelist = new ArrayList<Question>();
		
		//simulates populating of a ListView
		for (Question q : qlist.getQuestions()){
			if (q.getIsFavorite()) {
				qfavoritelist.add(q);
			}
		}
		//simulates check that ListView contains favorited posts
		assertFalse(qfavoritelist.contains(q2));

		
		
	}

}
