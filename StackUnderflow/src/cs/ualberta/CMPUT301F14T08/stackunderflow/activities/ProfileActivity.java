
package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.UsernameDialog;
import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.ProfileFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;
/**
 * ProfileActivity This is the view for the view profile attributes. This displays how many questions and answers a user has submitted. 
 * This also displays all of a users read later, favorites and personal posts.
 * Finally this is also the main location where the user can view his name and change it. 
 * 
 * @author Cmput301 Winter 2014 Group 8
 */

public class ProfileActivity extends Activity implements TabListener {

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    protected ProfileFragment tf = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setCustomView(R.layout.profile_action_bar);		
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);

        TextView mUserName = (TextView) actionBar.getCustomView().findViewById(R.id.profile_user_name);
        TextView mQuestionsPosted = (TextView) actionBar.getCustomView().findViewById(R.id.profile_questions_box);
        TextView mAnswersPosted = (TextView) actionBar.getCustomView().findViewById(R.id.profile_answers_box);
        RatingBar mRatingBar = (RatingBar) actionBar.getCustomView().findViewById(R.id.ratingBar);

        UserProfileManager upm = UserProfileManager.getInstance(getApplication());
        String setUsername = upm.getUsername();
        mUserName.setText(setUsername);
        int q_count = UserProfileManager.getInstance(getApplication()).getQuestionsPostedCount();
        int a_count = UserProfileManager.getInstance(getApplication()).getAnswerPostedCount();

        // Set rating bar from 0-5 with 1 star for every 5 combined total posts. Max of 5 stars
        mRatingBar.setRating(q_count + a_count < 25 ? (int) Math.floor((q_count + a_count) / 5) : 5.0f);
           
        String q_string = Integer.toString(q_count);
        String a_string = Integer.toString(a_count);

        String string = String.format("%s\nQuestions", q_string);		
        Spannable formattedString = new SpannableString(string);
        //q_string.length() is the number of digits the number of questions has
        formattedString.setSpan(new RelativeSizeSpan(0.4f), q_string.length(), formattedString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mQuestionsPosted.setText(formattedString);		

        string = String.format("%s\n  Answers  ", a_string);		
        formattedString = new SpannableString(string);
        //a_string.length() is the number of digits the number of answers has
        formattedString.setSpan(new RelativeSizeSpan(0.4f), a_string.length(), formattedString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mAnswersPosted.setText(formattedString);

        mUserName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((TextView)v.findViewById(R.id.profile_user_name)).getText() == "Guest"){
                    UsernameDialog.showDialog(getFragmentManager());
                    // We need a way to update the text view after returning from the fragment
                }
            }
        });


        Tab tab = actionBar.newTab();
        tab.setText(R.string.my_posts);
        tab.setTabListener(this);
        actionBar.addTab(tab);

        Tab tab2 = actionBar.newTab();
        tab2.setText(R.string.my_favorites);
        tab2.setTabListener(this);
        actionBar.addTab(tab2);

        Tab tab3 = actionBar.newTab();
        tab3.setText(R.string.read_later);
        tab3.setTabListener(this);
        actionBar.addTab(tab3);

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void onTabSelected(ActionBar.Tab tab,
            FragmentTransaction fragmentTransaction) {

        Fragment f = null;	

        if (fragmentList.size() > tab.getPosition())
            fragmentList.get(tab.getPosition());


        tf = new ProfileFragment();
        Bundle data = new Bundle();
        data.putInt("idx",  tab.getPosition());
        tf.setArguments(data);
        fragmentList.add(tf);

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




}