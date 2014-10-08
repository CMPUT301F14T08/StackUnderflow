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
 * StackUnderflow application
 * Class created for testing QuestionList model
 * 
 * 
 * @author Michael Williams
 *
 */
public class TestQuestionList extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public TestQuestionList() {
		super(StackUnderflowActivity.class);
		// TODO Auto-generated constructor stub
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
		
		q1.setmText(text);
		q1.setmAuthor(author);
		q1.setmUpvotes(upvotes);
		q1.setmDate(date); 
		q1.setmPhoto(image);
		
		qlist.addQuestion(q1);
		
		assertTrue(qlist.getQuestions().contains(q1));
		
	}
	
	/**
	 *  Test viewing of favorite posts
	 *  Use Case 8: viewFavoritedPosts
	 *  	TODO partial test implementation: 
	 *  		populates list and favorites posts
	 *  		requires UI activity and view 
	 *  
	 */
	public void testViewFavoritedPosts(){
		QuestionList qlist = new QuestionList();
		Question q1 = new Question();
		Question q2 = new Question();
		Question q3 = new Question();
		q1.setmIsFavorite(true);
		q3.setmIsFavorite(true);
		qlist.addQuestion(q1);
		qlist.addQuestion(q2);
		qlist.addQuestion(q3);
		ArrayList<Question> qfavoritelist = new ArrayList<Question>();
		for (Question q : qlist.getQuestions()){
			if (q.getmIsFavorite()) {
				qfavoritelist.add(q);
			}
		}
		assertFalse(qfavoritelist.contains(q2));
		//TODO pass qfavoritelist to ListView adapter of activity		
		//TODO implement activity/fragment and ListView for UI testing
		
		
	}

}
