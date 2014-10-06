package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.Date;

/**
 * 
 * StackUnderflow application
 * Reply class (utilized by Post class)
 * 
 * @author Michael Williams
 * 
 */

public class Reply {
	


	private Date mDate;
	private String mText;
	private	String mAuthor;
	
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
