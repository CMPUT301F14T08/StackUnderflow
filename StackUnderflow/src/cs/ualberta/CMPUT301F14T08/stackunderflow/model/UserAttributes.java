
package cs.ualberta.CMPUT301F14T08.stackunderflow.model;

/**
 * UserAttributes class (stores attributes local to machine)
 * 
 * @author Cmput301 Winter 2014 Group 8
 */
public class UserAttributes {

    private boolean mIsUpvoted;
    private boolean mIsFavorited;
    private boolean mIsReadLater;
    private boolean mIsUsers;

    /*
     * mIsUsers is true when the user was the one to post the question mIsUpvoted is true when the
     * user has upvoted the post mIsFavorited is true when the user has favorited the post
     * mIsReadLater is true when the user has set the post to read later mIsRead is true when the
     * user reads a post
     */
    public UserAttributes() {
        mIsUsers = false;
        mIsUpvoted = false;
        mIsFavorited = false;
        mIsReadLater = false;
    }

    public boolean getIsFavorited() {
        return mIsFavorited;
    }

    public void toggleIsFavorited() {
        mIsFavorited = !mIsFavorited;
    }

    public boolean getIsUpvoted() {
        return mIsUpvoted;
    }

    public void toggleIsUpvoted() {
        mIsUpvoted = !mIsUpvoted;
    }

    public boolean getIsUsers() {
        return mIsUsers;
    }

    public void setIsUsers(boolean isUsers) {
        mIsUsers = isUsers;
    }

    public boolean getIsReadLater() {
        return mIsReadLater;
    }

    public void setIsReadLater(boolean isReadLater) {
        mIsReadLater = isReadLater;
    }
}
