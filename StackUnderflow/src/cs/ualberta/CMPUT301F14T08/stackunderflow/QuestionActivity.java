/**
 * questionActivity - Simply calls QuestionFragment.
 * @author Cmput301 Winter 2014 Group 8
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;

public class QuestionActivity extends BaseFragmentActivity {

	@Override
	protected Fragment newFragmentType() {
	
		return new QuestionFragment();
	}
}
