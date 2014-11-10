
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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

	@Override
	public void onResume(){
		super.onResume();
	}

	/** Called when the activity is first created. */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.ask_question:	

			Intent intent = new Intent(this, NewQuestionActivity.class);				
			startActivityForResult(intent, PICK_QUESTION);

			return true;

		default:				
			CharSequence text = "Implement menu item";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(this, text, duration);
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
		} else {
			tf = (MainFragment) f;
		}
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


	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		if (requestCode == PICK_QUESTION) {
			if (resultCode == RESULT_OK) {
				String title = data.getStringExtra("question.title");
				String body = data.getStringExtra("question.body");
				String author = data.getStringExtra("question.author");
				Question q = new Question(body, author, title);
				tf.sPostController.getPostManager().addQuestion(q);
			}
		}
	}



}