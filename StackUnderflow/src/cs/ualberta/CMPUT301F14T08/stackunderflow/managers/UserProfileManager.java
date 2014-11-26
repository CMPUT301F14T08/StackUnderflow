package cs.ualberta.CMPUT301F14T08.stackunderflow.managers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.UserAttributes;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.UserProfile;
/**
 * UserProfileManager does exactly what the name suggests. It managers the profile manager in aspects of saving and loading to the local device
 * and checking if there is already a existing user profile. By default the user file it is saved to is user_profile.json. UserProfile should be called
 * by getInstance. Because userProfile should act like a singleton and not be called if one already exists.
 * @author Cmput301 Winter 2014 Group 8
 */
public class UserProfileManager {
    private static UserProfileManager sUserProfileManager;
    private static String PROFILE_FILE = "user_profile.json";
    private UserProfile mUserProfile;
    protected Context mContext;

    private UserProfileManager(Context context){
        mContext = context;
        try {
            mUserProfile = loadFromFile();
        } catch (Exception e) {
            mUserProfile = new UserProfile();
        }
    }

    /**
     * This will attempt to save the user profile to the a file called "user_profile.json" 
     * @throws IOException will occur if for some reason the save fails. (Eg. If there is not enough memory on the device)
     */
    private void sendToFile() throws IOException {	
        Writer writer = null;	
        try {
            Gson gson = new Gson();
            OutputStream answer_out = mContext.openFileOutput(PROFILE_FILE, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(answer_out);
            gson.toJson(mUserProfile, writer);
        } 
        finally {
            if (writer != null)
                writer.close();
        }
    }

    /**
     * LoadFromFile will attempt to load any information that is saved on the user_profile.json file on the device
     * @return returns anything that was found in "user_profile.json" if nothing was found it will simply output a blank UserProfile
     * @throws IOException if the load fails it will throw an IOExecption
     */
    private UserProfile loadFromFile() throws IOException{
        Reader reader = null;
        UserProfile userProfile=null;
        try {
            Gson gson = new Gson();
            InputStream input = mContext.openFileInput(PROFILE_FILE);
            reader = new InputStreamReader(input);
            userProfile = gson.fromJson(reader, new TypeToken <UserProfile>() {}.getType());

        } finally { 
            if (reader != null)
                reader.close();
        }
        return userProfile;		
    }

    /**
     * Creates a singleton of sUserProfileManager. THIS IS HOW YOU CREATE A USERPROFILEMANAGER
     * @return the current user profile or a new UserProfileManager when no UserProfileManager can be found
     */
    public static UserProfileManager getInstance(Context context){
        if (sUserProfileManager == null) {
            sUserProfileManager = new UserProfileManager(context.getApplicationContext());
        }
        return sUserProfileManager;
    }

    /**
     * Attempt to save to a file locally on the device called user_profile.json
     * @return true if was able to load, false if it failed
     */
    public boolean save(){
        try {
            sendToFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean getIsUpvoted(Post post) {
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        if (attributes == null)
            return false;
        else
            return attributes.getIsUpvoted();
    }
    
    public void toggleIsUpvoted(Post post) {
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        attributes.toggleIsUpvoted();
        save();
    }

    public boolean getIsUsers(Post post) {
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        if (attributes == null)
            return false;
        else
            return attributes.getIsUsers();
    }

    // Posts can only be added by user, so setIsUsers will always be true for new posts
    public void saveNewPostAttributes(Post post) {
        UUID id = post.getID();
        UserAttributes newAttribs = new UserAttributes();
        newAttribs.setIsUsers(true);
        mUserProfile.addToMap(id, newAttribs);
        
        
        if (post instanceof Question)
            mUserProfile.incrementQuestionsPostedCount();
        else
            mUserProfile.incrementAnswersPostedCount();
        
        save();
    }
    
    public boolean getIsFavorite(Post post){
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        if (attributes == null)
            return false;
        else
            return attributes.getIsFavorited();
    }
    
    public void toggleIsFavorited(Post post) {
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        attributes.toggleIsFavorited();
        save();
    }
    
    public boolean getIsReadLater(Post post){
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        if (attributes == null)
            return false;
        else
            return attributes.getIsReadLater();
    }
    
    public void setIsReadLater(Post post) {
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        
        if (attributes == null) {
            attributes = new UserAttributes();
            attributes.setIsReadLater(true);
            mUserProfile.addToMap(id, attributes);
            return;
        }
             
        attributes.setIsReadLater(true);
        save();
    }

    public void setRead(Post post){
        UUID id = post.getID();
        UserAttributes attributes = mUserProfile.getUserAttributesForId(id);
        
        if (attributes == null) {
            attributes = new UserAttributes();
            mUserProfile.addToMap(id, attributes);
            return;
        }
        
        attributes.setIsReadLater(false);
        save();
    }    


    public String getUsername(){
        return mUserProfile.getUsername();
    }
    

    public void setUsername(String username){
        mUserProfile.setUsername(username);
        save();
    }

    public int getAnswerPostedCount() {
        return mUserProfile.getAnswerPostedCount();
    }
    
    public int getQuestionsPostedCount() {
        return mUserProfile.getQuestionsPostedCount();
    }
}