
package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.NewQuestionFragment;
import android.app.Fragment;

/**
 * NewQuestionActivity - Simple code for when ever the "Ask a Question" button is pressed. Will
 * simply call NewQuestionFragment.
 * 
 * @author Cmput301 Winter 2014 Group 8
 */
public class NewQuestionActivity extends BaseFragmentActivity {

    @Override
    protected Fragment newFragmentType() {

        return new NewQuestionFragment();
    }
}
