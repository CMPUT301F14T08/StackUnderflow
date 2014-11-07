package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;
import android.app.FragmentManager;
import android.view.MenuItem;

public class MainActivity extends BaseFragmentActivity {

	/** Called when the activity is first created. */
	@Override
	protected Fragment newFragmentType() {
		//return new MainFragment();
		return new MainFragment();
	}
	
    
	
	// Code to allow testing of Username Dialog, remove once copied over
	// to other places that need to call it.
	public void testDialogFragment(MenuItem menu) {
		FragmentManager fm = getFragmentManager();
		UsernameDialogFragment udf = new UsernameDialogFragment();
		udf.show(fm, "Username Dialog Fragment");
	}

}