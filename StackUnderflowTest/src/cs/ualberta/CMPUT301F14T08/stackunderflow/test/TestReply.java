package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Reply;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;

public class TestReply extends ActivityInstrumentationTestCase2<MainActivity> {
	
    public TestReply() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	public void testCreateReply(){
		Reply r1 = new Reply("body", "author");
		assertTrue(r1.getText().equals("body"));
		assertTrue(r1.getSignature().equals("author"));
	}
}
