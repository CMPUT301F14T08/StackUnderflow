
package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.UsernameDialog;
import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.ProfileFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.LocManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;
/**
 * ProfileActivity This is the view for the view profile attributes. This displays how many questions and answers a user has submitted. 
 * This also displays all of a users read later, favorites and personal posts.
 * Finally this is also the main location where the user can view his name and change it. 
 * 
 * @author Cmput301 Winter 2014 Group 8
 */

public class ProfileActivity extends Activity implements TabListener {
    protected static final int REQUEST_MAP_CODE = 11223344;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private UserProfileManager upm;
    protected ProfileFragment tf = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setCustomView(R.layout.profile_action_bar);		
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);

        TextView mUserName = (TextView) actionBar.getCustomView().findViewById(R.id.profile_user_name);
        TextView mUserLocation = (TextView) actionBar.getCustomView().findViewById(R.id.user_location_prof);
        TextView mQuestionsPosted = (TextView) actionBar.getCustomView().findViewById(R.id.profile_questions_box);
        TextView mAnswersPosted = (TextView) actionBar.getCustomView().findViewById(R.id.profile_answers_box);
        RatingBar mRatingBar = (RatingBar) actionBar.getCustomView().findViewById(R.id.ratingBar);

        upm = UserProfileManager.getInstance(getApplication());
        String setUsername = upm.getUsername();
        mUserName.setText(setUsername);

        String setLocation = "Location";
        LatLng userLoc = upm.getLocation();
        if (userLoc != null) {
            if ((userLoc.latitude != LocManager.LOC_ERROR) && (userLoc.latitude != LocManager.LOC_ERROR)) {
                setLocation = LocManager.getLocationString(getApplicationContext(), userLoc);
                if (setLocation == LocManager.LOCATION_ERROR){
                	setLocation = "Location";
                }
            }
        }
        mUserLocation.setText(setLocation);


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
                }
            }
        });

        mUserLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//Disable location if running on an emulator
				if(Build.BRAND.equalsIgnoreCase("generic")){
					Toast.makeText(getApplicationContext(), "This feature is not supported using an emulator", Toast.LENGTH_LONG).show();
				}
				else{
					if(PostController.getInstanceNoRefresh(getApplicationContext()).isOnline()){
						Intent intent = new Intent(getApplicationContext(), MapActivity.class);                
						startActivityForResult(intent, REQUEST_MAP_CODE);
					}
					else{
						Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_LONG).show();
					}
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

    //gets picture as a byteArray and file name as a string from NewImageDialogFragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_MAP_CODE && resultCode == Activity.RESULT_OK) {
            Double mLatitude = data.getDoubleExtra("latitude", LocManager.LOC_ERROR);
            Double mLongitude = data.getDoubleExtra("longitude", LocManager.LOC_ERROR);
            if(mLatitude != LocManager.LOC_ERROR && mLongitude != LocManager.LOC_ERROR){
                upm.setLocation(new LatLng(mLatitude, mLongitude));
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }


}