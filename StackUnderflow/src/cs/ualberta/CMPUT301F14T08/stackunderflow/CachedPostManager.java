

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * CachedPostManager manages anything a user enters when they are offline. Also only post manager uses one of these to hold posts
 * sendToFile allows for local saving of posts that can be pushed later to online to a Gson file on their device
 * loadToFile allows for users to load all local data that they already have saved inside their Gson file on their device
 * toggleUpove changes the upvote status from that user in the local cache
 * @author Cmput301 Winter 2014 Group 8
 */
public class CachedPostManager extends PostManager{
	private String QUESTION_CACHE_FILE = "cached_questions.json";
	protected static CachedPostManager sPostManager;
	protected boolean addedOffline;
	
	// Keep this private!
	// -- Loads posts from cache when created
	private CachedPostManager(Context context) {
		super(context);
		try {
			mQuestions = loadFromFile();
		} catch (Exception e) {
		    mQuestions = new ArrayList<Post>();
		}
		addedOffline = true;
	}
	
	//TODO: Delete this later!
	private void loadTestQuestions() {
		Question q1 = new Question(  
				"LOL I don't actually have a question, I just wanted to post.",
				"gregthegreg",
				"pic1",
				"MY FIRST QUESTION GUIS!!!11");
		
		Answer a1 = new Answer("LOL I AM ANSWERING MY OWN QUESTION!", "gregthegreg");
		a1.setExistsOnline(true);
		Answer a2 = new Answer("Please just stop posting.", "djhindle");
		a2.setExistsOnline(true);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.incrementVotes();
		
		Question q2 = new Question(
				"What is the difference between a UML class diagram,"
				+ "a UML Sequence diagram, and a UML state diagram?",
				"djhindle", 
				"UML Diagrams");
		q2.incrementVotes();
		q2.incrementVotes();
		
		// Q3 tests the concatenating of long titles/text
		Question q3 = new Question(
				"Hey DJ Hindle help me out?? I deleted my System 32 folder like you said,"
				+ "but now I'm getting this weird blue screen when I start"
				+ "my computer??? What's going on? How do I fix it. Is this a"
				+ "virus or what?",
				"gregthegreg", 
				"Weird Blue ScreenWeird Blue ScreenWeird Blue ScreenWeird "
				+" Blue ScreenWeird Blue ScreenWeird Blue Screen");
		q2.setVotes(5);
		q3.setVotes(15);
		
		
		Answer a3 = new Answer("Don't call me DJ Hindle :/", "djhindle");
		a3.setExistsOnline(true);
		Answer a4 = new Answer("Attached is a picture of how much I don't want you to call me DJ Hindle", "djhindle", "hindlepic.jpg");
		a4.setExistsOnline(true);
		
		q3.addAnswer(a3);
		q3.addAnswer(a4);
		
		mQuestions.add(q1);
		mQuestions.add(q2);
		mQuestions.add(q3);
	}

	/**
	 * Save Questions using Gson
	 * @throws IOException if it cannot save to gson file
	 */
	private void sendToFile() throws IOException {
		// Don't save the 'selected' attribute
		clearSelected();
		clearFilters();
		
		Writer writer = null;
		
		try {
			Gson gson = new Gson();
			OutputStream answer_out = mContext.openFileOutput(QUESTION_CACHE_FILE, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(answer_out);
			gson.toJson(mQuestions, writer);
		} 
		// Cleanup our writers if anything fails
		finally {
			if (writer != null)
				writer.close();
		}
	}
	
	/**
	 *  Load posts from file using GSON
	 * @return Posts a Array List of Posts that the device pulls from a local gson file from the device. Will return a blank list if there is no gson file
	 * @throws IOException if there is a error preventing the gson file from being loaded. 
	 */
	public ArrayList<Post> loadFromFile() throws IOException{

		Reader reader = null;
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			Gson gson = new Gson();
			InputStream input = mContext.openFileInput(QUESTION_CACHE_FILE);
			reader = new InputStreamReader(input);
			questions = gson.fromJson(reader, new TypeToken <ArrayList<Question>>() {}.getType());
			
		} finally {
			if (reader != null)
				reader.close();
		}
		
		// Separate out Answer References
		ArrayList<Post> posts = castToPosts(questions);
		return posts;
	}
	

	/** Static initializer, use this to get the active instance.
	 * This insures we only ever have one copy going at once!
	 * @return a singleton post manager. 
	 */
	public static CachedPostManager getInstance(Context context) {
		if (sPostManager == null) {
			sPostManager = new CachedPostManager(context.getApplicationContext());
		}

		return sPostManager;
	}
	
	/**
	 *  Public save method
	 * @return true if successful
	 */
	public boolean save(){
		try {
			sendToFile();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	

	@Override
	public void addQuestion(Question newQuestion) {
		UserProfileManager.getInstance(mContext).getUserProfile().addToMap(newQuestion.mUserAttributes, newQuestion.getID());
		super.addQuestion(newQuestion);
		save();
		addedOffline = true;
		
		
	}
	
	@Override
	public void addAnswer(Question parent, Answer newAnswer) {
		super.addAnswer(parent, newAnswer);
		save();
		addedOffline = true;
		UserProfileManager.getInstance(mContext).getUserProfile().addToMap(newAnswer.mUserAttributes, newAnswer.getID());
	}
	
	//TODO: Implement in Project Part 4
	@Override
	public void addReply(Question parent, Reply newReply) {
		super.addReply(parent, newReply);
		save();
		addedOffline = true;
	}
	
	//TODO: Update with implementation of user attributes
    /**
     * For now this will just increment votes
     * @param a post that will have its vote increased by one if it is currently false or a decreased by one if it is currenly false
     */
	@Override
    public void toggleUpvote(Post post) {
        super.toggleUpvote(post);
        int incrementVotes = 1;
        
        if (!post.getUserAttributes().getIsUpvoted()) {
            incrementVotes = -1;
        }
        
        post.setUpvotesChangedOffline(incrementVotes);
        save();
        addedOffline = true;
    }
	/**
	 * sets as a users favorite which if is called again this favorite is removed.
	 * @param post that will be set or removed as a favorite
	 */
    @Override
    public void toggleFavorite(Post post) {
        super.toggleFavorite(post);
        save();
        addedOffline = true;
    }
	
    public boolean hasAddedOffline() {
        return addedOffline;
    }

}
