package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.UUID;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewPostFragment extends Fragment {
	
	public static final String EXTRA_POST_ID = "cs.ualberta.CMPUT301F14T08.stackunderflow.post_id";
	protected static final String DIALOG_USERNAME = "username";
    protected static final int REQUEST_USERNAME = 0;
    
	protected PostController sPostController;
	protected UUID mPostId;
	
	protected EditText mPostTitle;
	protected EditText mPostBody;
	protected ImageButton mUploadPictureButton;
	protected Button mSubmitButton;
	
	protected int mBlackColor;
	protected int mWhiteColor;
	protected int mBlueColor;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		sPostController = PostController.getInstance(getActivity());
		
		mBlackColor = getResources().getColor(R.color.black);
		mWhiteColor = getResources().getColor(R.color.white);
		mBlueColor = getResources().getColor(R.color.blue);
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
//        	if(sPostController.getPostManager().getUserProfileManager().getUserProfile().getUsername.equals(null)){
//        		FragmentManager fm = getActivity().getFragmentManager();
//        		UsernameFragment dialog = new UsernameFragment();
//        		dialog.setTargetFragment(PostFragment.this, REQUEST_USERNAME);
//        		dialog.show(fm, DIALOG_USERNAME);
//        	}
//        	else{
//        		//new reply
//        		String toastString = "Someone needs to implement code to show add replies";
//                Toast toast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_LONG);
//                toast.show(); 
//        	}
        	return true;
        	
        default:
            return super.onOptionsItemSelected(menuItem);
    	}
	}
    	
    	
	
	
}
