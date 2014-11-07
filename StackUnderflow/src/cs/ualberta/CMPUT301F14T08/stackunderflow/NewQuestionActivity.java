package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;

public class NewQuestionActivity extends BaseFragmentActivity {

	@Override
	protected Fragment newFragmentType() {
	
		return new NewQuestionFragment();
	}
}
