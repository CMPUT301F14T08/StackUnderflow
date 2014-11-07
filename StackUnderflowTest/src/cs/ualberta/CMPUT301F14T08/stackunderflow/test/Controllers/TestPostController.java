package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Controllers;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.CachedPostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.PostManager;

public class TestPostController extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public TestPostController() {
		super(MainActivity.class);
	}
	
	public void testGetPostManager() {
		//Uncomment once online post manager is added/working.
		/*CachedPostManager manInit = CachedPostManager.getInstance(getActivity());
		OnlinePostManager manTest = OnlinePostManager.getInstance(getActivity());
		
		assertTrue(CachedPostManager.getInstance(getActivity()) == OnlinePostManager.getInstance(getActivity()));
		*/
	}

}
