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
	
	/*
	public Post(ArrayList<Reply> mReplies, UUID mID, String mText,
			String mAuthor, int mUpvotes, Date mDate, String mPhoto) {
		super();
		this.mReplies = mReplies;
		this.mID = mID;
		this.mText = mText;
		this.mAuthor = mAuthor;
		this.mUpvotes = mUpvotes;
		this.mDate = mDate;
		this.mPhoto = mPhoto;
	}
	*/

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

	public UUID getmID() {
		return mID;
	}

	public void setmID(UUID mID) {
		this.mID = mID;
	}

	public String getmText() {
		return mText;
	}

	public void setmText(String mText) {
		this.mText = mText;
	}

	public String getmAuthor() {
		return mAuthor;
	}

	public void setmAuthor(String mAuthor) {
		this.mAuthor = mAuthor;
	}

	public int getmUpvotes() {
		return mUpvotes;
	}

	public void setmUpvotes(int mUpvotes) {
		this.mUpvotes += mUpvotes;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public String getmPhoto() {
		return mPhoto;
	}

	public void setmPhoto(String mPhoto) {
		this.mPhoto = mPhoto;
	}
	
	public void addReply(Reply reply){
		mReplies.add(reply);
	}
	
	
	
}