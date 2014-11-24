package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.SearchFragment;
import android.app.Fragment;
/**
 * SearchActivity. Simply the base for the SearchFragment. 
 * @author Cmput301 Winter 2014 Group 8
 */
public class SearchActivity extends BaseFragmentActivity {
    public SearchActivity() {

    }

    @Override
    protected Fragment newFragmentType() {
        return new SearchFragment();
    }	
}
