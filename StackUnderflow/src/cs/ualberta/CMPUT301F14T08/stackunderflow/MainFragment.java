package cs.ualberta.CMPUT301F14T08.stackunderflow;

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
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainFragment extends ListFragment implements ActionBar.TabListener{

	private PostController sPostController;
	private PostAdapter adapter;
		
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
	    
	    sPostController = PostController.getInstance(getActivity());
	    
		adapter = new PostAdapter(getActivity(), sPostController);
		setListAdapter(adapter);
	    
	}
	
	@Override
	public void onResume(){
		super.onResume();
		adapter.notifyDataSetChanged();
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
		Intent i;
		
		Post p = ((PostAdapter)getListAdapter()).getItem(position);
		
		// Move the putExtra & startActivity out once AnswerActivity is created
		if (p instanceof Question) {
			i = new Intent(getActivity(), QuestionActivity.class);
		}
		else {
			i = new Intent(getActivity(), AnswerActivity.class);
		}
		i.putExtra(PostFragment.EXTRA_POST_ID, p.getID());
		startActivity(i);
		// place putExtra and start activity down here, remove braces on statements
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

}
