package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewQuestionFragment extends Fragment {
	// (TEMPORARILY DISABLED extends NewPostFragment, while Jon has a look at MainActivity issues)

	//TODO: Implement Fragment: currently just a layout/view 
	//private Question mQuestion;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//getActivity().setTitle(R.string.new_question_title);
	}	
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		
		View v = inflater.inflate(R.layout.new_question_fragment, parent, false);		
        
		/* (DISABLED extends NewPostFragment, while Jon has a look at MainActivity issues)
        mPostTitle = ((EditText) v.findViewById(R.id.new_question_fragment_edittext_title));
		mPostTitle.setText(R.id.new_question_fragment_edittext_title);
		mPostBody = (EditText)v.findViewById(R.id.new_question_fragment_edittext_body);
		mPostBody.setText(R.id.new_question_fragment_edittext_body);
        mUploadPictureButton = (ImageButton)v.findViewById(R.id.post_fragment_button_upvote);
        mSubmitButton = (Button) v.findViewById(R.id.new_question_fragment_submit_button);
        mUploadPictureButton.setImageResource(R.drawable.picture_dark);
        
		if(mQuestion.hasPicture()){
			mUploadPictureButton.setImageResource(R.drawable.picture_white);
			mUploadPictureButton.setEnabled(true);			
		}
        
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {       	
            	
            	mQuestion.setTitle(mPostTitle.getText().toString());
            	mQuestion.setText(mPostBody.getText().toString());
            	// set picture;
            	// save/cache;
            	sPostController.getPostManager().addQuestion(mQuestion);            
            	
            }
        });
        
        //TODO Implement picture dialog/upload
        mUploadPictureButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// Implement picture dialog
				
			}
		});
		*/
        
        return v;
		
	}	
	
}
