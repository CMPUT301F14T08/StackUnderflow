package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;

public class TestReply extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {
	public TestReply() {
		super(StackUnderflowActivity.class);
		// TODO Auto-generated constructor stub
	}
	public void testCreateReply(){
		Reply r1 = new Reply("body", "author");
		assertTrue(r1.getText().equals("body"));
		assertTrue(r1.getSignature().equals("author"));
	}
}
