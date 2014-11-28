
package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MapActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.LocManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;
/**
 * NewQuestionFragment - Called from NewQuestionACtivity - User to allow a user to input a question. Takes input and saves
 * the information to the correct post manager.  Will not allow a user to input blank fields. 
 * @author Cmput301 Winter 2014 Group 8
 */
public class NewQuestionFragment extends NewPostFragment {

	public static final int MAP_CODE = 11223344;
	public static final double LOC_ERROR = 999999;
	
	//@Override
	//public void onCreate(Bundle savedInstanceState){
		//super.onCreate(savedInstanceState);
		//getActivity().setTitle(R.string.new_question_title);
	//}
	
	private CheckBox mCheckBox;
	private LatLng location = null;
	

    @Override
    int getViewID() {
        return R.layout.new_question_fragment;
    }

    @Override
    int getBodyTextViewID() {
        return R.id.new_question_fragment_edittext_body;
    }

    @Override
    int getAddPictureButtonID() {
        // TODO Auto-generated method stub
        return R.id.new_question_fragment_upload_photo_button;
    }

    @Override
    int getSubmitButtonID() {
        // TODO Auto-generated method stub
        return R.id.new_question_fragment_submit_button;
    }   
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == MAP_CODE && resultCode == Activity.RESULT_OK) {
    			double lat = data.getDoubleExtra("lat", LOC_ERROR);
    			double lon = data.getDoubleExtra("lon", LOC_ERROR);
    			if(lat != LOC_ERROR && lon != LOC_ERROR){
    				location = new LatLng(lat, lon);
    					mCheckBox.setText("Location: " + LocManager.getLocationString(getActivity(), location));
            	}
    		}  
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        // Call NewPostFragment onCreateView
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        // Set up onClickListner for Adding new Questions
        // For adding images see the super class onCreateView
        mPostTitle = (EditText) v.findViewById(R.id.new_question_fragment_edittext_title);		
        mPostBody = (EditText)v.findViewById(R.id.new_question_fragment_edittext_body);		
        mSubmitButton = (Button) v.findViewById(R.id.new_question_fragment_submit_button);
        mCheckBox = (CheckBox) v.findViewById(R.id.new_question_fragment_location_checkbox);
        mCheckBox.setText("Location:");
        
        mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Log.d("CHECKED", String.valueOf(isChecked));
					Intent intent = new Intent(getActivity(), MapActivity.class);                
		            startActivityForResult(intent, MAP_CODE);
				}
				else{
					mCheckBox.setText("Location:");
				}
				
			}
		});

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {       	

                String title = mPostTitle.getText().toString();
                // TODO: Change this to use usernames after implementing user profile
                String author = UserProfileManager.getInstance(getActivity()).getUsername();
                String body = mPostBody.getText().toString();

                //Checks if fields are left blank
                if(title.equalsIgnoreCase("")||body.equalsIgnoreCase("") || (title.equalsIgnoreCase("")&&body.equalsIgnoreCase(""))){
                    Toast.makeText(getActivity().getApplicationContext(), "Please Fill All Fields", 
                            Toast.LENGTH_LONG).show();
                }
                else{

                    Intent msg = new Intent();
                    msg.putExtra("question.title", title);
                    msg.putExtra("question.author", author);
                    msg.putExtra("question.body", body);
                    msg.putExtra("question.picture", mJPEGByteArray);

                    getActivity().setResult(Activity.RESULT_OK, msg);
                    getActivity().finish();
                }
            }
        });



        return v;

    }	

}
