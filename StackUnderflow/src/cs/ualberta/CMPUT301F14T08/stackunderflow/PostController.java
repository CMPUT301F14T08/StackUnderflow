/**
 * StackUnderflow application PostController. Point of access for post manager - handles
 * differences between online/cached post manager automatically. Determines whether
 * to provide online or cache manager based on online/offline status.
 * @author Cmput301 Winter 2014 Group 8
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;
import java.util.UUID;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


// TODO: Currently this just controls the CachedPostManager. 
// Update during Project Part 3, Week 2 to include OnlineCachedManager.

public class PostController {
	private static PostController sPostController;
	private PostManager mPostManager;
	private Context mContext;

	// Keep this private!
	private PostController(Context context) {
		// Checks if the user is connected to the Internet it will use the online post manager otherwise it
		// will use a cached post manger that will later be pushed online when the user enters a network.
		mContext = context;

		if(isOnline()) {
			mPostManager = OnlinePostManager.getInstance(context);
			Log.i("Debug", "Using Online Post Manager");
		}
		
		else {
		    mPostManager = CachedPostManager.getInstance(context);	
		    Log.i("Debug", "Using Offline Post Manager");
		}
	}
	
	// check if we are online
	// returns true if online returns false if off line 
	public boolean isOnline() {
		
		ConnectivityManager cm =(ConnectivityManager)mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
		    // There are no active networks.
			Log.d("Debug", "Is Online: False");
		    return false;
		}
		Log.d("Debug", "Is Online: " + ni.isConnected());
		return ni.isConnected();
	}
	
	// retrieves the post controller or initializes it if it doesn't exits
	// pushes offline updates online
	private static PostController getInstance(Context context) {
	    
		if (sPostController == null) {
			sPostController = new PostController(context.getApplicationContext());
			return sPostController;
		}
		
        // if using cached list and we go online than switch to the online post manager
        else if (sPostController.isOnline()) {
            sPostController.mPostManager = OnlinePostManager.getInstance(context);
        }
        // if using cached list and we go online than switch to the online post manager
        else {
            sPostController.mPostManager = CachedPostManager.getInstance(context);
        }
        
        return sPostController;
	}
	
	// Static initializer, use this to get the active instance if 
    // you're just concerned with refreshing a single post
    public static PostController getInstanceNoRefresh(Context context) {
        
        getInstance(context);
        return sPostController;
    }
	
	// Static initializer, use this to get the active instance if 
	// you're just concerned with refreshing a single post
	public static PostController getInstanceForID(Context context, UUID postUUID) {
	    
        getInstance(context);

        // if we're using the online list, refresh for the required post
        if (sPostController.usingOnlinePostManager()) {
       
            Question question = sPostController.getQuestion(postUUID);
            ((OnlinePostManager)sPostController.mPostManager).refreshQuestion(question);
        }
        
        return sPostController;
	}
	
	// Static initializer, use this to get the active instance if 
    // you're concerned with refreshing the entire list
	public static PostController getInstanceForList(Context context) {
	    getInstance(context);
	    
        // if we're using the online list, refresh it
        if (sPostController.usingOnlinePostManager()) {
            // if we've added anything that hasn't been pushed to live, try to push it now
            ((OnlinePostManager)sPostController.mPostManager).pushOfflineUpdates();
            ((OnlinePostManager)sPostController.mPostManager).refreshAll();
        }
        
        return sPostController;
	}

	// Given a answer/reply/or question returns the question related to the object
	// For example, given an answer - returns answer's the parent question
	// given an answer reply - returns the answer's parent question
	public Question getRelatedQuestion(Object object) {
	    
	    if (object instanceof Question)
	        return (Question)object;
	    
	    if (object instanceof Answer)
	        return (Question)mPostManager.getPost(((Answer)object).getParentID());
	    
	    if (object instanceof Reply) {
	        Reply reply = (Reply) object;
	        return (Question)mPostManager.getPost(reply.getQuestionID());
	    }
	        
	    throw new InvalidParentException();
	}
	
	// Given a UUID returns the corresponding question
	// returns parent question if given an answer uuid
    public Question getQuestion(UUID uuid) {
        return mPostManager.getQuestion(uuid);
    }
   
	// Type Checking
    // -- Use this to tell if we're online or offline
    public boolean usingOnlinePostManager() {
        if(mPostManager instanceof CachedPostManager) 
            return false;
        return true;
    }
    
    public void addToCache(Question question) {
        if (usingOnlinePostManager()) {
            ((OnlinePostManager)mPostManager).addToCache(question);
        }
    }
	
	
    public PostManager getPostManager() {
        return mPostManager;
    }
}

