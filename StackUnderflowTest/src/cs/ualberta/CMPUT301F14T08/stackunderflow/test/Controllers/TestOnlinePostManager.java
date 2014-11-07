/*package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Controllers;

import java.io.IOException;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.OnlinePostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;

public class TestOnlinePostManager extends ActivityInstrumentationTestCase2<MainActivity>  {

	public TestOnlinePostManager() {
		super(MainActivity.class);

	}
	public void testInnerCachedPostManager() throws IOException {
		//checks if you can add questions directly to the cached post manager inside of online post manager
		OnlinePostManager mPostManager = OnlinePostManager.getInstance(getActivity());
		mPostManager.getCachedPostManager().getPosts().clear();
		mPostManager.getCachedPostManager().addQuestion(new Question("Topic","Author", "Picture"));
		
		assertEquals(mPostManager.getCachedPostManager().getPosts().get(0).getText(),"Topic");
		assertEquals(mPostManager.getCachedPostManager().getPosts().get(0).getSignature(),"Author");	
	}
	public void testAddToOnlinePostManager() throws IOException {
		//checks if you can add and remove questions to the online post manager
		OnlinePostManager mPostManager = OnlinePostManager.getInstance(getActivity());
		mPostManager.getPosts().clear();
		Question q1=new Question("Topic","Author", "Picture");
		Answer a1=new Answer("User","Author",null);
		a1.setParentQuestion(q1);
		mPostManager.addQuestion(q1);
		mPostManager.addAnswer(q1, a1);
		
		assertEquals(mPostManager.getPosts().get(0).getText(),"Topic");
		assertEquals(mPostManager.getPosts().get(0).getSignature(),"Author");
		
		assertEquals(((Question)mPostManager.getPosts().get(0)).countAnswers(),2);
	}
	public void testPushandPullOnline() throws IOException {
		
		OnlinePostManager mPostManager = OnlinePostManager.getInstance(getActivity());
		Question q1 = new Question("Topic","Author", "Picture");
		mPostManager.getPosts().clear();
		assertEquals(mPostManager.getPosts().size(),0);
		mPostManager.addQuestion(q1);
		assertEquals(mPostManager.getPosts().get(0).getText(),"Topic");
		mPostManager.getPosts().clear();
		assertEquals(mPostManager.getPosts().size(),0);
		
		mPostManager.refreshQuestion(q1);
		Log.d("DEBUG", "Being Pulled : "+mPostManager.getOnlineQuestion(q1)); 
		assertEquals(mPostManager.getPosts().get(0).getText(),"Topic");
	}
	public void testrefreshAll() throws IOException {
		
		OnlinePostManager mPostManager = OnlinePostManager.getInstance(getActivity());
		Question q1 = new Question("Topic","Author", "Picture");
		mPostManager.getPosts().clear();
		assertEquals(mPostManager.getPosts().size(),0);
		mPostManager.addQuestion(q1);
		assertEquals(mPostManager.getPosts().get(0).getText(),"Topic");
		mPostManager.getPosts().clear();
		assertEquals(mPostManager.getPosts().size(),0);
		
		mPostManager.refreshAll();
		Log.d("DEBUG", "Being Pulled : "+mPostManager.getOnlineQuestion(q1)); 
		assertEquals(mPostManager.getPosts().get(0).getText(),"Topic");
	}
	public void testToggleUpvote() throws IOException {
		
		OnlinePostManager mPostManager = OnlinePostManager.getInstance(getActivity());
		Question q1 = new Question("Topic","Author", "Picture");
		mPostManager.getPosts().clear();
		assertEquals(mPostManager.getPosts().size(),0);
		mPostManager.addQuestion(q1);
		assertEquals(mPostManager.getPosts().get(0).getText(),"Topic");
		mPostManager.getPosts().clear();
		assertEquals(mPostManager.getPosts().size(),0);
		
		mPostManager.toggleUpvote(q1);
		Log.d("DEBUG", "Being Pulled : "+mPostManager.getOnlineQuestion(q1)); 
		assertEquals(mPostManager.getPosts().get(0).getText(),"Topic");
		assertEquals(mPostManager.getPosts().get(0).getVotes(), 1 );
	}
	public void testpush(){
		OnlinePostManager mPostManager = OnlinePostManager.getInstance(getActivity());
		Question q1 = new Question(  
				"LOL I don't actually have a question, I just wanted to post.",
				"gregthegreg",
				"pic1",
				"MY FIRST QUESTION GUIS!!!11");
		mPostManager.addQuestion(q1);
		assertEquals(q1, q1);
		
	}
}*/