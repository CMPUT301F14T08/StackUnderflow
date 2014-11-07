/*
 * NewQuestionFragment - Called from NewQuestionACtivity - User to allow a user to input a question. Takes input and saves
 * the information to the correct post manager.  Will not allow a user to input blank fields. 
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NewQuestionFragment extends NewPostFragment {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//getActivity().setTitle(R.string.new_question_title);
	}	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

		View v = inflater.inflate(R.layout.new_question_fragment, parent, false);		
		mPostTitle = (EditText) v.findViewById(R.id.new_question_fragment_edittext_title);		
		mPostBody = (EditText)v.findViewById(R.id.new_question_fragment_edittext_body);		
		mUploadPictureButton = (ImageButton) v.findViewById(R.id.new_question_fragment_upload_photo_button);
		mUploadPictureButton.setImageResource(R.drawable.picture_dark);
		mSubmitButton = (Button) v.findViewById(R.id.new_question_fragment_submit_button);
		mSubmitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {       	
				
				String title = mPostTitle.getText().toString();
				String author = "Guest";
				String body = mPostBody.getText().toString();
				
				//Checks if fields are left blank
				if(title.equalsIgnoreCase("")||body.equalsIgnoreCase("") || (title.equalsIgnoreCase("")&&body.equalsIgnoreCase(""))){
					Toast.makeText(getActivity().getApplicationContext(), "Please Fill all Fields", 
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

		mPostTitle.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// Implement picture dialog
				mPostTitle.setText("");
			}
		});


		return v;

	}	

}
