/*
 * AnswerFragment called from AnswerActivity. This is called when the user attempts to view and answer.
 * 
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerFragment extends PostFragment {
	
	private Answer mAnswer;
	private Question mParent;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.answer_title);
		mAnswer = (Answer)sPostController.getPostManager().getPost(mPostId);
		mParent = (Question)sPostController.getPostManager().getPost(mAnswer.getParentID());
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
			case R.id.menu_item_new_answer:
				
				Intent intent = new Intent(getActivity(), NewAnswerActivity.class);
				intent.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID()); 
				startActivity(intent);
				
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
				Intent i = new Intent(getActivity(), QuestionActivity.class);
				i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID());
				startActivity(i);				 
			    return true;
			default:
				return super.onOptionsItemSelected(item);
	    } }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.post_fragment, parent, false);
		
		LinearLayout linearLayout = (LinearLayout)v.findViewById(R.id.post_fragment_top_linearlayout);
		linearLayout.setBackgroundColor(mBlueColor);
		
		mQuestionTitle = (TextView)v.findViewById(R.id.post_fragment_textview_title);
		mQuestionTitle.setVisibility(View.GONE);
		
		mPostBody = (TextView)v.findViewById(R.id.post_fragment_textview_body);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
		String date = sdf.format(mAnswer.getDate());
		mPostBody.setText(mAnswer.getText() + " (" + date + ")");
		mPostBody.setTextColor(mWhiteColor);
		
		mUpvoteButton = (ImageButton)v.findViewById(R.id.post_fragment_button_upvote);
		mUpvoteButton.setImageResource(mAnswer.getUserAttributes().getIsUpvoted() ? R.drawable.upvote_full : R.drawable.upvote_empty);
		mUpvoteButton.setBackgroundColor(mBlueColor);
		mUpvoteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mAnswer.getUserAttributes().toggleIsUpvoted();
				if(mAnswer.getUserAttributes().getIsUpvoted()){
					mAnswer.incrementVotes();
					mUpvoteButton.setImageResource(R.drawable.upvote_full);
				}
				else{
					mAnswer.decrementVotes();
					mUpvoteButton.setImageResource(R.drawable.upvote_empty);
				}		
				mUpvoteCountTextView.setText(""+mAnswer.getVotes());
			}
		});
		
		
		mUpvoteCountTextView = (TextView)v.findViewById(R.id.post_fragment_textview_upvotes);
		mUpvoteCountTextView.setText(""+ mAnswer.getVotes());
		mUpvoteCountTextView.setTextColor(mWhiteColor);
		
		mFavoriteButton = (ImageButton)v.findViewById(R.id.post_fragment_button_favorite);
		mFavoriteButton.setImageResource(mAnswer.getUserAttributes().getIsFavorited() ? R.drawable.star_full : R.drawable.star_empty);
		mFavoriteButton.setBackgroundColor(mBlueColor);
		mFavoriteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mAnswer.getUserAttributes().toggleIsFavorited();
				mFavoriteButton.setImageResource(mAnswer.getUserAttributes().getIsFavorited() ? R.drawable.star_full : R.drawable.star_empty);
			}
		});
		
		mFavoriteTextView = (TextView)v.findViewById(R.id.post_fragment_textview_favorite);
		mFavoriteTextView.setTextColor(mWhiteColor);
		
		mPictureButton = (ImageButton)v.findViewById(R.id.post_fragment_button_photo);
		if(mAnswer.hasPicture()){
			mPictureButton.setImageResource(R.drawable.picture_white);
			mPictureButton.setEnabled(true);
			mPictureButton.setVisibility(View.VISIBLE);
			mPictureButton.setBackgroundColor(mBlueColor);
			mPictureButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Show the picture
					String toastString = "Someone needs to implement code to show the picture";
	                Toast toast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_LONG);
	                toast.show();    
				}
			});
		}
		else{
			mPictureButton.setImageResource(R.drawable.picture_dark);
			mPictureButton.setEnabled(false);
			mPictureButton.setVisibility(View.GONE);
		}
		
		
		mUsername = (TextView)v.findViewById(R.id.post_fragment_textview_username);
		mUsername.setText(mAnswer.getSignature());
		mUsername.setTextColor(mWhiteColor);
		
		
		mAnswersButton = (ImageButton)v.findViewById(R.id.post_fragment_button_answers);
		
		final int position = sPostController.getPostManager().getPositionOfAnswer(mParent, mAnswer);
		int remainingAnswers = mParent.countAnswers() - position - 1;
		if(remainingAnswers > 0){
			
			mAnswersButton.setEnabled(true);
			mAnswersButton.setVisibility(View.VISIBLE);
			mAnswersButton.setImageResource(R.drawable.box_arrow_right_large);
			mAnswersButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(getActivity(), AnswerActivity.class);
					i.putExtra(PostFragment.EXTRA_POST_ID, mParent.getAnswers().get(position+1).getID());
					startActivity(i);
				}
			});
		}
		else{
			mAnswersButton.setEnabled(false);
			mAnswersButton.setVisibility(View.GONE);
		}
		
		mAnswersTextView = (TextView)v.findViewById(R.id.post_fragment_textview_answers);
		mAnswersTextView.setTextColor(mBlackColor);
		switch(remainingAnswers){
		case 0:
			mAnswersTextView.setText("No More Answers");
			break;
		case 1:
			mAnswersTextView.setText("1 More Answer");
			break;
		default:
			mAnswersTextView.setText(remainingAnswers + " More Answers");
			break;
		}
		
		return v;		
	}
}
