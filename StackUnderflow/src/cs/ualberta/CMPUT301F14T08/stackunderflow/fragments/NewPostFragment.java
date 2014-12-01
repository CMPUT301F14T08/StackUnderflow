
package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import java.util.UUID;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MapActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.NewImageDialogFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs.UsernameDialog;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.LocManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;

/**
 * This is used when a user would like to make a new post. This will also allow the user to input a
 * new user name if the user has not yet chosen one If the user does not choose a user name it will
 * simply default to User.
 * 
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
    protected boolean includeLocation;

    protected EditText mPostTitle;
    protected EditText mPostBody;
    protected Button mUploadPictureButton;
    protected Button mLocationButton;
    protected Button mSubmitButton;

    protected int mBlackColor;
    protected int mWhiteColor;
    protected int mBlueColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUsername();
        includeLocation = false;
    }

    protected void checkUsername() {
        // access user profile manager, check the name -- ISSUE WITH INITIAL USE
        UserProfileManager man = UserProfileManager.getInstance(getActivity());
        if (man.getUsername() == "Guest" || man.getUsername() == "" || man.getUsername() == null) {
            UsernameDialog.showDialog(getFragmentManager());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.post_menu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.new_post_fragment, parent, false);

        // Get Views IDs
        mPostTitle = (EditText) v.findViewById(R.id.new_post_fragment_edittext_title);
        mPostBody = (EditText) v.findViewById(R.id.new_post_fragment_edittext_body);
        mUploadPictureButton = (Button) v.findViewById(R.id.new_post_fragment_upload_photo_button);
        mSubmitButton = (Button) v.findViewById(R.id.new_post_fragment_submit_button);
        mJPEGByteArray = null;
        mJPEGFileName = null;
        mLatitude = LocManager.LOC_ERROR;
        mLongitude = LocManager.LOC_ERROR;

        // Calls newImageDialogFragment with existing picture info (byteArray and fileName)
        mUploadPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();
                NewImageDialogFragment dialog = NewImageDialogFragment.newInstance(mJPEGFileName,
                        mJPEGByteArray);
                dialog.setTargetFragment(NewPostFragment.this, REQUEST_IMAGE);
                dialog.show(fm, DIALOG_IMAGE);

            }
        });

        mLocationButton = (Button) v.findViewById(R.id.new_post_fragment_location_button);

        mLocationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                includeLocation = !includeLocation;

                // Disable location if running on an emulator
                if (Build.BRAND.equalsIgnoreCase("generic")) {
                    Toast.makeText(getActivity(),
                            "This feature is not supported using an emulator", Toast.LENGTH_LONG)
                            .show();
                }
                else {
                    if (includeLocation) {
                        if (PostController.getInstanceNoRefresh(getActivity()).isOnline()) {
                            Intent intent = new Intent(getActivity(), MapActivity.class);
                            startActivityForResult(intent, REQUEST_MAP_CODE);
                        }
                        else {
                            Toast.makeText(getActivity(), "No network connection",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        mLocationButton.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.mapmarker, 0, 0, 0);
                        mLocationButton.setText(getResources().getString(
                                R.string.new_post_fragment_location_button_false));
                        mLocationButton.setTextColor(getResources().getColor(R.color.black));
                        mLatitude = LocManager.LOC_ERROR;
                        mLongitude = LocManager.LOC_ERROR;
                    }
                }

            }
        });

        return v;
    }

    // gets picture as a byteArray and file name as a string from NewImageDialogFragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                mJPEGByteArray = bundle.getByteArray("BYTES"); // null exception error
                mJPEGFileName = bundle.getString("NAME");
                if (mJPEGFileName != null) {
                    mUploadPictureButton.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.picture_blue, 0, 0, 0);
                    mUploadPictureButton.setText(" " + mJPEGFileName);
                    mUploadPictureButton.setTextColor(getResources().getColor(R.color.blue));
                }
                else {
                    mUploadPictureButton.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.picture_dark, 0, 0, 0);
                    mUploadPictureButton.setText(getResources().getString(
                            R.string.new_post_fragment_upload_photo_textview));
                    mUploadPictureButton.setTextColor(getResources().getColor(R.color.black));
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                mJPEGByteArray = null;
                mJPEGFileName = null;
                mUploadPictureButton.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.picture_dark, 0, 0, 0);
                mUploadPictureButton.setText(getResources().getString(
                        R.string.new_post_fragment_upload_photo_textview));
                mUploadPictureButton.setTextColor(getResources().getColor(R.color.black));
            }
        }
        if (requestCode == REQUEST_MAP_CODE && resultCode == Activity.RESULT_OK) {
            mLatitude = data.getDoubleExtra("latitude", LocManager.LOC_ERROR);
            mLongitude = data.getDoubleExtra("longitude", LocManager.LOC_ERROR);
            if (mLatitude != LocManager.LOC_ERROR && mLongitude != LocManager.LOC_ERROR) {
                mLocationButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mapmarker_blue,
                        0, 0, 0);
                mLocationButton.setText(getResources().getString(
                        R.string.new_post_fragment_location_button_true)
                        + LocManager.getLocationString(getActivity(), new LatLng(mLatitude,
                                mLongitude)));
                mLocationButton.setTextColor(getResources().getColor(R.color.blue));
            }
        }
    }
}
