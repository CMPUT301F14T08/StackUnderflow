/**
 * StackUnderflow application PostController. Point of access for post manager - handles
 * differences between online/cached post manager automatically. Determines whether
 * to provide online or cache manager based on online/offline status.
 */

package cs.ualberta.CMPUT301F14T08.stackunderflow;
import android.content.Context;


// TODO: Currently this just controls the CachedPostManager. 
// Update during Project Part 3, Week 2 to include OnlineCachedManager.

public class PostController {
	private static PostController sPostController;
	private PostManager mPostManager;

	// Keep this private!
	private PostController(Context context) {
		//TODO: assign different post manager if isOnline is true
		mPostManager = CachedPostManager.getInstance(context);
	}
	
	// TODO: Implement in Project Part 3, Week 2
	private boolean isOnline() {
		return false;
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

