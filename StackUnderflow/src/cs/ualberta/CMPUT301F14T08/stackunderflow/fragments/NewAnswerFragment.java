
package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import java.util.UUID;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.LocManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;
/**
 * NewAnswerFragment - Called from NewAnswerActivity sets the user to Guest and makes the takes input from the NewAnswerFragment interface. 
 * Saves the data in the Edit text that the user inputs and submit it to the proper post manager. Also checks if the user has input anything
 * this will not allow the user to submit a blank field. If they attempt to it will display a toast telling them to.
 * @author Cmput301 Winter 2014 Group 8
 */
public class NewAnswerFragment extends NewPostFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.new_answer_title);
        mPostId = (UUID)getArguments().getSerializable(EXTRA_POST_ID);
        sPostController = PostController.getInstanceForID(getActivity(), mPostId);
    }	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        
    	// Call NewPostFragment onCreate View
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        
        //Hide question title textview
    	mPostTitle.setVisibility(View.GONE);
    	
    	//Change hint to display for answer textview
    	mPostBody.setHint(R.string.new_answer_fragment_edittext_body);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {           

                // TODO: Change this to use usernames after implementing user profile
                String author = UserProfileManager.getInstance(getActivity()).getUsername();
                String body = mPostBody.getText().toString();
                LatLng location = null;

                //Checks if fields are left blank
                if(body.equalsIgnoreCase("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter an answer", 
                            Toast.LENGTH_LONG).show();
                }

                else {
                    Answer mAnswer = null;
                    if (mJPEGByteArray != null) {
                    	//Encode byte array as a string to faster storage online
                        String picture = Base64.encodeToString(mJPEGByteArray, Base64.DEFAULT);

                        mAnswer = new Answer(body, author, picture);
                    }
                    else {
                        mAnswer = new Answer(body, author);  
                    }
                    Log.d("lat", ""+mLatitude);
                    Log.d("lon", ""+mLongitude);
                    if(mLatitude != LocManager.LOC_ERROR && mLongitude != LocManager.LOC_ERROR) {
                        location = new LatLng(mLatitude, mLongitude);
                    	mAnswer.setLocation(location);
                    }

                    Question qparent = (Question) sPostController.getQuestion(mPostId);
                    sPostController.getPostManager().addAnswer(qparent, mAnswer);
                    sPostController.getPostManager().setUserLocation(location);
                    getActivity().setResult(0);
                    getActivity().finish();
                }
            }
        });

        return v;
    }
}
