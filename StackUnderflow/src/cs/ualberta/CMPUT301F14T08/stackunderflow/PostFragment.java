/*
 * TODO: Write a nice discription to about this class
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public abstract class PostFragment extends Fragment {
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
	protected Button mUpvoteButton;
	protected Button mFavoriteButton;
	protected Button mPictureButton;
	protected TextView mUsername;
	protected ListView mListView;
	protected Button mAnswersButton;
	protected Button mBackButton;
	
	protected Drawable mUpvoteFull;
	protected Drawable mUpvoteEmpty;
	protected Drawable mFavoriteFull;
	protected Drawable mFavoriteEmpty;
	protected Drawable mImageIcon;
	
	protected int mTextColor;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mPostId = (UUID)getArguments().getSerializable(EXTRA_POST_ID);
		
		// Don't let HTTP run in the background, we're just waiting for updates on
		// one Post, not a list so we can wait until we receive them before rendering the view
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
		sPostController = PostController.getInstanceForID(getActivity(), mPostId);
		mPost = sPostController.getPostManager().getPost(mPostId);
		
		// Set variables according to the resource IDs provided in subclasses
		mTextColor = getResources().getColor(getTextColor());
		
		Context context = getActivity().getApplicationContext();
		
		mUpvoteFull = context.getResources().getDrawable(getUpvoteFullID());
		mUpvoteFull.setBounds(0, 0, mUpvoteFull.getMinimumHeight(), mUpvoteFull.getMinimumWidth());
		
		mUpvoteEmpty = context.getResources().getDrawable(getUpvoteEmptyID());
		mUpvoteEmpty.setBounds(0, 0, mUpvoteEmpty.getMinimumHeight(), mUpvoteEmpty.getMinimumWidth());

		mFavoriteFull = context.getResources().getDrawable(getFavoriteFullID());
		mFavoriteFull.setBounds(0, 0, mFavoriteFull.getMinimumHeight(), mFavoriteFull.getMinimumWidth());
		
		mFavoriteEmpty = context.getResources().getDrawable(getFavoriteEmptyID());
		mFavoriteEmpty.setBounds(0, 0, mFavoriteEmpty.getMinimumHeight(), mFavoriteEmpty.getMinimumWidth());
		
        mImageIcon = context.getResources().getDrawable(getImageIconID());
        mImageIcon.setBounds(0, 0, mImageIcon.getMinimumHeight(), mImageIcon.getMinimumWidth());
	}
	
	// Subclasses (Question/Answer) will implement these to tell
	// the super class what resources to use when drawing the view
	abstract protected int getUpvoteFullID();
	abstract protected int getUpvoteEmptyID();
	abstract protected int getFavoriteFullID();
	abstract protected int getFavoriteEmptyID();
	abstract protected int getImageIconID();
	abstract protected int getTextColor();
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
		super.onCreateOptionsMenu(menu, menuInflater);
		menuInflater.inflate(R.menu.post_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem){
	    // Both Question/Answer have this Menu Item
	    switch (menuItem.getItemId()) {
            case R.id.menu_item_new_answer:
                Intent i = new Intent(getActivity(), NewAnswerActivity.class);
                i.putExtra(PostFragment.EXTRA_POST_ID, mPost.getID()); 
                startActivity(i);
            
            default:
                return super.onOptionsItemSelected(menuItem);
    	}
	}
	
	// Set all the stuff relevant to both Question and Answer Fragments here
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
	    View v = inflater.inflate(R.layout.post_fragment, parent, false);
	    
	    // Post Body
	    mPostBody = (TextView)v.findViewById(R.id.post_fragment_textview_body);
        mPostBody.setText(mPost.getText());
        
        // Author + Date
        mUsername = (TextView)v.findViewById(R.id.post_fragment_textview_username);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
        String date = "(" + sdf.format(mPost.getDate()) + ")";
        mUsername.setText("- " + mPost.getSignature() + " " + date);
        
        // Upvote Button
        mUpvoteButton = (Button)v.findViewById(R.id.post_fragment_button_upvote);
        setVoteText(mPost, mUpvoteButton);
        mUpvoteButton.setTextColor(mTextColor);
        setIconUpvote(mPost, mUpvoteButton);
        mUpvoteButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                sPostController.getPostManager().toggleUpvote(mPost);
                setIconUpvote(mPost, mUpvoteButton);
                setVoteText(mPost, mUpvoteButton);
            }
        });
        
        // Favorite Button
        mFavoriteButton = (Button)v.findViewById(R.id.post_fragment_button_favorite);
        mFavoriteButton.setTextColor(mTextColor);
        setIconFavorited(mPost, mFavoriteButton);
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                sPostController.getPostManager().toggleFavorite(mPost);
                setIconFavorited(mPost, mFavoriteButton);
            }
        });
        
        
        // Picture Button
        mPictureButton = (Button)v.findViewById(R.id.post_fragment_button_photo);
        mPictureButton.setCompoundDrawables(mImageIcon, null, null, null);
        mPictureButton.setTextColor(mTextColor);

        if(mPost.hasPicture()){
            mPictureButton.setEnabled(true);
            mPictureButton.setVisibility(View.VISIBLE);
            mPictureButton.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO: Implement Code to show Pictures
                    String toastString = "Someone needs to implement code to show the picture";
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), toastString, Toast.LENGTH_LONG);
                    toast.show();    
                }
            });
        }
        else{
            mPictureButton.setEnabled(false);
            mPictureButton.setVisibility(View.GONE);
        }
        
        // Set Backbutton/Forward Button invisible for now, Answer/Question can
        // Choose to show them based on their individual requirements
        mBackButton = (Button)v.findViewById(R.id.post_fragment_button_back);
        mBackButton.setVisibility(View.GONE);
        
        mAnswersButton = (Button)v.findViewById(R.id.post_fragment_button_answers);
        mAnswersButton.setVisibility(View.GONE);
        
        return v;
	}

    protected void setIconFavorited(Post post, Button button) {
        if (post.getUserAttributes().getIsFavorited())
            button.setCompoundDrawables(mFavoriteFull, null, null, null);
        else
            button.setCompoundDrawables(mFavoriteEmpty, null, null, null);
    }
	   
    protected void setIconUpvote(Post post, Button button) {
        if (post.getUserAttributes().getIsUpvoted())
            button.setCompoundDrawables(mUpvoteFull, null, null, null);
        else
            button.setCompoundDrawables(mUpvoteEmpty, null, null, null);
    }
    
    protected void setVoteText(Post post, Button button) {
        if (post.getVotes() == 1)
            button.setText(mPost.getVotes() + " upvote");
        else
            button.setText(mPost.getVotes() + " upvotes");
    }

}