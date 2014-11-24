
package cs.ualberta.CMPUT301F14T08.stackunderflow;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * NewQuestionFragment - Called from NewQuestionACtivity - User to allow a user to input a question. Takes input and saves
 * the information to the correct post manager.  Will not allow a user to input blank fields. 
 * @author Cmput301 Winter 2014 Group 8
 */
public class NewQuestionFragment extends NewPostFragment {

	/*@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//getActivity().setTitle(R.string.new_question_title);
	}*/	
	
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

	    // Call NewPostFragment onCreateView
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        	
        // Set up onClickListner for Adding new Questions
        // For adding images see the super class onCreateView
		mPostTitle = (EditText) v.findViewById(R.id.new_question_fragment_edittext_title);		
		mPostBody = (EditText)v.findViewById(R.id.new_question_fragment_edittext_body);		
		mSubmitButton = (Button) v.findViewById(R.id.new_question_fragment_submit_button);
		
		mSubmitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {       	
				
				String title = mPostTitle.getText().toString();
                // TODO: Change this to use usernames after implementing user profile
				String author = "Guest";
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

					getActivity().setResult(Activity.RESULT_OK, msg);
					getActivity().finish();
				}
			}
		});



		return v;

	}	

}
