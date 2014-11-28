package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import java.util.UUID;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MapActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.NewImageDialogFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.UsernameDialog;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.LocManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;
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
	protected static final int REQUEST_MAP_CODE = 11223344;

    protected PostController sPostController;
    protected UUID mPostId;
    protected String mJPEGFileName;
    protected byte mJPEGByteArray[];
    protected Double mLatitude;
    protected Double mLongitude; 

    protected EditText mPostTitle;
    protected EditText mPostBody;
    protected Button mUploadPictureButton;
    protected CheckBox mCheckBox;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.post_menu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.new_post_fragment, parent, false);     

        // Get Views IDs
        mPostTitle = (EditText) v.findViewById(R.id.new_post_fragment_edittext_title);
        mPostBody = (EditText)v.findViewById(R.id.new_post_fragment_edittext_body);
        mUploadPictureButton = (Button) v.findViewById(R.id.new_post_fragment_upload_photo_button);
        mSubmitButton = (Button) v.findViewById(R.id.new_post_fragment_submit_button);
        mJPEGByteArray = null;
        mJPEGFileName = null;
        mLatitude = null;
        mLongitude = null;


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
        
        mCheckBox = (CheckBox) v.findViewById(R.id.new_post_fragment_location_checkbox);
        
        mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Intent intent = new Intent(getActivity(), MapActivity.class);                
		            startActivityForResult(intent, REQUEST_MAP_CODE);
				}
				else{
					mCheckBox.setText(getResources().getString(R.string.new_post_fragment_location_checkbox));
				}
				
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
        if (requestCode == REQUEST_MAP_CODE && resultCode == Activity.RESULT_OK) {
			mLatitude = data.getDoubleExtra("latitude", LocManager.LOC_ERROR);
			mLongitude = data.getDoubleExtra("longitude", LocManager.LOC_ERROR);
			if(mLatitude != LocManager.LOC_ERROR && mLongitude != LocManager.LOC_ERROR){
					mCheckBox.setText(getResources().getString(R.string.new_post_fragment_location_checkbox) + LocManager.getLocationString(getActivity(), new LatLng(mLatitude, mLongitude)));
        	}
		}
    }
}