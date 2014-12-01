package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.SearchDialogFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.SearchFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_main_menu, menu);

        return true;
    }
    /**
     * case statements of what to do when a option from the menu is selected.
     * Ask question make a new intent of NewQuestionActivity and inflates it
     * User Profile creates a new intent of User Profile and inflates it
     * Search makes opens a fragment manager and gives a pop up block to allow the user to search
     * default. If none of the above options are select it will say that there is a unimplemented method. This should not be seen
     * unless there is a bug. This is simply a safety to prevent the app from crashing if such a bug was to occure.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int duration = Toast.LENGTH_LONG;
        CharSequence text = "";
        Toast toast = null;

        switch (item.getItemId()) {
        /*case R.id.ask_question: 
            Intent intent = new Intent(this, NewQuestionActivity.class);                
            startActivityForResult(intent, PICK_QUESTION);

            return true;*/

        case R.id.user_profile: 
            Intent i = new Intent(this, ProfileActivity.class);                
            startActivity(i);

            return true;

        case R.id.search:
            FragmentManager fm = getFragmentManager();
            SearchDialogFragment sdf = new SearchDialogFragment();
            sdf.show(fm, "Search Dialog Fragment");
            return true;

        /*case R.id.mark_read:    
            if (tf.sPostController == null) {
                return true;
            }

            boolean postsAdded = tf.sPostController.markSelectedAsReadLater();
            tf.adapter.notifyDataSetChanged();

            if (postsAdded)
                text = "Successfully added to your Read Later List.";
            else
                text = "Long-click to select one or more posts.";
   
            toast = Toast.makeText(this, text, duration);
            toast.show();           

            return true;*/

        default:                
            text = "Implement menu item";
            toast = Toast.makeText(this, text, duration);
            toast.show();           
            return false;
        } 
    }   
}
