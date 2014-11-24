
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
/**
 * This fragment a question and display's it. This includes displaying the question along with all number of answers the question currently has. 
 * @author Cmput301 Winter 2014 Group 8
 */
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        // Call PostFragment onCreateView
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        // Logic unique to the Question Fragment

        final int answers = mQuestion.countAnswers();
        v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeLeft() {
                if(answers > 0){
                    Intent i = new Intent(getActivity(), AnswerActivity.class);
                    i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getAnswers().get(0).getID());
                    i.putExtra(PostFragment.EXTRA_CAME_FROM, PostFragment.FROM_QUESTION);
                    startActivityForResult(i,0);
                }
            }

            public void onSwipeRight() {
                getActivity().onBackPressed();
            }
        });

        mListView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeLeft() {
                if(answers > 0){
                    Intent i = new Intent(getActivity(), AnswerActivity.class);
                    i.putExtra(PostFragment.EXTRA_POST_ID, mQuestion.getAnswers().get(0).getID());
                    i.putExtra(PostFragment.EXTRA_CAME_FROM, PostFragment.FROM_QUESTION);
                    startActivityForResult(i,0);
                }
            }

            public void onSwipeRight() {
                getActivity().onBackPressed();
            }
        });

        mQuestionTitle = (TextView)v.findViewById(R.id.post_fragment_textview_title);
        mQuestionTitle.setText(mQuestion.getTitle());

        mAnswersButton = (Button)v.findViewById(R.id.post_fragment_button_answers);


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
                    startActivityForResult(i,0);
                }
            });
        }


        return v;		
    }
}
