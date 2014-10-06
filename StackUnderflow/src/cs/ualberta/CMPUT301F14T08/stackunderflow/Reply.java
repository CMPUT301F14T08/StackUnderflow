package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.Date;

public class Reply {
	
	private Date mDate;
	private String mText;
	private	String mAuthor;
	
	public Reply(Date mDate, String mText, String mAuthor) {
		super();
		this.mDate = mDate;
		this.mText = mText;
		this.mAuthor = mAuthor;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
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
	
}	
	
	
