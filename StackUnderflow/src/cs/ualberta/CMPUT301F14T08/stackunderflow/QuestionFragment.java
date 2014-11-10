/**
 * This fragment a question and display's it. This includes displaying the question along with all number of answers the question currently has. 
 * @author Cmput301 Winter 2014 Group 8
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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
        mFragment = this;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)  
    {  
              super.onActivityResult(requestCode, resultCode, data);  
                  
               // check if the request code is same as what is passed  here it is 2  
                if(requestCode==0)  
                      {  
                	Log.d("REQUEST CODE", ""+mQuestion.countAnswers());
                //	getFragmentManager().beginTransaction().detach(mFragment).attach(mFragment).commit();
              
                      }  
  
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
	
	//Use the same menu for both question and answer but just hide the "back to question" option for the question view
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
	    super.onPrepareOptionsMenu(menu);
	    menu.findItem(R.id.menu_item_back_to_question).setVisible(false);
	}
	
	
//	@Override
//	protected void configureAnswerButton() {
//	    mAnswersButton = (Button)postView.findViewById(R.id.post_fragment_button_answers);
//	    
//	    // add the answers button if answers exist
//        int answers = mQuestion.getAnswers().size();
//        if(answers > 0){
//                
//            mAnswersButton.setEnabled(true);
//            mAnswersButton.setVisibility(View.VISIBLE);
//            
//            if (answers == 1)
//                mAnswersButton.setText("1 Answer ");
//            else  
//                mAnswersButton.setText(answers + " Answers ");
//        
//            mAnswersButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    Intent i = new Intent(getActivity(), AnswerActivity.class);
//                    i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getAnswers().get(0).getID());
//                    startActivity(i);
//                }
//            });
//        }
//    }
	
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

		mAnswersButton = (Button)v.findViewById(R.id.post_fragment_button_answers);
		
		int answers = mQuestion.countAnswers();
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
					i.putExtra(PostFragment.EXTRA_CAME_FROM, PostFragment.FROM_QUESTION);
					startActivity(i);
				}
			});
		}


		return v;		
	}
}
