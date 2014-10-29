package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;

/**
 * 
 * Class created for testing Post model
 *  
 * @author Michael Williams
 * 
 * Edited Oct 20 - Benjamin Lavin
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
		Post p1 = new Post("post body", "author");
		p1.incrementVotes();
		assertTrue(p1.getVotes() == 1);
		p1.incrementVotes();
		assertTrue(p1.getVotes() == 2);
	}
	
	/**
	 * Tests downvoting a Post (removing upvote)
	 */
	public void testDownvotePost(){
		Post p1 = new Post("post body", "author");
		p1.incrementVotes();
		p1.incrementVotes();
		assertTrue(p1.getVotes() == 2);
		p1.decrementVotes();
		assertTrue(p1.getVotes() == 1);
		p1.decrementVotes();
		assertTrue(p1.getVotes() == 0);	
		p1.decrementVotes();
		assertTrue(p1.getVotes() == 0);		
	}
	
	/**
	 * 
	 *  Test attach a picture to a post
	 *  
	 */
	public void testPostPicture(){
		// attach photo method, to be implemented
		String image = "placeholder"; //placeholder for image
		Post p1 = new Post("post body", "author", image);

		assertTrue(p1.getPicture().equals(image));
	}
	
	
	/**
	 *  Test Post access methods (getters and setters)
	 */
	
// Do we need this? - Ben
	
//	public void testAccessors(){
//		Post p1 = new Post("title", "author");
//
//		//assign arbitrary Post attributes	
//		UUID id = UUID.randomUUID();
//		String text = "sample question 1";
//		String author = "author 1";
//		int upvotes = 0;
//		Date date = new Date();
//		String image = "placeholder"; //TODO team to determine image format
//		ArrayList<Reply> replies = new ArrayList<Reply>();
//
//		p1.setID(id);
//		p1.setText(text);
//		p1.setAuthor(author);
//		p1.setVotes(upvotes);
//		p1.setDate(date); 
//		p1.setPicture(image);
//
//		assertTrue(p1.getID().equals(id));
//		assertTrue(p1.getText().equals(text));		
//		assertTrue(p1.getAuthor().equals(author));
//		assertTrue(p1.getVotes() == upvotes);
//		assertTrue(p1.getPicture().equals(image));
//		assertTrue(p1.getDate().equals(date));
//
//	}
	
}
