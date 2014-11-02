package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;

public class TestAnswer extends ActivityInstrumentationTestCase2<MainActivity> {
	
	
	public TestAnswer() {
		super(MainActivity.class);
	}
	
	public void testAddReply(){
		Answer a1 = new Answer("post body", "author");
		Reply r1 = new Reply("post body", "author");
		
		a1.addReply(r1);
		assertTrue(a1.getReplies().contains(r1));
	}

}
