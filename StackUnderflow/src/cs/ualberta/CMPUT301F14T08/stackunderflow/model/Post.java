package cs.ualberta.CMPUT301F14T08.stackunderflow.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.LocManager;

/** 
 * Post base class only ever really used as a parent of question and answers. Saves all information about posts such as the id text votes pictures and so on
 * This also allows setting and getting of of attributes of post. You should always use getters and setters. A basic post should never be called. It should always
 * be called as a Question or an Answer.
 */
public class Post {

    protected UUID mID; 
    protected String mText;
    protected int mVotes = 0;
    protected String mPicture;  // placeholder: need image format, likely BitmapFactory implementation
    protected String mSignature;
    protected Date mDate;
    protected ArrayList<Reply> mReplies = new ArrayList<Reply>();
    protected boolean mIsSelected;
    protected boolean mIsFiltered;
    protected int mUpvotesChangedOffline;
    protected boolean mExistsOnline;
    protected LatLng mLocation;
    
    /**
     * Basic post part that is used to inherit into Answer and Question. This constructor is used when the user attempts to make a post
     * that has a text body, date, signature but not a picture. This will generate a random UUID. By default mExistsOline to false. 
     * @param text The main body of the question
     * @param signature the user name of the author that wrote the post.
     */
    public Post(String text, String signature) {
        this(text, signature, null);
    }

    public Post(String text, String signature, String picture){
        mID = UUID.randomUUID(); 
        mText = text;
        mVotes = 0;
        mPicture = picture;
        mSignature = signature;
        mDate = new Date();
        mReplies = new ArrayList<Reply>();
        mIsSelected = false;
        mIsFiltered = false;
        mUpvotesChangedOffline = 0;
        mExistsOnline = false;
        mLocation = null;
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

    public int setVotes(int votes) {
        return mVotes = votes;
    }


    public ArrayList<Reply> getReplies() {
        return mReplies;
    }

    public void addReply(Reply newReply){
        newReply.setParent(this);
        mReplies.add(newReply);
    }

    public void toggleIsSelected(){
        mIsSelected = !mIsSelected;
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
    
    public String getLocationString(Context context){
    	return hasLocation() ? LocManager.getLocationString(context, mLocation) : null;
    }

    public LatLng getLocation(){
		return mLocation;
    	
    }
    public void setLocation(LatLng location){
    	mLocation = location;
    }
    
    public boolean hasLocation(){
    	return mLocation != null;
    }
    
}
