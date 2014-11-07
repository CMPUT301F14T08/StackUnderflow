/**
 * Reply class (utilized by Post class)
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
	
