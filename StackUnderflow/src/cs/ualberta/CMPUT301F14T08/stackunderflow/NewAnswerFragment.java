package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.UUID;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewAnswerFragment extends NewPostFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.new_answer_title);
		mPostId = (UUID)getArguments().getSerializable(EXTRA_POST_ID);
		
	}	
	
	/*
	@Override
	public void onPause(){
		super.onPause();
	}
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		
		View v = inflater.inflate(R.layout.new_answer_fragment, parent, false);		
		
		
		// (DISABLED extends NewPostFragment, while Jon has a look at MainActivity issues)
		mPostBody = (EditText)v.findViewById(R.id.new_answer_fragment_edittext_body);
		mPostBody.setText(getResources().getString(R.string.new_answer_fragment_edittext_body));
		//mPostBody.setSelectAllOnFocus(true);
		
		mUploadPictureButton = (ImageButton) v.findViewById(R.id.new_answer_fragment_upload_photo_button);
        mUploadPictureButton.setImageResource(R.drawable.picture_dark);
        
        mSubmitButton = (Button) v.findViewById(R.id.new_answer_fragment_submit_button);
        
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {       	
            	
            	
            	String author = "user"; // Implement user profile
            	String body = mPostBody.getText().toString();
            	
            	Answer mAnswer = new Answer(body, author);      	
            	Question qparent = (Question) sPostController.getPostManager().getPost(mPostId);
            	sPostController.getPostManager().addAnswer(qparent, mAnswer);

            	getActivity().finish();
            	
            }
        });
        
        //TODO Implement picture dialog/upload
        mUploadPictureButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// Implement picture dialog
				
			}
		});
		
        mPostBody.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// Implement picture dialog
				mPostBody.setText("");
			}
		});
			
        
        return v;
	}	
	
}
