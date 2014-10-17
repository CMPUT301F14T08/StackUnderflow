package cs.ualberta.CMPUT301F14T08.stackunderflow;


import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * 
 * StackUnderflow application
 * Post base class
 * 
 * @author Michael Williams
 * 
 */

public class Post {
	
	private ArrayList<Reply> mReplies;
	private UUID mID; //TODO do we want random UUID or another method?
	private String mText;
	private String mAuthor;
	private int mUpvotes;
	private Date mDate;
	private String mPhoto; // placeholder: need image format, likely BitmapFactory implementation
	

	public Post() {
		mReplies = new ArrayList<Reply>();
		UUID mID = UUID.randomUUID(); //TODO do we want random UUID or another method?
	}

	public ArrayList<Reply> getmReplies() {
		return mReplies;
	}

	public void setmReplies(ArrayList<Reply> mReplies) {
		this.mReplies = mReplies;
	}

	public UUID getID() {
		return mID;
	}

	public void setID(UUID mID) {
		this.mID = mID;
	}

	public String getText() {
		return mText;
	}

	public void setText(String mText) {
		this.mText = mText;
	}

	public String getAuthor() {
		return mAuthor;
	}

	public void setAuthor(String mAuthor) {
		this.mAuthor = mAuthor;
	}

	public int getVotes() {
		return mUpvotes;
	}

	public void setVotes(int mUpvotes) {
		this.mUpvotes += mUpvotes;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date mDate) {
		this.mDate = mDate;
	}

	public String getPicture() {
		return mPhoto;
	}

	public void setPicture(String mPhoto) {
		this.mPhoto = mPhoto;
	}
	
	public void addReply(Reply reply){
		mReplies.add(reply);
	}
	
	
	
}