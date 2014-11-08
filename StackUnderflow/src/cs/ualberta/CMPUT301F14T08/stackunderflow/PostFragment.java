/*
 * TODO: Write a nice discription to about this class
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PostFragment extends Fragment {
	public static final String EXTRA_POST_ID = "cs.ualberta.CMPUT301F14T08.stackunderflow.post_id";
	protected static final String DIALOG_USERNAME = "username";
    protected static final int REQUEST_USERNAME = 0;
    
	protected PostController sPostController;
	protected ReplyAdapter adapter;
	protected UUID mPostId;
	protected Post mPost;
	
	protected LinearLayout mTopLinearLayout;
	protected TextView mQuestionTitle;
	protected TextView mPostBody;
	protected ImageButton mUpvoteButton;
	protected TextView mUpvoteCountTextView;
	protected ImageButton mFavoriteButton;
	protected TextView mFavoriteTextView;
	protected ImageButton mPictureButton;
	protected TextView mUsername;
	protected ListView mListView;
	protected ImageButton mAnswersButton;
	protected TextView mAnswersTextView;
	protected ImageButton mBackButton;

	
	protected int mBlackColor;
	protected int mWhiteColor;
	protected int mBlueColor;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	    
		mPostId = (UUID)getArguments().getSerializable(EXTRA_POST_ID);
		Log.d("Debug", "Opening Post view for: " + mPostId);
		
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
		sPostController = PostController.getInstanceForID(getActivity(), mPostId);
		
		mPost = sPostController.getPostManager().getPost(mPostId);
		
	    Log.d("Debug", "Post UUID: " + mPostId);
	    Log.d("Debug", "Posts: " + sPostController.getPostManager().getQuestions().toString());

		mBlackColor = getResources().getColor(R.color.black);
		mWhiteColor = getResources().getColor(R.color.white);
		mBlueColor = getResources().getColor(R.color.blue);
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
		super.onCreateOptionsMenu(menu, menuInflater);
		menuInflater.inflate(R.menu.post_menu, menu);
	}
	
	protected void setPost(Post post){
		mPost = post;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem){
	    switch (menuItem.getItemId()) {
        case R.id.menu_item_new_reply:
//        	if(sPostController.getPostManager().getUserProfileManager().getUserProfile().getUsername.equals(null)){
//        		FragmentManager fm = getActivity().getFragmentManager();
//        		UsernameFragment dialog = new UsernameFragment();
//        		dialog.setTargetFragment(PostFragment.this, REQUEST_USERNAME);
//        		dialog.show(fm, DIALOG_USERNAME);
//        	}
//        	else{
//        		//new reply
//        		String toastString = "Someone needs to implement code to show add replies";
//                Toast toast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_LONG);
//                toast.show(); 
//        	}
        	return true;
        default:
            return super.onOptionsItemSelected(menuItem);
    	}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.post_fragment, parent, false);
		
		mQuestionTitle = (TextView)v.findViewById(R.id.post_fragment_textview_title);
		mQuestionTitle.setTextColor(mWhiteColor);
		
		mTopLinearLayout = (LinearLayout)v.findViewById(R.id.post_fragment_top_linearlayout);
		
		mPostBody = (TextView)v.findViewById(R.id.post_fragment_textview_body);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
		String date = sdf.format(mPost.getDate());
		mPostBody.setText(mPost.getText() + " (" + date + ")");
		mPostBody.setTextColor(mWhiteColor);
		
		mUpvoteButton = (ImageButton)v.findViewById(R.id.post_fragment_button_upvote);
		mUpvoteButton.setImageResource(mPost.getUserAttributes().getIsUpvoted() ? R.drawable.upvote_full : R.drawable.upvote_empty);
		mUpvoteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPost.getUserAttributes().toggleIsUpvoted();
				if(mPost.getUserAttributes().getIsUpvoted()){
					mPost.incrementVotes();
					mUpvoteButton.setImageResource(R.drawable.upvote_full);
				}
				else{
					mPost.decrementVotes();
					mUpvoteButton.setImageResource(R.drawable.upvote_empty);
				}		
				mUpvoteCountTextView.setText(""+mPost.getVotes());
			}
		});
		
		
		mUpvoteCountTextView = (TextView)v.findViewById(R.id.post_fragment_textview_upvotes);
		mUpvoteCountTextView.setText(""+ mPost.getVotes());
		mUpvoteCountTextView.setTextColor(mWhiteColor);
		
		mFavoriteButton = (ImageButton)v.findViewById(R.id.post_fragment_button_favorite);
		mFavoriteButton.setImageResource(mPost.getUserAttributes().getIsFavorited() ? R.drawable.star_full : R.drawable.star_empty);
		mFavoriteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPost.getUserAttributes().toggleIsFavorited();
				mFavoriteButton.setImageResource(mPost.getUserAttributes().getIsFavorited() ? R.drawable.star_full : R.drawable.star_empty);
			}
		});
		
		mFavoriteTextView = (TextView)v.findViewById(R.id.post_fragment_textview_favorite);
		mFavoriteTextView.setTextColor(mWhiteColor);
		
		mPictureButton = (ImageButton)v.findViewById(R.id.post_fragment_button_photo);
		if(mPost.hasPicture()){
			mPictureButton.setImageResource(R.drawable.picture_white);
			mPictureButton.setEnabled(true);
			mPictureButton.setVisibility(View.VISIBLE);
			mPictureButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Show the picture
					String toastString = "Someone needs to implement code to show the picture";
	                Toast toast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_LONG);
	                toast.show();    
				}
			});
		}
		else{
			mPictureButton.setImageResource(R.drawable.picture_dark);
			mPictureButton.setEnabled(false);
			mPictureButton.setVisibility(View.GONE);
		}
		
		
		mUsername = (TextView)v.findViewById(R.id.post_fragment_textview_username);
		mUsername.setText(mPost.getSignature());
		mUsername.setTextColor(mWhiteColor);
		
		mListView = (ListView)v.findViewById(R.id.post_fragment_listview_replies);
		adapter = new ReplyAdapter(getActivity(), mPost.getReplies());
		mListView.setAdapter(adapter);
		
		mAnswersButton = (ImageButton)v.findViewById(R.id.post_fragment_button_answers);
		
		mAnswersTextView = (TextView)v.findViewById(R.id.post_fragment_textview_answers);
		mAnswersTextView.setTextColor(mBlackColor);
		
		return v;		
	}
}