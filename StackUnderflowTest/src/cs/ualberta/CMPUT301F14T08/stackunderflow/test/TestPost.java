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
 * Class created for testing Post model
 *  
 * @author Michael Williams
 *
 */
public class TestPost extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {
	
	public TestPost(){
		super(StackUnderflowActivity.class);
	}
	
	
	/**
	 * Tests upvoting a Post 
	 */
	public void testUpvotePost(){
		Post p1 = new Post();
		p1.setVotes(1);
		assertTrue(p1.getVotes() == 1);
		p1.setVotes(1);
		assertTrue(p1.getVotes() == 2);
	}
	
	/**
	 * Tests downvoting a Post (removing upvote)
	 */
	public void testDownvotePost(){
		Post p1 = new Post();
		p1.setVotes(3);
		assertTrue(p1.getVotes() == 3);
		p1.setVotes(-1);
		assertTrue(p1.getVotes() == 2);
		p1.setVotes(-1);
		assertTrue(p1.getVotes() == 1);	
		
	}
	
	/**
	 * 
	 *  Test attach a picture to a post
	 *  
	 */
	public void testAttachPicture(){
		Post p1 = new Post();
		
		// attach photo method, to be implemented
		String image = "placeholder"; //placeholder for image
		p1.setPicture(image);

		assertTrue(p1.getPicture().equals(image));

	}
	
	
	/**
	 *  Test Post accessor methods (getters and setters)
	 */
	public void testAccessors(){
		Post p1 = new Post();
		
		//assign arbitrary Post attributes	
		UUID id = UUID.randomUUID();
		String text = "sample question 1";
		String author = "author 1";
		int upvotes = 0;
		Date date = new Date();
		String image = "placeholder"; //TODO team to determine image format
		ArrayList<Reply> replies = new ArrayList<Reply>();

		p1.setID(id);
		p1.setText(text);
		p1.setAuthor(author);
		p1.setVotes(upvotes);
		p1.setDate(date); 
		p1.setPicture(image);
		
		assertTrue(p1.getID().equals(id));
		assertTrue(p1.getText().equals(text));		
		assertTrue(p1.getAuthor().equals(author));
		assertTrue(p1.getVotes() == upvotes);
		assertTrue(p1.getPicture().equals(image));
		assertTrue(p1.getDate().equals(date));
		
	}
	
}
