
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.UUID;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
/**
 * BaseFragmentActivity simply a basic background fragment used to check if a fragment exists and if not create one. 
 * @author Cmput301 Winter 2014 Group 8
 */
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
			UUID uuid = (UUID)getIntent().getSerializableExtra(PostFragment.EXTRA_POST_ID);
			int cameFrom = getIntent().getIntExtra(PostFragment.EXTRA_CAME_FROM, PostFragment.FROM_OTHER);
			if(uuid != null){
				Bundle args = new Bundle();
				args.putSerializable(PostFragment.EXTRA_POST_ID, uuid);
				args.putInt(PostFragment.EXTRA_CAME_FROM, cameFrom);
				fragment.setArguments(args);
			}
			fragment_manager.beginTransaction()
					.add(R.id.base_container, fragment).commit();
		}
	}
}
