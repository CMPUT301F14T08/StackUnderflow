/**
 * newAnswerActivity - basic new new activity for answers simply calls a newAnswerFragment
 * @author Cmput301 Winter 2014 Group 8
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;

public class NewAnswerActivity extends BaseFragmentActivity {

	@Override
	protected Fragment newFragmentType() {
	
		return new NewAnswerFragment();
	}
}
