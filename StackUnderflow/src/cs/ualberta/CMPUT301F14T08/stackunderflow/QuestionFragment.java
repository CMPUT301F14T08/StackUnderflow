package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class QuestionFragment extends PostFragment {
	
	private Question mQuestion;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.question_title);
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
