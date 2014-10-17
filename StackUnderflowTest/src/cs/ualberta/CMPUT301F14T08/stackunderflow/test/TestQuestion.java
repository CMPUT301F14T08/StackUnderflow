package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * Class created for testing Question model
 *  
 * @author Michael Williams
 *
 */
public class TestQuestion extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public TestQuestion() {
		super(StackUnderflowActivity.class);
		// TODO Auto-generated constructor stub
	}
	/**
	 *  Add an answer to a question
	 *  
	 */
	public void testAddAnswer(){
		Question q1 = new Question();
		Answer a1 = new Answer();
		q1.addAnswer(a1);
		assertTrue(q1.getAnswers().contains(a1));
	}
	/**
	 *  Add a reply to a question
	 *  
	 */
	public void testAddReply(){
		Question q1 = new Question();
		Reply r1 = new Reply();
		q1.addReply(r1);
		assertTrue(q1.getmReplies().contains(r1));
	}
	
	/**
	 * 
	 *  Attach a picture to a question
	 *  
	 */
	public void testAttachPicture(){
		Question q1 = new Question();
		
		// attach photo method, to be implemented
		String image = "placeholder"; //placeholder for image
		q1.setPicture(image);

		assertTrue(q1.getPicture().equals(image));

	}
		
	/**
	 *  Mark a question as a favorite
	 *  Use Case 4: markQuestionAsFavorite
	 */
	public void testMarkQuestionAsFavorite(){
		Question q1 = new Question();
		q1.setIsFavorite(true);
		assertTrue(q1.getIsFavorite() == true);
	}
	
	/**
	 *  Unmark a question as a favorite
	 *  Use Case 5: unMarkQuestionAsFavorite
	 */
	public void testUnMarkQuestionAsFavorite(){
		Question q1 = new Question();
		q1.setIsFavorite(false);
		assertTrue(q1.getIsFavorite() == false);
	}
	
	
	
	/**
	 *  Test Question accessor methods (getters and setters)
	 */
	public void testAccessors(){
		Question q1 = new Question();
		
		//assign arbitrary Post attributes	
		UUID id = UUID.randomUUID();
		String text = "sample question 1";
		String author = "author 1";
		int upvotes = 0;
		Date date = new Date();
		String image = "placeholder"; //TODO team to determine image format
		ArrayList<Reply> replies = new ArrayList<Reply>();
		
		//assign Question attributes
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		q1.setID(id);
		q1.setText(text);
		q1.setAuthor(author);
		q1.setVotes(upvotes);
		q1.setDate(date); 
		q1.setPicture(image);
		
		assertTrue(q1.getID().equals(id));
		assertTrue(q1.getText().equals(text));		
		assertTrue(q1.getAuthor().equals(author));
		assertTrue(q1.getVotes() == upvotes);
		assertTrue(q1.getPicture().equals(image));
		assertTrue(q1.getDate().equals(date));
		assertTrue(q1.getAnswers() != null);

		
	}
	
}
