package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Model;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;

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
	
}
