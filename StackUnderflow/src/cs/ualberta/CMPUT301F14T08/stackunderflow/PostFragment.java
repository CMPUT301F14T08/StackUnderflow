package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.UUID;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PostFragment extends Fragment {
	public static final String EXTRA_POST_ID = "cs.ualberta.CMPUT301F14T08.stackunderflow.post_id";
	protected static final String DIALOG_USERNAME = "username";
    protected static final int REQUEST_USERNAME = 0;
    
	protected PostController sPostController;
	
	protected Post mPost;
	protected LinearLayout mLinearLayoutTop;
	protected TextView mPostBody;
	protected ImageButton mUpvoteButton;
	protected TextView mUpvoteCountTextView;
	protected ImageButton mFavoriteButton;
	protected TextView mFavoriteTextView;
	protected ImageButton mPictureButton;
	protected TextView mUsername;
	protected LinearLayout mLinearLayoutBottom;
	protected ImageButton mAnswersButton;
	protected TextView mAnswersTextView;
	
	protected int mBlackColor;
	protected int mWhiteColor;
	protected int mBlueColor;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		sPostController = new PostController();
		UUID postId = (UUID)getArguments().getSerializable(EXTRA_POST_ID);
		mPost = sPostController.getPostManager().getPost(postId);
		
		mBlackColor = getResources().getColor(R.color.black);
		mWhiteColor = getResources().getColor(R.color.white);
		mBlueColor = getResources().getColor(R.color.blue);
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
		super.onCreateOptionsMenu(menu, menuInflater);
		menuInflater.inflate(R.menu.post_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem){
	    switch (menuItem.getItemId()) {
        case R.id.menu_item_new_reply:
        	if(sPostController.getPostManager().getUserProfileManager().getUserProfile().getUsername.equals(null)){
        		FragmentManager fm = getActivity().getFragmentManager();
        		UsernameFragment dialog = new UsernameFragment();
        		dialog.setTargetFragment(PostFragment.this, REQUEST_USERNAME);
        		dialog.show(fm, DIALOG_USERNAME);
        	}
        	else{
        		//new reply
        		String toastString = "Someone needs to implement code to show add replies";
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_LONG);
                toast.show(); 
        	}
        	return true;
        default:
            return super.onOptionsItemSelected(menuItem);
    	}
	}
}
