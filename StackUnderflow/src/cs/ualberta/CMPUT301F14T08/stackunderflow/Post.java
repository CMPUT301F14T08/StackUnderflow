/** 
 * Post base class
 */


package cs.ualberta.CMPUT301F14T08.stackunderflow;


import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Post {
	
	private UUID mID; //TODO do we want random UUID or another method?
	private String mText;
	private int mVotes = 0;
	private String mPicture;  // placeholder: need image format, likely BitmapFactory implementation
	private String mSignature;
	private Date mDate;
	private ArrayList<Reply> mReplies = new ArrayList<Reply>();
	private boolean mIsSelected;
	private boolean mIsFiltered;
	private UserAttributes mUserAttributes;
	private int mUpvotesChangedOffline;
    private boolean mExistsOnline;
	

	public Post(String text, String signature) {
		this(text, signature, null);
	}
	
	public Post(String text, String signature, String picture){
		mID = UUID.randomUUID(); //TODO do we want random UUID or another method?
		mText = text;
		mVotes = 0;
		mPicture = picture;
		mSignature = signature;
		mDate = new Date();
		mReplies = new ArrayList<Reply>();
		mIsSelected = false;
		mIsFiltered = false;
		mUserAttributes = new UserAttributes();
		mUpvotesChangedOffline = 0;
	    mExistsOnline = false;
	}
	
	public UUID getID() {
		return mID;
	}
	
	public String getText() {
		return mText;
	}
	
	public String getSignature() {
		return mSignature;
	}
	
	public String getPicture() {
		return mPicture;
	}
	
	public Date getDate() {
		return mDate;
	}
	
	public int getVotes() {
		return mVotes;
	}
	
	public void incrementVotes(){
		mVotes += 1;
	}
	
	public void decrementVotes(){
		if(mVotes > 0) mVotes -= 1;
	}
	
public void setText(String mText) {
		this.mText = mText;
	}

public int setVotes(int votes) {
        return mVotes = votes;
    }
	    

	public ArrayList<Reply> getReplies() {
		return mReplies;
	}
	
	public void addReply(Reply newReply){
		mReplies.add(newReply);
	}
	
	public boolean getIsSelected(){
		return mIsSelected;
	}
	
	public void setIsSelected(boolean isSelected){
		mIsSelected = isSelected;
	}
	
	public boolean hasPicture(){
		return mPicture != null ? true : false;
	}
	
	public boolean getIsFiltered(){
		return mIsFiltered;
	}
	
	public void setIsFiltered(boolean isFiltered){
		mIsFiltered = isFiltered;
	}
	
	public UserAttributes getUserAttributes(){
		return mUserAttributes;
	}
	
    public int getUpvotesChangedOffline() {
        return mUpvotesChangedOffline;
    }

    public void setUpvotesChangedOffline(int upvotesChangedOffline) {
        mUpvotesChangedOffline = upvotesChangedOffline;
    }
    
    public boolean getExistsOnline() {
        return mExistsOnline;
    }

    public void setExistsOnline(boolean existsOnline) {
        this.mExistsOnline = existsOnline;
    }
	
}
