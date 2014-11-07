package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Fragment;
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
	final Fragment frag = this;

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
				getActivity().onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
	    } }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.post_fragment, parent, false);
		final int position = sPostController.getPostManager().getPositionOfAnswer(mParent, mAnswer);
		final int remainingAnswers = mParent.countAnswers() - position - 1;
		
		v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
			public void onSwipeLeft() {
				if(remainingAnswers > 0){
					mAnswer = mParent.getAnswers().get(position+1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();
				}
		    }
			
			public void onSwipeRight() {
				if(position==0){
					getActivity().onBackPressed();   
				}
				else{
					mAnswer = mParent.getAnswers().get(position-1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();
				}
		    }
		});
		
		
		mTopLinearLayout.setBackgroundColor(mBlueColor);
		
		mQuestionTitle.setVisibility(View.GONE);
		

		mUpvoteButton.setBackgroundColor(mBlueColor);
		mFavoriteButton.setBackgroundColor(mBlueColor);
		mPictureButton.setBackgroundColor(mBlueColor);
			
		mListView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
			public void onSwipeLeft() {
				if(remainingAnswers > 0){
					mAnswer = mParent.getAnswers().get(position+1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();
				}
			}
				
			public void onSwipeRight() {
				if(position==0){
					getActivity().onBackPressed();
				}
				else{
					mAnswer = mParent.getAnswers().get(position-1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();
				}
			}	
	    });
		
		if(remainingAnswers > 0){	
			mAnswersButton.setEnabled(true);
			mAnswersButton.setVisibility(View.VISIBLE);
			mAnswersButton.setImageResource(R.drawable.box_arrow_right_large);
			mAnswersButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					mAnswer = mParent.getAnswers().get(position+1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();
				}
			});
		}
		else{
			mAnswersButton.setEnabled(false);
			mAnswersButton.setVisibility(View.GONE);
		}

		mBackButton = (ImageButton)v.findViewById(R.id.post_fragment_button_back);
		mBackButton.setImageResource(R.drawable.box_arrow_left_large);
		boolean isFirstAnswer = position == 0;
		

		if(!isFirstAnswer){
			mBackButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(getActivity(), AnswerActivity.class);
					i.putExtra(PostFragment.EXTRA_POST_ID, mParent.getAnswers().get(position-1).getID());
					startActivity(i);
				}
			});
		}
		//button navigates back to question
		else{
			mBackButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					getActivity().onBackPressed();
				}
			});
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
			mAnswersTextView.setText(remainingAnswers + "More Answers");
			break;
		}
		
		return v;		
	}
}	
		