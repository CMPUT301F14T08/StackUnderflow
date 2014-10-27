package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.Random;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainFragment extends ListFragment implements ActionBar.TabListener{
	
	private ArrayList<Question> mQuestions;
	private ArrayList<Post> mQandA;
	public PostController sPostController;

	// TODO: This will be changed into a PostController once merged.
	private ArrayList<Post> mQandA = new ArrayList<Post>();
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		setHasOptionsMenu(true);
	    final ActionBar actionBar = getActivity().getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText(R.string.newest)
	            .setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText(R.string.popular)
	            .setTabListener(this));
	    
//TODO: Update
	    sPostController = PostController.getInstance(getActivity());
	    mQuestions = sPostController.getPostManager().castToQuestions();


	    getQuestions();
	    // TODO: mQandA will change to be a PostController
		PostAdapter adapter = new PostAdapter(getActivity(), sPostController);
		setListAdapter(adapter);
	    
	}
	
	//Options Menu
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_main_menu, menu);
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	
	}
	
	//Action Bar Tabs : Newest and Popular
	@Override
	public void onTabSelected(ActionBar.Tab tab,
		FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, show the tab contents in the
		// container view.
		
		//TODO: implement switching/filtering of list view
		//TODO: verify with group that change of tab colour is 
		// required: requires heavy theme modification
		  
		/*TODO JUST FOR TESTING, Remove: testing Dummy sections for tabs
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
		    tab.getPosition() + 1);
		fragment.setArguments(args);
		getFragmentManager().beginTransaction()
		    .replace(R.id.base_container, fragment).commit();
		 */		  
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		//TODO implement
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		//TODO implement
	}

	/*
	 * List click listener
	 * uses ListFragment extension of class 
	 * 
	 * Will be used to send a question to the QuestionActivity
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
// TODO: Update
		Question q = ((QuestionAdapter)getListAdapter()).getItem(position);
		Intent i = new Intent(getActivity(), QuestionActivity.class);
		i.putExtra(PostFragment.EXTRA_POST_ID, q.getID());
		startActivity(i);
	}


	
	//TEST CODE BELOW *************************************************************
	
	//TODO JUST FOR TESTING, Remove. Test sections for tab switching
	public static class DummySectionFragment extends Fragment {
		public static final String ARG_SECTION_NUMBER = "placeholder_text";
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		    Bundle savedInstanceState) {
		  TextView textView = new TextView(getActivity());
		  textView.setGravity(Gravity.CENTER);
		  textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		  return textView;
		}
	}
	
	
	//TODO JUST FOR TESTING, Remove. Creates list of questions for testing
    private void getQuestions() {
	  	
    	ArrayList<Question> entries = new ArrayList<Question>();
    	
    	for(int i = 1; i < 50; i++) {

    		Question q = new Question("Q: " + i + " Lorem ipsum dolor sit amet, consectetur adipiscing elit", "Author "+i, "Title" + i);    		
    		
//    		Random rand = new Random();
//    		int rn = rand.nextInt(10) + 1;
//    		for (int j = 0; j < rn; ++j){
//    			Answer a = new Answer("1","1");
//    			q.addAnswer(a);
//    		}
=======
    		
    		String author = "Author "+i;
    		String text = "Q: " + i + " Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.  ";
    		Question q = new Question(text, author, "Title"+i);
    		mQandA.add(q);
    		
    		
    		Random rand = new Random();
    		int rn = rand.nextInt(10) + 1;
    		for (int j = 0; j < rn; ++j){
    			Answer a = new Answer("text"+j, "user"+j);
    			q.incrementVotes();
    			q.addAnswer(a);
    			mQandA.add(a);
    		}
    		
    		entries.add(q);
    	} 	
    }
    
}
