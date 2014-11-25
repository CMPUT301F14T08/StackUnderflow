package cs.ualberta.CMPUT301F14T08.stackunderflow.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.LatLng;
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
    private UserAttributes mUserAttributes;
    protected int mUpvotesChangedOffline;
    protected boolean mExistsOnline;
    protected LatLng location;
    /**
     * Basic post part that is used to inherit into Answer and Question. This constructor is used when the user attempts to make a post
     * that has a text body, date, signature but not a picture. This will generate a random UUID. By default mExistsOline to false. 
     * @param text The main body of the question
     * @param signature the user name of the author that wrote the post.
     */
    public Post(String text, String signature) {
        mID = UUID.randomUUID(); 
        mText = text;
        mVotes = 0;
        mPicture = null;
        mSignature = signature;
        mDate = new Date();
        mReplies = new ArrayList<Reply>();
        mIsSelected = false;
        mIsFiltered = false;
        setmUserAttributes(new UserAttributes());
        mUpvotesChangedOffline = 0;
        mExistsOnline = false;
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
        setmUserAttributes(new UserAttributes());
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

    public UserAttributes getUserAttributes(){
        return getmUserAttributes();
    }

    public void setUserAttributes(UserAttributes userAttribs){
        this.setmUserAttributes(userAttribs);
    }

    public void clearUserAttributes(){
        this.setmUserAttributes(new UserAttributes());
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


    // TODO: Remove this, use java reflection in tests to set date
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
        setmUserAttributes(new UserAttributes());
    }

    public UserAttributes getmUserAttributes() {
        return mUserAttributes;
    }

    public void setmUserAttributes(UserAttributes mUserAttributes) {
        this.mUserAttributes = mUserAttributes;
    }
    public LatLng getLocation(){
		return location;
    	
    }
    public void setLocation(LatLng latLng){
    	location = latLng;
    }
 
 }
