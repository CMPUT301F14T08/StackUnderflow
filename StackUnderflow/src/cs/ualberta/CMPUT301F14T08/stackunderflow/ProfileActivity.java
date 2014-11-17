
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
/**
 * ProfileActivity
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
	    
	    TextView user_name = (TextView) actionBar.getCustomView().findViewById(R.id.profile_user_name);
	    TextView questions_posted = (TextView) actionBar.getCustomView().findViewById(R.id.profile_questions_box);
	    TextView answers_posted = (TextView) actionBar.getCustomView().findViewById(R.id.profile_answers_box);
	    
	    //Use once UserProfile is implemented
	    //user_name.setText(getUsername());
	    //int q_count = getQuestionsPostedCount();
	    //int a_count = getAnswersPostedCount();
	    
	    
	    //temporary until UserProfile implemented
	    user_name.setText("guest ");
	    int q_count = 10;
	    int a_count = 15;
	    //
	    
	    String q_string = Integer.toString(q_count);
	    String a_string = Integer.toString(a_count);
	    
		String string = String.format("%s\nQuestions", q_string);		
		Spannable formattedString = new SpannableString(string);
		formattedString.setSpan(new RelativeSizeSpan(0.4f), q_count/10+1, formattedString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		questions_posted.setText(formattedString);		
	    
		string = String.format("%s\n  Answers  ", a_string);		
		formattedString = new SpannableString(string);
		formattedString.setSpan(new RelativeSizeSpan(0.4f), a_count/10+1, formattedString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		answers_posted.setText(formattedString);    
	    
	    
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

	// Code to allow testing of Username Dialog, remove once copied over
	// to other places that need to call it.
	public void testDialogFragment(MenuItem menu) {
		FragmentManager fm = getFragmentManager();
		UsernameDialogFragment udf = new UsernameDialogFragment();
		udf.show(fm, "Username Dialog Fragment");
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