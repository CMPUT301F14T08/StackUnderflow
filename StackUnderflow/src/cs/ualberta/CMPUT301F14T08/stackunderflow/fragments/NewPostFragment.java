package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import java.io.File;
import java.util.UUID;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.id;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.menu;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.NewImageDialogFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.UsernameDialog;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    protected static final String DIALOG_IMAGE = "image";
    protected static final int REQUEST_USERNAME = 0;
    protected static final int REQUEST_IMAGE = 1;

    protected PostController sPostController;
    protected UUID mPostId;
    protected String mJPEGFileName;
    protected byte mJPEGByteArray[];

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
        checkUsername();
    }

    protected void checkUsername(){
        //access user profile manager, check the name -- ISSUE WITH INITIAL USE
        UserProfileManager man = UserProfileManager.getInstance(getActivity());
        if (man.getUsername() == "Guest" || man.getUsername() == "" || man.getUsername() == null){
            UsernameDialog.showDialog(getFragmentManager());
        }
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
        mJPEGByteArray = null;
        mJPEGFileName = null;


        //Calls newImageDialogFragment with existing picture info (byteArray and fileName)
        mUploadPictureButton.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();
                NewImageDialogFragment dialog = NewImageDialogFragment.newInstance(mJPEGFileName, mJPEGByteArray);
                dialog.setTargetFragment(NewPostFragment.this, REQUEST_IMAGE);
                dialog.show(fm, DIALOG_IMAGE);

            }
        });

        return v;
    }

    //gets picture as a byteArray and file name as a string from NewImageDialogFragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                mJPEGByteArray = bundle.getByteArray("BYTES"); //null exception error
                mJPEGFileName = bundle.getString("NAME");
                if (mJPEGFileName != null) {
                    mUploadPictureButton.setText(mJPEGFileName);
                }
                else {
                    mUploadPictureButton.setText("Upload Photo");
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                mJPEGByteArray = null;
                mJPEGFileName = null;
                mUploadPictureButton.setText("Upload Photo");
            }
        }
    }
}