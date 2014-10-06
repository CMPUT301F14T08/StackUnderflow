package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import java.util.Date;

import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;
import android.test.ActivityInstrumentationTestCase2;

public class AnswerTest extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {
	
	
	public AnswerTest() {
		super(StackUnderflowActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	public void testAddReply(){
		Answer a1 = new Answer();
		Reply r1 = new Reply();
		
		String text = "sample reply 1";
		String author = "author 1";
		Date date = new Date();
		
		r1.setmText(text);
		r1.setmAuthor(author);
		r1.setmDate(date); 

		a1.addReply(r1);
		//assertTrue(a1.getmReplies().contains(r1));
	}

}
