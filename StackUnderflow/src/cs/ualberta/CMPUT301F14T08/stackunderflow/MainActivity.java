package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.List;

//import com.survivingwithandroid.actionbartabnavigation.R;
//import com.survivingwithandroid.actionbartabnavigation.TabFragment;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;


public class MainActivity extends Activity implements TabListener {// implements ActionBar.TabListener{//BaseFragmentActivity implements ActionBar.TabListener {
	
	//ActionBar.Tab Tab, Tab2;
	//Fragment mTestFragment = new TestFragment();
    //Fragment mTest2Fragment = new TestFragment2();
    //Fragment fragment;
	/*
    @Override
	protected Fragment newFragmentType() {
		//return new MainFragment();
		return new TestFragment();
	}
	*/
	List<Fragment> fragList = new ArrayList<Fragment>();
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		//setContentView(R.layout.main_list_view);

		//setHasOptionsMenu(true);
	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    
	   
	    /*
        Tab = actionBar.newTab().setText(R.string.newest);
        Tab2 = actionBar.newTab().setText(R.string.popular);		
        TabListener tl = new TabListener(this,
        		getResources().getString(R.string.newest), TestFragment.class);

        Tab.setTabListener(tl);
        Tab2.setTabListener(this);
        
        actionBar.addTab(Tab);
        actionBar.addTab(Tab2);
        m
        */
		Tab tab = actionBar.newTab();
		tab.setText("Newest");
		tab.setTabListener(this);
		actionBar.addTab(tab);
		
		Tab tab2 = actionBar.newTab();
		tab2.setText("Popular");
		tab2.setTabListener(this);
		actionBar.addTab(tab2);
	    
        //actionBar.addTab(actionBar.newTab().setText(R.string.newest).setTabListener(this));
	    //actionBar.addTab(actionBar.newTab().setText(R.string.popular).setTabListener(this));
	    
        
	    //sPostController = PostController.getInstance(getActivity());
	    
		//adapter = new PostAdapter(getActivity(), sPostController);
		//setListAdapter(adapter);
	    
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/
	
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
			// When the given tab is selected, show the tab contents in the
			// container view.
		//Fragment mTestFragment = new TestFragment();
	    //Fragment mTest2Fragment = new TestFragment2();
		//fragmentTransaction.replace(R.id.fragment_container, fragment);
		
		
		//fragmentTransaction.replace(R.id.fragment_container, fragment);
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
		
		Fragment f = null;
		TestFragment tf = null;
		
		if (fragList.size() > tab.getPosition())
				fragList.get(tab.getPosition());
		
		if (f == null) {
			tf = new TestFragment();
			Bundle data = new Bundle();
			data.putInt("idx",  tab.getPosition());
			tf.setArguments(data);
			fragList.add(tf);
		}
		else
			tf = (TestFragment) f;
		
		fragmentTransaction.replace(android.R.id.content, tf);
		
		}

		@Override
		public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
			//TODO implement
	        //if(mFragment!=null)
	            //fragmentTransaction.detach(mFragment);
			if (fragList.size() > tab.getPosition()) {
				fragmentTransaction.remove(fragList.get(tab.getPosition()));
			}
		}

		@Override
		public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
			//TODO implement
		}
	
    /** Called when the activity is first created. */

}