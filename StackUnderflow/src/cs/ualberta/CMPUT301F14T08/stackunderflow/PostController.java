/**
 * StackUnderflow application PostController. Point of access for post manager - handles
 * differences between online/cached post manager automatically. Determines whether
 * to provide online or cache manager based on online/offline status.
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


// TODO: Currently this just controls the CachedPostManager. 
// Update during Project Part 3, Week 2 to include OnlineCachedManager.

public class PostController {
	private static PostController sPostController;
	private PostManager mPostManager;
	private Context mContext;

	// Keep this private!
	private PostController(Context context) {
		//TODO: assign different post manager if isOnline is true
		mContext = context;
		mPostManager = OnlinePostManager.getInstance(context);
	}
	
	// check if we are online
	// returns true if online returns false if off line 
	public boolean isOnline() {
		ConnectivityManager cm =(ConnectivityManager)mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
		    // There are no active networks.
		    return false;
		}
		return ni.isConnected();
	}

	// TODO: Implement in Project Part 3, Week 2
	private void pollIfOnline() {
		return;
	}

	// Static initializer, use this to get the active instance.
	public static PostController getInstance(Context context) {
		if (sPostController == null) {
			sPostController = new PostController(context.getApplicationContext());
		}

		return sPostController;
	}

	public PostManager getPostManager() {
		return mPostManager;
	}
	
	
	// Type Checking
	// -- Use this to tell if we're online or offline
	public boolean usingOnlinePostManager() {
		if(mPostManager instanceof CachedPostManager) 
			return false;
		return true;
	}
	
	
}

