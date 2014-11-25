
package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.id;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.menu;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.string;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.SearchDialogFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.MainFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;

import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
/**
 * MainActivity is the main page. Called when the user first starts the app
 * works with the tabs on the main screen and the dialog allowing the user to navigate between the different screens.
 * @author Cmput301 Winter 2014 Group 8
 */

public class MainActivity extends Activity implements TabListener {

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    protected MainFragment tf = null;
    static final int PICK_QUESTION = 0;
    static final int PICK_ANSWER = 1;

/**
 * Called when the activity is first create. This is used to create the menu bar for sorting a posts by newest and most popular.
 */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab = actionBar.newTab();
        tab.setText(R.string.newest);
        tab.setTabListener(this);
        actionBar.addTab(tab);

        Tab tab2 = actionBar.newTab();
        tab2.setText(R.string.popular);
        tab2.setTabListener(this);
        actionBar.addTab(tab2);

    }
/**
 * called when the app is resumed from being paused. Simply calles the super class on Resume.
 */
    @Override
    public void onResume(){
        super.onResume();
    }

    /** Called when the activity is first created. inflates the main menu options */

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

        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "";
        Toast toast = null;

        switch (item.getItemId()) {
        case R.id.ask_question: 
            Intent intent = new Intent(this, NewQuestionActivity.class);                
            startActivityForResult(intent, PICK_QUESTION);

            return true;

        case R.id.user_profile: 
            Intent i = new Intent(this, MapActivity.class);                
            startActivity(i);

            return true;

        case R.id.search:
            FragmentManager fm = getFragmentManager();
            SearchDialogFragment sdf = new SearchDialogFragment();
            sdf.show(fm, "Search Dialog Fragment");

        case R.id.mark_read:	
            if (tf.sPostController == null) {
                return true;
            }

            boolean postsAdded = tf.sPostController.addSelectedToCache();
            tf.adapter.notifyDataSetChanged();


            if (tf.sPostController.usingOnlinePostManager() && postsAdded)
                text = "Successfully added to Cache.";
            else if (tf.sPostController.usingOnlinePostManager() && !postsAdded)
                text = "Long-click to select one or more posts.";
            else 
                text = "Currently Offline. All posts are in Cache.";


            toast = Toast.makeText(this, text, duration);
            toast.show();			

            return true;

        default:				
            text = "Implement menu item";
            toast = Toast.makeText(this, text, duration);
            toast.show();			
            return false;
        } 
    }	
    
    public void onTabSelected(ActionBar.Tab tab,
            FragmentTransaction fragmentTransaction) {

        Fragment f = null;	

        if (fragmentList.size() > tab.getPosition())
            fragmentList.get(tab.getPosition());

        if (f == null) {
            tf = new MainFragment();
            Bundle data = new Bundle();
            data.putInt("idx",  tab.getPosition());
            tf.setArguments(data);
            fragmentList.add(tf);

        }

        fragmentTransaction.replace(android.R.id.content, tf);		
    }    

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (fragmentList.size() > tab.getPosition()) {
            fragmentTransaction.remove(fragmentList.get(tab.getPosition()));
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //No implementation required at present
    }


    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == PICK_QUESTION) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra("question.title");
                String body = data.getStringExtra("question.body");
                String author = data.getStringExtra("question.author");
                String picture = null;
                if (data.getByteArrayExtra("question.picture") != null) {
                	//Encode byte array as a string to faster storage online
                    picture = Base64.encodeToString(data.getByteArrayExtra("question.picture"), Base64.DEFAULT);
                }

                Question q = null;
                if (picture != null) {
                    q = new Question(body, author, picture, title);
                }
                else {
                    q = new Question(body, author, title);
                }

                tf.sPostController.getPostManager().addQuestion(q);
            }
        }
    }



}