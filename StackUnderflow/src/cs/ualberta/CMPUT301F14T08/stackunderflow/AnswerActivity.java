/**
 * AnswerActivity. Simply the base for the AnswerFragment. 
 * @author Cmput301 Winter 2014 Group 8
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;

public class AnswerActivity extends BaseFragmentActivity {

	@Override
	protected Fragment newFragmentType() {
		return new AnswerFragment();
	}	
}
