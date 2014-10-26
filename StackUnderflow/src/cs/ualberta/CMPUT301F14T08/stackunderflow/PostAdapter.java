/*
 * An adaptor to return the desired view of a post, for use in a list view.
 * Called by profile fragment and mainfragment to populate the list views.
 * 
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class PostAdapter extends ArrayAdapter<Post> {
	
	
	/* uses the passed in controller to create an ArrayAdapter of Posts
	 * Once merged with PostController, edit out ArrayList<Post> for
	 * PostController, and add controller.getPostManager().getPosts());
	 */
	// TODO: read above
	public PostAdapter(Context context, ArrayList<Post> controller) {
		super(context, 0, controller);
	}
	
	// set up the required view, or uses a recycled view instead
	public View getView(int position, View view, ViewGroup parent) {

		TextView postDetails;
		TextView postTitle;
		TextView numberOfAns;
		TextView answerBoxText;
		ImageView answerBox;
		Boolean isQuestion;
		Post currPost;
		
		currPost = getItem(position);
		
		// Boolean to see if the post is a question or not, to simplify logic
		isQuestion = (currPost instanceof Question);
		
		// If the view is null, inflate one
		if (view == null) {
			LayoutInflater inflator = LayoutInflater.from(this.getContext());
			view = inflator.inflate(R.layout.fragment_main_list_item, parent, false); //list_item_post, parent, false);
		}
		
		currPost = getItem(position);
		postTitle = (TextView) view.findViewById(R.id.main_question_text);//postViewTitle);
		postDetails = (TextView) view.findViewById(R.id.main_question_subtitle_text);//postViewDetails);
		//numberOfAns = (TextView) view.findViewById(R.id.numberOfAnswersView);
		//answerBox = (ImageView) view.findViewById(R.id.answerViewBox);
		
		//answerBoxText is essentially a combination of numberOfAns and answerBox
		answerBoxText = (TextView) view.findViewById(R.id.main_answer_count_text);//answersTextView);
		
		if (isQuestion) {
			Question tmp = (Question)currPost;
			
			postTitle.setText("Q: "+ tmp.getTitle());
			
			String string = String.format("%s\nAnswers", String.valueOf(tmp.countAnswers()));		
			//String string = String.format(format, args)String.valueOf(tmp.countAnswers());	
			//numberOfAns.setText(string);			
			//String formattedAnswerBoxText = String.format("%s\nAnswers", string);
			Spannable formattedString = new SpannableString(string);
			formattedString.setSpan(new RelativeSizeSpan(0.4f), tmp.countAnswers()/10+1, formattedString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			answerBoxText.setText(formattedString);
			
			/*
			answerBox.setEnabled(true);
			answerBox.setVisibility(View.VISIBLE);
			answerBox.setImageResource(R.drawable.answer_box_large);
			*/
			answerBoxText.setEnabled(true);
			answerBoxText.setVisibility(View.VISIBLE);
			
			/*
			numberOfAns.setEnabled(true);
			numberOfAns.setVisibility(View.VISIBLE);
			*/
		}
		else {
			postTitle.setText("A: "+ currPost.getText());
			
			/*
			answerBox.setEnabled(false);
			answerBox.setVisibility(View.GONE);
			answerBox.setImageResource(R.drawable.answer_box_large);
			*/
			
			answerBoxText.setEnabled(false);
			answerBoxText.setVisibility(View.GONE);
			
			/*
			numberOfAns.setEnabled(false);
			numberOfAns.setVisibility(View.GONE);		
			*/
		}

		postDetails.setText(templateDetails(currPost));
		
		return view;
	}

	// returns a properly formatted string for displaying post details
	private String templateDetails(Post post) {
		String author = post.getSignature();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
		String date = format.format(post.getDate());
		Integer votes = post.getVotes();
				
		return "by "+ author + " | " + date + " | " + votes + " votes";
	}

	
}
