package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PostFragment extends Fragment {

	private PostController sPostController;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		sPostController = new PostController();
	}
	
	@Override
	public void onPause(){
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
		super.onCreateOptionsMenu(menu, menuInflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem){
	    switch (menuItem.getItemId()) {
        case R.id.new_answer:
        	if(sPostController.getPostManager().getUserProfileManager().getUserProfile().getUsername.equals(null)){
        		UsernameFragment dialog = new UsernameFragment();
        		dialog.setTargetFragment(PostFragment.this, REQUEST_USERNAME)
        		dialog.show(fm, DIALOG_USERNAME);
        	}
        	else{
        		Intent i = new Intent(getActivity(), AnswerActivity.class);
        		startActivity(i);
        	}
            return true;
        case R.id.new_reply:
        	if(sPostController.getPostManager().getUserProfileManager().getUserProfile().getUsername.equals(null)){
        		FragmentManager fm = getActivity().getFragmentManager();
        		UsernameFragment dialog = new UsernameFragment();
        		dialog.setTargetFragment(PostFragment.this, REQUEST_USERNAME)
        		dialog.show(fm, DIALOG_USERNAME);
        	}
        	else{
        		//new reply
        	}
        	return true;
        default:
            return super.onOptionsItemSelected(menuItem);
    	}
	}
}
