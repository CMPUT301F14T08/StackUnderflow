package cs.ualberta.CMPUT301F14T08.stackunderflow;

/**
 * 
 * StackUnderflow application PostController. Used to check if the phone is
 * connected to online. Also used to get instances of PostController and
 * PostManager
 * 
 * @author Maciej Ogrocki
 * 
 */

public class PostController {
	private PostController sPostController;
	private PostManager mPostManager;

	private PostController() {

	}

	private boolean pollIfOnline() {
		// TODO: Check if the app in connect to the Internet
		return false;
	}

	public PostController getInstance() {
		// TODO: knowing the online status of the app choose which
		// postcontroller to use
		return sPostController;
	}

	public PostManager getPostManager() {
		// TODO: knowing the online status of the app choose which postmanger to
		// use
		return mPostManager;
	}

	public boolean usingOnlinePostManager() {
		// TODO: Decide which post manager to use depending on online status
		return false;
	}
}

