/**
 * An adaptor to return the desired view of a post, for use in a list view.
 * Called by profile fragment and main fragment to populate the list views.
 * @author Cmput301 Winter 2014 Group 8
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PostAdapter extends ArrayAdapter<Post> {
	
	
	/* uses the passed in controller to create an ArrayAdapter of Posts
	 * Once merged with PostController, edit out ArrayList<Post> for
	 * PostController, and add controller.getPostManager().getPosts());
	 */
	// TODO: read above
	public PostAdapter(Context context, ArrayList<Post> list) {
		super(context, 0, list);
	}
	
	// set up the required view, or uses a recycled view instead
	public View getView(int position, View view, ViewGroup parent) {

		TextView postDetails;
		TextView postTitle;
		TextView answerBoxText;
		Boolean isQuestion;
		Post currPost;
		
		currPost = getItem(position);
		
		// Boolean to see if the post is a question or not, to simplify logic
		isQuestion = (currPost instanceof Question);
		
		// If the view is null, inflate one
		if (view == null) {
			LayoutInflater inflator = LayoutInflater.from(this.getContext());
			view = inflator.inflate(R.layout.main_fragment_list_item, parent, false); //list_item_post, parent, false);
		}
		
		currPost = getItem(position);
		postTitle = (TextView) view.findViewById(R.id.main_question_text);
		postDetails = (TextView) view.findViewById(R.id.main_question_subtitle_text);
		answerBoxText = (TextView) view.findViewById(R.id.main_answer_count_text);
		postTitle.setEllipsize(TextUtils.TruncateAt.END); //Add ellipses if whole title doesn't fit
		view.setBackgroundResource(R.color.off_white);
		
		if (isQuestion) {
			Question tmp = (Question)currPost;
			
			postTitle.setText("Q: "+ tmp.getTitle());
			
			String string = String.format("%s\nAnswers", String.valueOf(tmp.countAnswers()));		
			Spannable formattedString = new SpannableString(string);
			formattedString.setSpan(new RelativeSizeSpan(0.4f), tmp.countAnswers()/10+1, formattedString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			answerBoxText.setText(formattedString);
			answerBoxText.setEnabled(true);
			answerBoxText.setVisibility(View.VISIBLE);
			
		}
		else {
			postTitle.setText("A: "+ currPost.getText());
			
			answerBoxText.setEnabled(false);
			answerBoxText.setVisibility(View.GONE);

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
