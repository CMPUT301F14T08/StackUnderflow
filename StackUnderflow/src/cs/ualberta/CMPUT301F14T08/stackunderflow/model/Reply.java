

package cs.ualberta.CMPUT301F14T08.stackunderflow.model;

import java.util.Date;
import java.util.UUID;

import cs.ualberta.CMPUT301F14T08.stackunderflow.exceptions.InvalidParentException;
/**
 * Reply class (utilized by Post class) This is the class that holds the information of when a user wishs to make a comment about a question or an answer.
 * it has a body of text, a UUID, a date, a signature, and it allows to see if there is already such a post online that will remove change if it is pushed online
 * @author Cmput301 Winter 2014 Group 8
 */
public class Reply {

    private UUID mUUID;
    private String mText;
    private Date mDate;
    private	String mSignature;
    private boolean mExistsOnline; // flag stating whether the reply exists online yet
    private UUID mParentID; // immediate parent
    private UUID mQuestionID;  // UUID of question containing this object (if this reply is to an answer, it will be the parent Question of that answer)

    public Reply(String text, String signature) {
        mText = text;
        mSignature = signature;
        mUUID = UUID.randomUUID();
        mDate = new Date();
        mExistsOnline = false;
        mParentID = null;
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

    public void setExistsOnline(boolean isPushedToLive) {
        mExistsOnline = isPushedToLive;
    }

    public UUID getParentID() {
        return mParentID;
    }

    public UUID getQuestionID() {
        return mQuestionID;
    }
    /**
     * Checks if the passed post is a question or answer
     * if its and question it will simply output the post
     * if its a answer it will output the answers parent.
     * will throw an exception if it answer parent is null.
     * @param post
     */
    public void setParent(Post post) {
        this.mParentID = post.getID();

        if (post instanceof Question) {
            this.mQuestionID = post.getID();
        }
        else {
            if (((Answer)post).getParentID() == null)
                throw new InvalidParentException();
            this.mQuestionID = ((Answer)post).getParentID();
        }
    }

}	

