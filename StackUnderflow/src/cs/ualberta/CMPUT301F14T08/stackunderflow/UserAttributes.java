/**
 * UserAttributes class (stores attributes local to machine)
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;

public class UserAttributes {
	
	private boolean mIsUpvoted;
	private boolean mIsFavorited;
	private boolean mIsReadLater;
	private boolean mIsUsers;
	private boolean mIsRead;


    public UserAttributes(){
        mIsUsers = false;
        mIsUpvoted = false;
        mIsFavorited = false;
        mIsReadLater = false;
        mIsRead = false;
    }
	
	public UserAttributes(boolean isUsers){
    	mIsUsers = isUsers;
    	mIsUpvoted = false;
    	mIsFavorited = false;
    	mIsReadLater = false;
    	mIsRead = false;
	}

	public boolean getIsFavorited() {
		return mIsFavorited;
	}

	public void toggleIsFavorited() {
		mIsFavorited = !mIsFavorited;
	}
	
	public void setIsFavorited(boolean favorited) {
        mIsFavorited = favorited;
    }


	public boolean getIsUpvoted() {
		return mIsUpvoted;
	}
	
    public void setIsUpvoted(boolean isUpvoted) {
        this.mIsUpvoted = isUpvoted;
    }


	public void toggleIsUpvoted() {
		mIsUpvoted = !mIsUpvoted;
	}
	
	public boolean getIsUsers() {
		return mIsUsers;
	}

	public boolean getIsReadLater() {
		return mIsReadLater;
	}

	public void setIsReadLater(boolean isReadLater) {
		mIsReadLater = isReadLater;
	}

	public boolean getIsRead() {
		return mIsRead;
	}

	public void setIsReadTrue() {
		mIsRead = true;
		if (mIsReadLater) setIsReadLater(false);
	}



	
}
