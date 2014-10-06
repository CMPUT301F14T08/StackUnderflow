package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;

public class ReplyTest extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {
	public ReplyTest() {
		super(StackUnderflowActivity.class);
		// TODO Auto-generated constructor stub
	}
	public void testSetDate(){
		Reply r1 = new Reply();
		Date d1 = new Date();
		r1.setmDate(d1);
		assertTrue(r1.getmDate().equals(d1));
	}
	public void testSetText(){
		Reply r1 = new Reply();
		String s1 = "test text";
		r1.setmText(s1);
		assertTrue(r1.getmText().equals(s1));
	}
	public void testSetAuthor(){
		Reply r1 = new Reply();
		String s1 = "test author";
		r1.setmAuthor(s1);
		assertTrue(r1.getmAuthor().equals(s1));
	}
}
