package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Controllers;

import java.util.ArrayList;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.TextView;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostAdapter;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;

public class TestPostAdapter extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public TestPostAdapter() {
		super(MainActivity.class);
	}
	
	public void testPostAdapter() {
		ArrayList<Post> list = new ArrayList<Post>();
		PostAdapter adapter;
		
		Question q = new Question("a", "a", "a");
		list.add(q);
		
		adapter = new PostAdapter(getActivity(), list);
		
		assertEquals(q, adapter.getItem(0));
	}
	
	// This test will fail on first-run with no cache file
	public void testViews() {
		Intent intent = new Intent();
		setActivityIntent(intent);
		
		MainActivity ma = getActivity();
		TextView test = (TextView) ma.findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_text);
		TextView test2 = (TextView) ma.getWindow().getDecorView().findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_text);
		
		ViewAsserts.assertOnScreen(test, test2);
		
		test = (TextView) ma.findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_subtitle_text);
		test2 = (TextView) ma.getWindow().getDecorView().findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_subtitle_text);
		
		ViewAsserts.assertOnScreen(test, test2);
		
		test = (TextView) ma.findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_answer_count_text);
		test2 = (TextView) ma.getWindow().getDecorView().findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_answer_count_text);
		
		ViewAsserts.assertOnScreen(test, test2);
	}

}
