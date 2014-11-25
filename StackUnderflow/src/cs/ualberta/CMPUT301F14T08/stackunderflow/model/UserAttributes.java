package cs.ualberta.CMPUT301F14T08.stackunderflow.model;
/**
 * UserAttributes class (stores attributes local to machine)
 * @author Cmput301 Winter 2014 Group 8
 */
public class UserAttributes {

    private boolean mIsUpvoted;
    private boolean mIsFavorited;
    private boolean mIsReadLater;
    private boolean mIsUsers;
    private boolean mIsRead;

/*
 * mIsUsers is true when the user was the one to post the question
 * mIsUpvoted is true when the user has upvoted the post
 * mIsFavorited is true when the user has favorited the post
 * mIsReadLater is true when the user has set the post to read later
 * mIsRead is true when the user reads a post
 */
    public UserAttributes(){
        mIsUsers = false;
        mIsUpvoted = false;
        mIsFavorited = false;
        mIsReadLater = false;
        mIsRead = false;
    }
/*
 * When calling user attributes is called with a boolean as parameter it will send that to if it belongs to the user.
 */
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

    public void toggleIsUsers() {
        mIsUsers = !mIsUsers;
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
