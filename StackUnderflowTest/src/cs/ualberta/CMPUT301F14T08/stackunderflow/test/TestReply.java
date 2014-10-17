package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;

public class TestReply extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {
	public TestReply() {
		super(StackUnderflowActivity.class);
		// TODO Auto-generated constructor stub
	}
	public void testSetDate(){
		Reply r1 = new Reply();
		Date d1 = new Date();
		r1.setDate(d1);
		assertTrue(r1.getDate().equals(d1));
	}
	public void testSetText(){
		Reply r1 = new Reply();
		String s1 = "test text";
		r1.setText(s1);
		assertTrue(r1.getText().equals(s1));
	}
	public void testSetAuthor(){
		Reply r1 = new Reply();
		String s1 = "test author";
		r1.setAuthor(s1);
		assertTrue(r1.getAuthor().equals(s1));
	}
}
