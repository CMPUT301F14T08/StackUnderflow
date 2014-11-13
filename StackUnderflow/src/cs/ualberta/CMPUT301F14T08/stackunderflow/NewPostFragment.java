package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.UUID;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
/**
 * This is used when a user would like to make a new post. This will also allow the user to input a new user name if the user has not yet chosen one
 * If the user does not choose a user name it will simply default to User. 
 * @author Cmput301 Winter 2014 Group 8
 */
public abstract class NewPostFragment extends Fragment {
	
	public static final String EXTRA_POST_ID = "cs.ualberta.CMPUT301F14T08.stackunderflow.post_id";
	protected static final String DIALOG_USERNAME = "username";
    protected static final int REQUEST_USERNAME = 0;
    
	protected PostController sPostController;
	protected UUID mPostId;
	
	protected EditText mPostTitle;
	protected EditText mPostBody;
	protected Button mUploadPictureButton;
	protected Button mSubmitButton;
	
	protected int mBlackColor;
	protected int mWhiteColor;
	protected int mBlueColor;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	abstract int getViewID();
	abstract int getBodyTextViewID();
	abstract int getAddPictureButtonID();
	abstract int getSubmitButtonID();
	
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
	
	   @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
	        
	        View v = inflater.inflate(getViewID(), parent, false);     

	        // Get Views IDs
	        mPostBody = (EditText)v.findViewById(getBodyTextViewID());
	        mUploadPictureButton = (Button) v.findViewById(getAddPictureButtonID());
	        mSubmitButton = (Button) v.findViewById(getSubmitButtonID());
	        

	        //TODO Implement picture dialog/upload
	        mUploadPictureButton.setOnClickListener(new View.OnClickListener() {            
	            @Override
	            public void onClick(View v) {
	                // Implement picture dialog
	                
	            }
	        });
	        
	        return v;
	    }
    	
    	
	
	
}