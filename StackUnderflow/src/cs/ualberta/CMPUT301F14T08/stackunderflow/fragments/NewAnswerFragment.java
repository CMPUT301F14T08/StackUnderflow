
package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import java.util.UUID;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.id;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.layout;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.string;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
    int getViewID() {
        return R.layout.new_answer_fragment;
    }
    
    @Override
    int getBodyTextViewID() {
        return R.id.new_answer_fragment_edittext_body;
    }

    @Override
    int getAddPictureButtonID() {
        return R.id.new_answer_fragment_upload_photo_button;
    }

    @Override
    int getSubmitButtonID() {
        return R.id.new_answer_fragment_submit_button;
    }   
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		// Call NewPostFragment onCreate View
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		
		
		// Set up on click listener for submitting answers
        // For adding images see the super class onCreateView
        mPostBody = (EditText)v.findViewById(getBodyTextViewID());
        mUploadPictureButton = (Button) v.findViewById(getAddPictureButtonID());
        mSubmitButton = (Button) v.findViewById(getSubmitButtonID());
        
        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {           
                
                // TODO: Change this to use usernames after implementing user profile
                String author = "Guest"; 
                String body = mPostBody.getText().toString();
                
                //Checks if fields are left blank
                if(body.equalsIgnoreCase("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter an answer", 
                               Toast.LENGTH_LONG).show();
                }
                else{
                Answer mAnswer = new Answer(body, author);          
                Question qparent = (Question) sPostController.getQuestion(mPostId);
                sPostController.getPostManager().addAnswer(qparent, mAnswer);
                getActivity().setResult(0);
                getActivity().finish();
                }
            }
        });
        
        return v;
	}
}
