package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public abstract class BaseFragmentActivity extends Activity {
	
	// Sub-classes will just have to implement
	// a method returning the Fragment type they want to use
	protected abstract Fragment newFragmentType();

	@Override
	public void onCreate(Bundle saved_state) {
		super.onCreate(saved_state);
		setContentView(R.layout.base_fragment_activity);

		FragmentManager fragment_manager = getFragmentManager();
		Fragment fragment = fragment_manager
				.findFragmentById(R.id.base_container);

		if (saved_state == null) {
			fragment = newFragmentType();
			fragment_manager.beginTransaction()
					.add(R.id.base_container, fragment).commit();
		}
	}
}
