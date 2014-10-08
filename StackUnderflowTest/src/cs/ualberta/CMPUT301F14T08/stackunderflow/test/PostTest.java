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
 * @author Michael Williams
 * 
 * Note, partially implemented JUnit tests for project part 2
 *
 */
public class PostTest extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {
	
	public PostTest(){
		super(StackUnderflowActivity.class);
	}
	
	
	/**
	 * Tests upvoting a Post 
	 */
	public void testUpvotePost(){
		Post p1 = new Post();
		p1.setmUpvotes(1);
		assertTrue(p1.getmUpvotes() == 1);
		p1.setmUpvotes(1);
		assertTrue(p1.getmUpvotes() == 2);
	}
	
	/**
	 * Tests downvoting a Post
	 */
	public void testDownvotePost(){
		Post p1 = new Post();
		p1.setmUpvotes(3);
		assertTrue(p1.getmUpvotes() == 3);
		p1.setmUpvotes(-1);
		assertTrue(p1.getmUpvotes() == 2);
		p1.setmUpvotes(-1);
		assertTrue(p1.getmUpvotes() == 1);	
		
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

		p1.setmID(id);
		p1.setmText(text);
		p1.setmAuthor(author);
		p1.setmUpvotes(upvotes);
		p1.setmDate(date); 
		p1.setmPhoto(image);
		
		assertTrue(p1.getmID().equals(id));
		assertTrue(p1.getmText().equals(text));		
		assertTrue(p1.getmAuthor().equals(author));
		assertTrue(p1.getmUpvotes() == upvotes);
		assertTrue(p1.getmPhoto().equals(image));
		assertTrue(p1.getmDate().equals(date));
		
	}
	
}
