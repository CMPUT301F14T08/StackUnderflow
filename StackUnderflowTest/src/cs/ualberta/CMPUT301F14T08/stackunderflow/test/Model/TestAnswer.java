package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Model;

import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import android.test.ActivityInstrumentationTestCase2;

public class TestAnswer extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public TestAnswer() {
		super(MainActivity.class);
	}
	
	public void testAddReply(){
		String text = "sample reply 1";
		String author = "author 1";
		
		Answer a1 = new Answer(text, author);
		Reply r1 = new Reply(text, author);
		

		a1.addReply(r1);
		assertEquals(a1.getReplies().get(0), r1);
	}

}
