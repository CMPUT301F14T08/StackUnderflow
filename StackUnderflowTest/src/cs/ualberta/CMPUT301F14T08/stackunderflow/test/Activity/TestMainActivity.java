package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Activity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.TextView;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activity.MainActivity;

public class TestMainActivity extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public TestMainActivity() {
		super(MainActivity.class);
	}

	public void testMainView() {
		Intent intent = new Intent();
		setActivityIntent(intent);
		
		MainActivity ma = getActivity();
		TextView test = (TextView) ma.findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_text);
		TextView test2 = (TextView) ma.getWindow().getDecorView().findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_text);
		
		ViewAsserts.assertOnScreen(test, test2);
	}
	
	// Add in tests for tabs during part 4.

}
