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
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
            case R.id.menu_item_new_answer:
                Intent newAnswerIntent = new Intent(getActivity(), NewAnswerActivity.class);
                newAnswerIntent.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID()); 
                startActivity(newAnswerIntent);
                return true;
			case R.id.menu_item_back_to_question:
				Intent i = new Intent(getActivity(), QuestionActivity.class);
				i.putExtra(PostFragment.EXTRA_POST_ID, mAnswer.getParentID());
				startActivity(i);				 
			    return true;
			default:
			    // Call PostFragment onOptionItemSelected to get the rest of the menu
				return super.onOptionsItemSelected(item);
	    } 
	}
	
	@Override
	protected void configureAnswerButton() {
        mAnswersButton = (Button)postView.findViewById(R.id.post_fragment_button_answers);
        
        final int position = sPostController.getPostManager().getPositionOfAnswer(mParent, mAnswer);
        int remainingAnswers = mParent.countAnswers() - position - 1;
        if(remainingAnswers > 0){
            
            mAnswersButton.setEnabled(true);
            mAnswersButton.setVisibility(View.VISIBLE);
            mAnswersButton.setText(remainingAnswers + " More ");

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
    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
	    // Call PostFragment onCreateView
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		
		// Logic unique to the Answer Fragment
		LinearLayout linearLayout = (LinearLayout)v.findViewById(R.id.post_fragment_top_linearlayout);
		linearLayout.setBackgroundColor(mTextColor);
		
		mQuestionTitle = (TextView)v.findViewById(R.id.post_fragment_textview_title);
		mQuestionTitle.setVisibility(View.GONE);
	    configureAnswerButton();

		
		return v;		
	}
}
