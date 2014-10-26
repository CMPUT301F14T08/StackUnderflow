package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnswerFragment extends PostFragment {
	
	private Answer mAnswer;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.question_title);
		mAnswer = (Answer)sPostController.getPostManager().getPost(mPostId);
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
			case R.id.menu_item_new_answer:
//				if(sPostController.getPostManager().getUserProfileManager().getUserProfile().getUsername.equals(null)){
//	        		FragmentManager fm = getActivity().getFragmentManager();
//					UsernameFragment dialog = new UsernameFragment();
//					dialog.setTargetFragment(AnswerFragment.this, REQUEST_USERNAME);
//					dialog.show(fm, DIALOG_USERNAME);
//				}
//				else{
//					Intent i = new Intent(getActivity(), NewAnswerActivity.class);
//					i.putExtra(NewAnswerFragment.EXTRA_PARENT_QUESTION_ID, mAnswer.getParentQuestion().getID());
//					startActivity(i);
//				}
			    return true;
			case R.id.menu_item_back_to_question:
					//Code to go back to question
			    return true;
			default:
				return super.onOptionsItemSelected(item);
	    } }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.question_fragment, parent, false);
		
		
		mPostBody = (TextView)v.findViewById(R.id.question_fragment_textview_body);
		mPostBody.setText(mAnswer.getText() + " " + mAnswer.getDate().toString());
		
		mUpvoteButton = (ImageButton)v.findViewById(R.id.question_fragment_button_upvote);
		mUpvoteButton.setImageResource(mAnswer.getUserAttributes().getIsUpvoted() ? R.drawable.upvote_full : R.drawable.upvote_empty);
		
		mUpvoteCountTextView = (TextView)v.findViewById(R.id.question_fragment_textview_upvotes);
		mUpvoteCountTextView.setText(mAnswer.getVotes());
		
		mFavoriteButton = (ImageButton)v.findViewById(R.id.question_fragment_button_favorite);
		mFavoriteButton.setImageResource(mAnswer.getUserAttributes().getIsFavorited() ? R.drawable.star_full_small : R.drawable.star_empty_small);
		
		mPictureButton = (ImageButton)v.findViewById(R.id.question_fragment_button_photo);
		if(mAnswer.hasPicture()){
			mPictureButton.setImageResource(R.drawable.picture_white);
			mPictureButton.setEnabled(true);
		}
		else{
			mPictureButton.setImageResource(R.drawable.picture_dark);
			mPictureButton.setEnabled(false);
		}
		
		mUsername = (TextView)v.findViewById(R.id.question_fragment_textview_username);
		mUsername.setText(mAnswer.getSignature());
	
		return v;
	}
}
