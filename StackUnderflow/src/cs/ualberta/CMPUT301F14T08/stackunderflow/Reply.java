/**
 * Reply class (utilized by Post class) This is the class that holds the information of when a user wishs to make a comment about a question or an answer.
 * it has a body of text, a UUID, a date, a signature, and it allows to see if there is already such a post online that will remove change if it is pushed online
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.Date;
import java.util.UUID;

public class Reply {
	
	private UUID mUUID;
	private String mText;
	private Date mDate;
    private long mTimeStamp;
	private	String mSignature;
	private boolean mExistsOnline; // flag stating whether the reply exists online yet
	
	public Reply(String text, String signature) {
		mText = text;
		mSignature = signature;
		mUUID = UUID.randomUUID();
		mDate = new Date();
		mExistsOnline = false;
	    mTimeStamp = System.currentTimeMillis();
	}

	public UUID getUUID(){
		return mUUID;
	}
	
	public String getText() {
		return mText;
	}
	
	public Date getDate() {
		return mDate;
	}
	
	public String getSignature() {
		return mSignature;
	}

    public boolean getExistsOnline() {
        return mExistsOnline;
    }
    
    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setExistsOnline(boolean isPushedToLive) {
        mExistsOnline = mExistsOnline;
    }
	
}	
	
