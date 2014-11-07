package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Activity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.TextView;
import cs.ualberta.CMPUT301F14T08.stackunderflow.BaseFragmentActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.PostFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;

public class TestBaseFragmentActivity extends ActivityInstrumentationTestCase2<BaseFragmentActivity> {
	
	public TestBaseFragmentActivity() {
		super(BaseFragmentActivity.class);
	}

	// Refer to comment in TestAnswerActivity
	/*public void testBaseFragment() {
		Intent intent = new Intent();
		Question q = new Question("a", "a", "a");
		intent.putExtra(PostFragment.EXTRA_POST_ID, q.getID());
		setActivityIntent(intent);
		
		BaseFragmentActivity ba = getActivity();
		View test = (View) ba.findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.layout.base_fragment_activity);
		View test2 = (View) ba.getWindow().getDecorView().findViewById(cs.ualberta.CMPUT301F14T08.stackunderflow.R.layout.base_fragment_activity);
		
		ViewAsserts.assertOnScreen(test, test2);
	}*/
}
