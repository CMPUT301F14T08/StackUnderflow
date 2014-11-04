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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity implements TabListener {// implements ActionBar.TabListener{//BaseFragmentActivity implements ActionBar.TabListener {
	
	/*
    @Override
	protected Fragment newFragmentType() {
		//return new MainFragment();
		return new TestFragment();
	}
	*/
	
	private PostController sPostController;
	private PostAdapter adapter;
	List<Fragment> fragmentList = new ArrayList<Fragment>();
	
	
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
	
	//Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//super.onCreateOptionsMenu(menu, inflater);
		getMenuInflater().inflate(R.menu.fragment_main_menu, menu);
		return true;
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
				
		//return super.onOptionsItemSelected(item);
		return onOptionsItemSelected(item);
	}
	
	
	/*
	@Override
	public void onResume(){
		//super.onResume();
		adapter.notifyDataSetChanged();
	}
	*/
	
	
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	
		Fragment f = null;
		MainFragment tf = null;
		
		if (fragmentList.size() > tab.getPosition())
				fragmentList.get(tab.getPosition());
		
		if (f == null) {
			tf = new MainFragment();
			Bundle data = new Bundle();
			data.putInt("idx",  tab.getPosition());
			tf.setArguments(data);
			fragmentList.add(tf);
		}
		else
			tf = (MainFragment) f;
		
		fragmentTransaction.replace(android.R.id.content, tf);
		
		}

		@Override
		public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	        //if(mFragment!=null)
	            //fragmentTransaction.detach(mFragment);
			if (fragmentList.size() > tab.getPosition()) {
				fragmentTransaction.remove(fragmentList.get(tab.getPosition()));
			}
		}

		@Override
		public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
			//No implementation required at present
		}
		

	
    /** Called when the activity is first created. */

}