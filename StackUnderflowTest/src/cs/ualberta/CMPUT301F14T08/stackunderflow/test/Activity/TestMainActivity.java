package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Activity;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.NewQuestionActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.QuestionActivity;

public class TestMainActivity extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public TestMainActivity() {
		super(MainActivity.class);
	}

	public void testMainView() {
		Intent intent = new Intent();
		setActivityIntent(intent);
		
		MainActivity ma = getActivity();
//		TextView test = (TextView) ma.findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_text);
//		TextView test2 = (TextView) ma.getWindow().getDecorView().findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.id.main_question_text);
		
//		ViewAsserts.assertOnScreen(test, test2);
		
	}
	
	// check that activity menu selection succeeds and associated activity starts */
	public void testMenuSettings() {
	
    	ActivityMonitor am = getInstrumentation().addMonitor(NewQuestionActivity.class.getName(), null, false);
    
    	// click the menu item
    	getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
    	getInstrumentation().invokeMenuActionSync(getActivity(), R.id.ask_question, 0);
    
    	Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 1000);
    	assertEquals(true, getInstrumentation().checkMonitorHit(am, 1));
    	a.finish();
	
	}
	

	// Add in tests for tabs during part 4.

}
