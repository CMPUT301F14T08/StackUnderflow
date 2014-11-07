package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Model;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;

public class TestPost extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public TestPost(){
		super(MainActivity.class);
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
	
	public void testReplies() {
		Reply r1 = new Reply("test", "author");
		Question q1 = new Question("test", "author", "test");
		Answer a1 = new Answer("test", "test");
		
		q1.addReply(r1);
		a1.addReply(r1);
		
		assertEquals(q1.getReplies(), a1.getReplies());
	}
	
}
