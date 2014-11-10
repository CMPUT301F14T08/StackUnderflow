/**
 * AnswerFragment called from AnswerActivity. This is called when the user attempts to view and answer.
 * @author Cmput301 Winter 2014 Group 8
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnswerFragment extends PostFragment {
	
	private Answer mAnswer;
	private Question mParent;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.answer_title);
		mAnswer = (Answer)mPost;
		mParent = (Question)sPostController.getPostManager().getPost(mAnswer.getParentID());
	    // We cache a post after viewing it
        sPostController.addToCache(mParent);
        mFragment = this;
	}
	
   @Override
    protected int getUpvoteFullID() {
        return R.drawable.upvote_full_blue;
    }
    
    @Override
    protected int getUpvoteEmptyID() {
        return R.drawable.upvote_empty_blue;
    }
    
    @Override
    protected int getFavoriteFullID() {
        return R.drawable.star_full_blue;
    }
    
    @Override
    protected int getFavoriteEmptyID() {
        return R.drawable.star_empty_blue;
    }
    
    @Override
    protected int getImageIconID() {
        return R.drawable.picture_blue;
    }

    @Override
    protected int getTextColor() {
        return R.color.blue;
    }

    /**
     * This is called when the user expands the option menu and choose an option
     * In the case of the option being "Back to Question" The user will be returned to the question page   
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
			case R.id.menu_item_back_to_question:
				if(mCameFrom == FROM_QUESTION){
					getActivity().onBackPressed();
				}
				else{
					getActivity().finish();
					Intent i = new Intent(getActivity(), QuestionActivity.class);
					i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID());
					i.putExtra(PostFragment.EXTRA_CAME_FROM, PostFragment.FROM_OTHER);
					startActivity(i);	
				}
			    return true;
			default:
			    // Call PostFragment onOptionItemSelected to get the rest of the menu
				return super.onOptionsItemSelected(item);
	    } 
	}
	
	/**
	 * When the view is created this works with the listeners work with buttons and edit text fields.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
	    // Call PostFragment onCreateView
		super.setPost(mAnswer);
		View v = super.onCreateView(inflater, parent, savedInstanceState);

		// Logic unique to the Answer Fragment
		LinearLayout linearLayout = (LinearLayout)v.findViewById(R.id.post_fragment_top_linearlayout);
		linearLayout.setBackgroundColor(mTextColor);
		
		mQuestionTitle = (TextView)v.findViewById(R.id.post_fragment_textview_title);
		mQuestionTitle.setVisibility(View.GONE);
		
		mAnswersButton = (Button)v.findViewById(R.id.post_fragment_button_answers);
		
		final int position = sPostController.getPostManager().getPositionOfAnswer(mParent, mAnswer);
		final int remainingAnswers = mParent.countAnswers() - position - 1;
		
		v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
			public void onSwipeLeft() {
				if(remainingAnswers > 0){
					mAnswer = mParent.getAnswers().get(position+1);
					getFragmentManager().beginTransaction().detach(mFragment).attach(mFragment).commit();
				}
		    }
			
			public void onSwipeRight() {

				if(position==0){
					if(mCameFrom == FROM_QUESTION){
						getActivity().setResult(0);
						getActivity().onBackPressed();
					}
					else{
						getActivity().finish();
						Intent i = new Intent(getActivity(), QuestionActivity.class);
						i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID());
						i.putExtra(PostFragment.EXTRA_CAME_FROM, PostFragment.FROM_OTHER);
						startActivity(i);	
					}   
				}
				else{
					mAnswer = mParent.getAnswers().get(position-1);
					getFragmentManager().beginTransaction().detach(mFragment).attach(mFragment).commit();
				}
		    }
		});
		
		mListView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
			public void onSwipeLeft() {
				if(remainingAnswers > 0){
					mAnswer = mParent.getAnswers().get(position+1);
					getFragmentManager().beginTransaction().detach(mFragment).attach(mFragment).commit();
				}
		    }
			
			public void onSwipeRight() {

				if(position==0){
					if(mCameFrom == FROM_QUESTION){
						getActivity().setResult(0);
						getActivity().onBackPressed();
					}
					else{
						getActivity().finish();
						Intent i = new Intent(getActivity(), QuestionActivity.class);
						i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID());
						i.putExtra(PostFragment.EXTRA_CAME_FROM, PostFragment.FROM_OTHER);
						startActivity(i);	
					}   
				}
				else{
					mAnswer = mParent.getAnswers().get(position-1);
					getFragmentManager().beginTransaction().detach(mFragment).attach(mFragment).commit();
				}
		    }
		});
		
		if(remainingAnswers > 0){
			
			mAnswersButton.setEnabled(true);
			mAnswersButton.setVisibility(View.VISIBLE);
			mAnswersButton.setText(remainingAnswers + " More ");

			mAnswersButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					mAnswer = mParent.getAnswers().get(position+1);
					getFragmentManager().beginTransaction().detach(mFragment).attach(mFragment).commit();
				}
			});
		}
		else{
			mAnswersButton.setEnabled(false);
			mAnswersButton.setVisibility(View.GONE);
		}
		
		return v;		
	}
}
