package cs.ualberta.CMPUT301F14T08.stackunderflow.model;

import java.util.HashMap;
import java.util.UUID;
/**
 * UserProfileManager allows for modification of private attributes such found in UserProfile
 * mUsername is the username that the device has specified to author posts with. Default being Guest.
 * mUserAttributesMap is a hashmap used to hold all users allowing for us to know which have up voted and favorite a post
 * mAnswersPostedCount a int representing the number of Answers a user has posted. By Defualt this value is 0.
 * mQuestionPostedCount a int representing the number of Questions a user has posted. By Defualt this value is 0.
 * @author Cmput301 Winter 2014 Group 8
 */
public class UserProfile {
    private String mUsername;
    private static HashMap<UUID, UserAttributes> mUserAttributesMap = new HashMap<UUID, UserAttributes>();
    private int mAnswersPostedCount;
    private int mQuestionsPostedCount;

    public UserProfile(){
        mUsername ="Guest";
        mAnswersPostedCount=0;
        mQuestionsPostedCount=0;

    }
    public String getUsername(){
        return mUsername;
    }
    public void setUsername(String username){
        mUsername=username;
    }

    public int getAnswerPostedCount(){
        return mAnswersPostedCount;
    }
    public void incrementAnswersPostedCount(){
        mAnswersPostedCount++;
    }

    public int getQuestionsPostedCount(){
        return mQuestionsPostedCount;
    }
    public void incrementQuestionsPostedCount(){
        mQuestionsPostedCount++;
    }

    public UserAttributes getUserAttributesForId(UUID id){
        return mUserAttributesMap.get(id);
    }
    public void addToMap(UserAttributes userAttributes, UUID id){
        mUserAttributesMap.put(id,userAttributes);
    }

}
