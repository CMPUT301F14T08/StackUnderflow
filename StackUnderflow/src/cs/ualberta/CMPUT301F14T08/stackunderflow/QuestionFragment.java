package cs.ualberta.CMPUT301F14T08.stackunderflow;


import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class QuestionFragment extends PostFragment {
	
	
	private Question mQuestion;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.question_title);
		// TODO: This should fail gracefully if getPost returns null
		mQuestion = (Question)sPostController.getPostManager().getPost(mPostId);
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	//Use the same menu for both question and answer but just hide the "back to question" option for the question view
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
	    super.onPrepareOptionsMenu(menu);
	    menu.findItem(R.id.menu_item_back_to_question).setVisible(false);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
			case R.id.menu_item_new_answer:
				
				Intent i = new Intent(getActivity(), NewAnswerActivity.class);
				i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getID()); 
				startActivity(i);
				
//				if(sPostController.getPostManager().getUserProfileManager().getUserProfile().getUsername.equals(null)){
//	        		FragmentManager fm = getActivity().getFragmentManager();
//					UsernameFragment dialog = new UsernameFragment();
//					dialog.setTargetFragment(QuestionFragment.this, REQUEST_USERNAME);
//					dialog.show(fm, DIALOG_USERNAME);
//				}
//				else{
//					Intent i = new Intent(getActivity(), NewAnswerActivity.class);
//					i.putExtra(NewAnswerFragment.EXTRA_PARENT_QUESTION_ID, mQuestion.getID());
//					startActivity(i);
//				}
			    return true;
			default:
				return super.onOptionsItemSelected(item);
	    } }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		
		v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
			public void onSwipeLeft() {
		        if(mQuestion.getAnswers().size() > 0){
				Intent i = new Intent(getActivity(), AnswerActivity.class);
				i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getAnswers().get(0).getID());
				startActivity(i);
		        }
		    }
		});
		
		mListView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
			public void onSwipeLeft() {
		        if(mQuestion.getAnswers().size() > 0){
				Intent i = new Intent(getActivity(), AnswerActivity.class);
				i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getAnswers().get(0).getID());
				startActivity(i);
		        }
		    }
			
    });
		
		mQuestionTitle = (TextView)v.findViewById(R.id.post_fragment_textview_title);
		mQuestionTitle.setText(mQuestion.getTitle());
		mQuestionTitle.setTextColor(mWhiteColor);

		mPostBody = (TextView)v.findViewById(R.id.post_fragment_textview_body);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
		String date = sdf.format(mQuestion.getDate());
		mPostBody.setText(mQuestion.getText() + " (" + date + ")");
		mPostBody.setTextColor(mWhiteColor);
		
		mUpvoteButton = (ImageButton)v.findViewById(R.id.post_fragment_button_upvote);
		mUpvoteButton.setImageResource(mQuestion.getUserAttributes().getIsUpvoted() ? R.drawable.upvote_full : R.drawable.upvote_empty);
		mUpvoteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mQuestion.getUserAttributes().toggleIsUpvoted();
				if(mQuestion.getUserAttributes().getIsUpvoted()){
					mQuestion.incrementVotes();
					mUpvoteButton.setImageResource(R.drawable.upvote_full);
				}
				else{
					mQuestion.decrementVotes();
					mUpvoteButton.setImageResource(R.drawable.upvote_empty);
				}		
				mUpvoteCountTextView.setText(""+mQuestion.getVotes());
			}
		});
		
		mUpvoteCountTextView = (TextView)v.findViewById(R.id.post_fragment_textview_upvotes);
		mUpvoteCountTextView.setText(""+ mQuestion.getVotes());
		mUpvoteCountTextView.setTextColor(mWhiteColor);
		
		mFavoriteButton = (ImageButton)v.findViewById(R.id.post_fragment_button_favorite);
		mFavoriteButton.setImageResource(mQuestion.getUserAttributes().getIsFavorited() ? R.drawable.star_full : R.drawable.star_empty);
		mFavoriteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mQuestion.getUserAttributes().toggleIsFavorited();
				mFavoriteButton.setImageResource(mQuestion.getUserAttributes().getIsFavorited() ? R.drawable.star_full : R.drawable.star_empty);
			}

		});
		
		mQuestionTitle.setText(mQuestion.getTitle());
		
		mAnswersButton = (ImageButton)v.findViewById(R.id.post_fragment_button_answers);
		
		if(mQuestion.getAnswers().size() > 0){	
			mAnswersButton.setEnabled(true);
			mAnswersButton.setVisibility(View.VISIBLE);
			mAnswersButton.setImageResource(R.drawable.box_arrow_right_large);
			mAnswersButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(getActivity(), AnswerActivity.class);
					i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getAnswers().get(0).getID());
					startActivity(i);
				}
			});
		}
		else{
			mAnswersButton.setEnabled(false);
			mAnswersButton.setVisibility(View.GONE);
		}
		
		switch(mQuestion.countAnswers()){
		case 1:
			mAnswersTextView.setText("1 Answer");
			break;
		default:
			mAnswersTextView.setText(mQuestion.countAnswers() + " Answers");
			break;
		}
		
		mBackButton = (ImageButton)v.findViewById(R.id.post_fragment_button_back);
		mBackButton.setEnabled(false);
		mBackButton.setVisibility(View.GONE);
		
		return v;		
	}
}
