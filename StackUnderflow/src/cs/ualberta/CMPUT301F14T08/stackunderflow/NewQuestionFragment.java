package cs.ualberta.CMPUT301F14T08.stackunderflow;

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

public class NewQuestionFragment extends NewPostFragment {
	// (TEMPORARILY DISABLED extends NewPostFragment, while Jon has a look at MainActivity issues)

	//TODO: Implement Fragment: currently just a layout/view 
	//private Question mQuestion;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//getActivity().setTitle(R.string.new_question_title);
		Log.i("LOG", "### Reached New Question Fragment ");
	}	
	
	/*
	@Override
	public void onPause(){
		super.onPause();
	}
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		
		View v = inflater.inflate(R.layout.new_question_fragment, parent, false);		
		
		
		Log.i("LOGGING", "### Reached New Question Fragment ");
		// (DISABLED extends NewPostFragment, while Jon has a look at MainActivity issues)
        mPostTitle = (EditText) v.findViewById(R.id.new_question_fragment_edittext_title);
        
		mPostTitle.setText(getResources().getString(R.string.new_question_fragment_edittext_title));
		mPostBody = (EditText)v.findViewById(R.id.new_question_fragment_edittext_body);
		mPostBody.setText(getResources().getString(R.string.new_question_fragment_edittext_body));
		
		mUploadPictureButton = (ImageButton) v.findViewById(R.id.new_question_fragment_upload_photo_button);
       
        mUploadPictureButton.setImageResource(R.drawable.picture_dark);
        
        mSubmitButton = (Button) v.findViewById(R.id.new_question_fragment_submit_button);
        
        
        /*
		if(mQuestion.hasPicture()){
			mUploadPictureButton.setImageResource(R.drawable.picture_white);
			mUploadPictureButton.setEnabled(true);			
		}
		*/
        
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {       	
            	
            	
            	String title = mPostTitle.getText().toString();
            	String author = "user";
            	String body = mPostBody.getText().toString();
            	
            			
            	Question mQuestion = new Question(body, author, title);
            	//mQuestion.setTitle(mPostTitle.getText().toString());
            	//mQuestion.setText(mPostBody.getText().toString());
            	// set picture;
            	// save/cache;
            	sPostController.getPostManager().addQuestion(mQuestion);
            	
				Intent i = new Intent(getActivity(), MainActivity.class);
				//i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID());
				startActivity(i);
			   
            	
            }
        });
        
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
