/**
 * This fragment a question and display's it. This includes displaying the question along with all number of answers the question currently has. 
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuestionFragment extends PostFragment {
	
	private Question mQuestion;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.question_title);
		mQuestion = (Question)mPost;
	    // We cache a post after viewing it
        sPostController.addToCache(mQuestion);
	}
	
	@Override
	protected int getUpvoteFullID() {
	    return R.drawable.upvote_full_black;
	}
	
	@Override
    protected int getUpvoteEmptyID() {
	    return R.drawable.upvote_empty_black;
	}
	
	@Override
    protected int getFavoriteFullID() {
	    return R.drawable.star_full_black;
	}
	
	@Override
    protected int getFavoriteEmptyID() {
	    return R.drawable.star_empty_black;
	}
   
	@Override
    protected int getImageIconID() {
        return R.drawable.picture_dark;
    }
   
	@Override
    protected int getTextColor() {
	    return R.color.black;
	}
	
	
	@Override
	protected void configureAnswerButton() {
	    mAnswersButton = (Button)postView.findViewById(R.id.post_fragment_button_answers);
	    
	    // add the answers button if answers exist
        int answers = mQuestion.getAnswers().size();
        if(answers > 0){
                
            mAnswersButton.setEnabled(true);
            mAnswersButton.setVisibility(View.VISIBLE);
            
            if (answers == 1)
                mAnswersButton.setText("1 Answer ");
            else  
                mAnswersButton.setText(answers + " Answers ");
        
            mAnswersButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), AnswerActivity.class);
                    i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getAnswers().get(0).getID());
                    startActivity(i);
                }
            });
        }
    }
	
   @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.menu_item_new_answer:
                Intent i = new Intent(getActivity(), NewAnswerActivity.class);
                i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getID()); 
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
	    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
	    // Call PostFragment onCreateView
	    View v = super.onCreateView(inflater, parent, savedInstanceState);
		
	    // Logic unique to the Question Fragment
	    
		mQuestionTitle = (TextView)v.findViewById(R.id.post_fragment_textview_title);
		mQuestionTitle.setText(mQuestion.getTitle());
		configureAnswerButton();


		return v;		
	}
}
