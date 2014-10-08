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
	 *  Use Case 7:submitReply (picture attachment tested separately TODO indicate method)
	 */
	public void testAddReply(){
		Question q1 = new Question();
		Reply r1 = new Reply();
		q1.addReply(r1);
		assertTrue(q1.getmReplies().contains(r1));
	}
	
	/**
	 *  TODO Implement this method
	 *  Attach a picture to a question
	 *  Use Case 7:submitReply (picture attachment )
	 */
	public void testAttachPicture(){
		//TODO Implement this method
	}
		
	/**
	 *  Mark a question as a favorite
	 *  Use Case 4: markQuestionAsFavorite
	 */
	public void testMarkQuestionAsFavorite(){
		Question q1 = new Question();
		q1.setmIsFavorite(true);
		assertTrue(q1.getmIsFavorite() == true);
	}
	
	/**
	 *  Unmark a question as a favorite
	 *  Use Case 5: unMarkQuestionAsFavorite
	 */
	public void testUnMarkQuestionAsFavorite(){
		Question q1 = new Question();
		q1.setmIsFavorite(false);
		assertTrue(q1.getmIsFavorite() == false);
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
		
		q1.setmID(id);
		q1.setmText(text);
		q1.setmAuthor(author);
		q1.setmUpvotes(upvotes);
		q1.setmDate(date); 
		q1.setmPhoto(image);
		
		assertTrue(q1.getmID().equals(id));
		assertTrue(q1.getmText().equals(text));		
		assertTrue(q1.getmAuthor().equals(author));
		assertTrue(q1.getmUpvotes() == upvotes);
		assertTrue(q1.getmPhoto().equals(image));
		assertTrue(q1.getmDate().equals(date));
		assertTrue(q1.getAnswers() != null);

		
	}
	
}
