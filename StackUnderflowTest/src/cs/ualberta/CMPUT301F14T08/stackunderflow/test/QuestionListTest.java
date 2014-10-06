package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import android.test.ActivityInstrumentationTestCase2;

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
public class QuestionListTest extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public QuestionListTest() {
		super(StackUnderflowActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	public void testQuestionList(){
		QuestionList qmodel = new QuestionList();
	}
	
	public void testAddQuestion(){
		QuestionList qlmodel = new QuestionList();
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
		
		qlmodel.addQuestion(q1);
		
		assertTrue(qlmodel.getQuestions().contains(q1));
		
	}
	

}
