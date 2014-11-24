
package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.AnswerFragment;
import android.app.Fragment;
/**
 * AnswerActivity. Simply the base for the AnswerFragment. 
 * @author Cmput301 Winter 2014 Group 8
 */
public class AnswerActivity extends BaseFragmentActivity {

    @Override
    protected Fragment newFragmentType() {
        return new AnswerFragment();
    }	
}
