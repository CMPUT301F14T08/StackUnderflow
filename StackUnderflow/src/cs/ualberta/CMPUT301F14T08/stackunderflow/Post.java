/** 
 * Post base class only ever really used as a parent of question and answers. Saves all information about posts such as the id text votes pictures and so on
 * This also allows setting and getting of of attributes of post. You should always use getters and setters. A basic post should never be called. It should always
 * be called as a Question or an Answer.
 */


package cs.ualberta.CMPUT301F14T08.stackunderflow;


import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Post {
	
    protected UUID mID; //TODO do we want random UUID or another method?
	protected String mText;
	protected int mVotes = 0;
	protected String mPicture;  // placeholder: need image format, likely BitmapFactory implementation
	protected String mSignature;
	protected Date mDate;
	protected ArrayList<Reply> mReplies = new ArrayList<Reply>();
	protected boolean mIsSelected;
	protected boolean mIsFiltered;
	protected UserAttributes mUserAttributes;
	protected int mUpvotesChangedOffline;
	protected boolean mExistsOnline;
	protected long mTimeStamp;
	

	public Post(String text, String signature) {
	    mID = UUID.randomUUID(); //TODO do we want random UUID or another method?
        mText = text;
        mVotes = 0;
        mPicture = null;
        mSignature = signature;
        mDate = new Date();
        mReplies = new ArrayList<Reply>();
        mIsSelected = false;
        mIsFiltered = false;
        mUserAttributes = new UserAttributes();
        mUpvotesChangedOffline = 0;
        mExistsOnline = false;
        mTimeStamp = System.currentTimeMillis();
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
	    mTimeStamp = System.currentTimeMillis();
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
	
   public long getTimeStamp() {
        return mTimeStamp;
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
	

	
	//Constructor used to properly test SortByDate()
	public Post(String text, String signature, String picture, Date date){
		mID = UUID.randomUUID(); //TODO do we want random UUID or another method?
		mText = text;
		mVotes = 0;
		mPicture = picture;
		mSignature = signature;
		mDate = date;
		mReplies = new ArrayList<Reply>();
		mIsSelected = false;
		mIsFiltered = false;
		mUserAttributes = new UserAttributes();
	}

}
