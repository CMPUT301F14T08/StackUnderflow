package cs.ualberta.CMPUT301F14T08.stackunderflow;

/*
 * An adaptor to return the desired view of a post, for use in a list view.
 * Called by profile fragment and mainfragment to populate the list views.
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/*
 * Author: Jonathan Emery
 * Modified by: 
 * 
 */

public class PostAdapter extends ArrayAdapter<Post> {
	// Boolean to see if the post is a question or not, to simplify logic
	private Boolean isQuestion = false;
	
	// uses the passed in controller to create an ArrayAdapter of Posts
	public PostAdapter(Context context, PostController controller) {
		super(context, 0, controller.getPostManager().getPosts());
	}
	
	// set up the required view, or uses a recycled view instead
	public View getView(int position, View view, ViewGroup parent) {
		final Post currPost = getItem(position);
		TextView postDetails;
		TextView postTitle;
		TextView numberOfAns;
		
		isQuestion = (currPost instanceof Question);
		
		if (view == null) {
			LayoutInflater inflator = LayoutInflater.from(this.getContext());
						
			if (isQuestion) {
				view = inflator.inflate(R.layout.list_item_post_question, parent, false);
			}
			else {
				view = inflator.inflate(R.layout.list_item_post_answer, parent, false);
			}
		}
		
		postTitle = (TextView) view.findViewById(R.id.postTitleView);
		if (isQuestion) {
			postDetails = (TextView) view.findViewById(R.id.postQuesDetailsView);
			numberOfAns = (TextView) view.findViewById(R.id.numberOfAnswers);
			
			postTitle.setText("Q: "+ currPost.getTitle());
			numberOfAns.setText(currPost.countAnswers());
		}
		else {
			postDetails = (TextView) view.findViewById(R.id.postAnsDetailsView);
			
			postTitle.setText("A: "+ currPost.getTitle());
		}

		postDetails.setText(templateDetails(currPost));
		
		return view;
	}

	// returns a properly formatted string for displaying post details
	private String templateDetails(Post post) {
		String author = post.getAuthor();
		Date date = post.getDate();
		Integer votes = post.getVotes();
				
		return "by "+ author + " | " + date + " | " + votes + " votes";
	}

	
}
