package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Model;

import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import android.test.ActivityInstrumentationTestCase2;

public class TestQuestion extends ActivityInstrumentationTestCase2<MainActivity> {

	public TestQuestion() {
		super(MainActivity.class);
	}
	/**
	 *  Add an answer to a question
	 *  
	 */
	public void testAddAnswer(){
		Question q1 = new Question("post body", "author", "title");
		Answer a1 = new Answer("post body", "author");
		q1.addAnswer(a1);
		assertTrue(q1.getAnswers().contains(a1));
		assertTrue(a1.getParentID().equals(q1.getID()));
		
		assertEquals(q1.getText(),"post body");
		assertEquals(q1.getSignature(),"author");
	}
	/**
	 *  Add a reply to a question
	 *  
	 */
	public void testAddReply(){
		Question q1 = new Question("post body", "author", "title");
		Reply r1 = new Reply("post body", "author");
		q1.addReply(r1);
		assertTrue(q1.getReplies().contains(r1));
	}
	
	/**
	 * 
	 *  Attach a picture to a question
	 *  
	 */
	public void testAttachPicture(){
		// attach photo method, to be implemented
		String image = "placeholder"; //placeholder for image
		Question q1 = new Question("post body", "author", image, "title");

		assertTrue(q1.getPicture().equals(image));
	}
		
	/**
	 *  Mark a question as a favorite
	 *  Use Case 4: markQuestionAsFavorite
	 */
	public void testMarkQuestionAsFavorite(){
		Question q1 = new Question("post body", "author", "title");
		q1.getUserAttributes().toggleIsFavorited();
		assertTrue(q1.getUserAttributes().getIsFavorited() == true);
	}
	
	/**
	 *  Unmark a question as a favorite
	 *  Use Case 5: unMarkQuestionAsFavorite
	 */
	public void testUnMarkQuestionAsFavorite(){
		Question q1 = new Question("post body", "author", "title");
		q1.getUserAttributes().toggleIsFavorited();
		assertTrue(q1.getUserAttributes().getIsFavorited() == true);
		q1.getUserAttributes().toggleIsFavorited();
		assertTrue(q1.getUserAttributes().getIsFavorited() == false);
	}
	
}
