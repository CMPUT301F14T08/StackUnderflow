package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AnswerFragment extends PostFragment {
	
	private Answer mAnswer;
	final Fragment frag = this;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.answer_title);
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
				getActivity().onBackPressed();
			    return true;

			default:
				return super.onOptionsItemSelected(item);
	    } }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		super.setPost(mAnswer);
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		
		final int remainingAnswers = mAnswer.getParentQuestion().countAnswers()-mAnswer.getPosition()-1;
		
		v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
			public void onSwipeLeft() {
				if(remainingAnswers > 0){
					mAnswer = mAnswer.getParentQuestion().getAnswers().get(mAnswer.getPosition()+1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();
				}
		    }
			
			public void onSwipeRight() {
				if(mAnswer.getPosition()==0){
					// TODO: Need to change this
					getActivity().onBackPressed();   
					
				}
				else{
					mAnswer = mAnswer.getParentQuestion().getAnswers().get(mAnswer.getPosition()-1);
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
					mAnswer = mAnswer.getParentQuestion().getAnswers().get(mAnswer.getPosition()+1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();
				}
			}
				
			public void onSwipeRight() {
				if(mAnswer.getPosition()==0){
					getActivity().onBackPressed();
				}
				else{
					mAnswer = mAnswer.getParentQuestion().getAnswers().get(mAnswer.getPosition()-1);
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
					mAnswer = mAnswer.getParentQuestion().getAnswers().get(mAnswer.getPosition()+1);
					getFragmentManager().beginTransaction().detach(frag).attach(frag).commit();

//					Intent i = new Intent(getActivity(), AnswerActivity.class);
//					i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentQuestion().getAnswers().get(mAnswer.getPosition()+1).getID());
//					startActivity(i);
//CONVERSION TO AVOID STACKING
//					UUID nextID = mAnswer.getParentQuestion().getAnswers()
//							.get(mAnswer.getPosition()+1).getID();
//					
//					Fragment fragOld = getFragmentManager().findFragmentByTag(EXTRA_POST_ID);
//					Fragment fragNew = new AnswerFragment();
//					
//					Bundle args = new Bundle();
//					args.putSerializable(EXTRA_POST_ID, nextID);
//					fragNew.setArguments(args);
//					
//					FragmentTransaction ft = getFragmentManager().beginTransaction();
//					ft.remove(fragOld);
//					ft.add(R.id.base_container, fragNew);
//					ft.commit();

				}
			});
		}
		else{
			mAnswersButton.setEnabled(false);
			mAnswersButton.setVisibility(View.GONE);
		}
		
		mBackButton = (ImageButton)v.findViewById(R.id.post_fragment_button_back);
		mBackButton.setImageResource(R.drawable.box_arrow_left_large);
		boolean isFirstAnswer = mAnswer.getPosition() == 0;
		
		//button navigates to previous answer, needs to be refactored once stack avoidance
		//is figured out
		if(!isFirstAnswer){
			mBackButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(getActivity(), AnswerActivity.class);
					i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentQuestion().getAnswers().get(mAnswer.getPosition()-1).getID());
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
