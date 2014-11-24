
package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.QuestionFragment;
import android.app.Fragment;
/**
 * questionActivity - Simply calls QuestionFragment.
 * @author Cmput301 Winter 2014 Group 8
 */
public class QuestionActivity extends BaseFragmentActivity {

    @Override
    protected Fragment newFragmentType() {

        return new QuestionFragment();
    }
}
