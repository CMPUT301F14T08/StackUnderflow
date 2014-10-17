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

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date mDate) {
		this.mDate = mDate;
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
	
}	
	
	
