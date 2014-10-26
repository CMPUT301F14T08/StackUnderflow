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
	private	String mSignature;
	//private Post mParent; Is this needed?
	
	public Reply(String text, String signature) {
		mText = text;
		mSignature = signature;
		mUUID = UUID.randomUUID();
		mDate = new Date();
		//mParent = null; Is this needed?
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
	
}	
	
