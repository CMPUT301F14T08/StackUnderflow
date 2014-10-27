/**
 * TODO: Add nice comment here
 */

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CachedPostManager extends PostManager{
	private String QUESTION_CACHE_FILE = "cached_questions.json";
	protected static CachedPostManager sPostManager;
	
	// Keep this private!
	// -- Loads posts from cache when created
	private CachedPostManager(Context context) {
		super(context);

		try {
			mPosts = loadFromFile();
		} catch (Exception e) {
			mPosts = new ArrayList<Post>();
		}
		
		if (mPosts.size() == 0)
			loadTestQuestions();
	}
	
	//TODO: Delete this later!
	private void loadTestQuestions() {
		Question q1 = new Question(  
				"LOL I don't actually have a question, I just wanted to post.",
				"gregthegreg",
				"pic1",
				"MY FIRST QUESTION GUIS!!!11");
		
		Answer a1 = new Answer("LOL I AM ANSWERING MY OWN QUESTION!", "gregthegreg");
		Answer a2 = new Answer("Please just stop posting.", "djhindle");
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		
		Question q2 = new Question(
				"What is the difference between a UML class diagram,"
				+ "a UML Sequence diagram, and a UML state diagram?",
				"djhindle", 
				"UML Diagrams");
		
		// Q3 tests the concatenating of long titles/text
		Question q3 = new Question(
				"Hey DJ Hindle help me out?? I deleted my System 32 folder like you said,"
				+ "but now I'm getting this weird blue screen when I start"
				+ "my computer??? What's going on? How do I fix it. Is this a"
				+ "virus or what?",
				"gregthegreg", 
				"Weird Blue ScreenWeird Blue ScreenWeird Blue ScreenWeird "
				+" Blue ScreenWeird Blue ScreenWeird Blue Screen");
		
		Answer a3 = new Answer("Don't call me DJ Hindle :/", "djhindle");
		Answer a4 = new Answer("Don't call me DJ Hindle :/ Don't call me DJ Hindle :/ Don't "
				+ "call me DJ Hindle :/ Don't call me DJ Hindle :/ Don't call me DJ Hindle :/ ",
				"djhindle");
		
		q3.addAnswer(a3);
		q3.addAnswer(a4);
		
		mPosts.add(q1);
		mPosts.add(a1);
		mPosts.add(a2);
		mPosts.add(q2);
		mPosts.add(q3);
		mPosts.add(a3);
		mPosts.add(a4);
	}

	// Save Questions using Gson
	private void sendToFile() throws IOException {
		// Don't save the 'selected' attribute
		clearSelected();
		clearFilters();
		
		// Gson has to know explicitly that it's saving questions
		// We have to cast from Post to Question
		ArrayList<Question> questions = castToQuestions();
		Writer writer = null;
		
		try {
			Gson gson = new Gson();
			OutputStream answer_out = mContext.openFileOutput(QUESTION_CACHE_FILE, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(answer_out);
			gson.toJson(questions, writer);
		} 
		// Cleanup our writers if anything fails
		finally {
			if (writer != null)
				writer.close();
		}
	}
	
	// Load posts from file using GSON
	private ArrayList<Post> loadFromFile() throws IOException{

		ArrayList<Question> questions = new ArrayList<Question>();
		Reader reader = null;
		
		try {
			Gson gson = new Gson();
			InputStream input = mContext.openFileInput(QUESTION_CACHE_FILE);
			reader = new InputStreamReader(input);
			questions = gson.fromJson(reader, new TypeToken<ArrayList<Question>>() {}.getType());
			
		} finally {
			if (reader != null)
				reader.close();
		}
		
		// Separate out Answer References
		ArrayList<Post> posts = castToPosts(questions);
		return posts;
	}
	

	// Static initializer, use this to get the active instance.
	// This insures we only ever have one copy going at once!
	public static CachedPostManager getInstance(Context context) {
		if (sPostManager == null) {
			sPostManager = new CachedPostManager(context.getApplicationContext());
		}

		return sPostManager;
	}
	
	// Public save method
	// -- Returns true if successful
	@Override
	public boolean save(){
		try {
			sendToFile();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	

	// If we are using the CachedPostManager we are OFFLINE
	// So pushed-to-live should be set to false
	@Override
	public void addQuestion(Question newQuestion) {
		//TODO when user attributes are enabled, setIsPushedToLive(false)
		super.addQuestion(newQuestion);
	}
	
	@Override
	public void addAnswer(Answer newAnswer) {
		//TODO when user attributes are enabled, setIsPushedToLive(false)
		super.addAnswer(newAnswer);
	}
	
	//TODO: Implement in Project Part 4
	@Override
	public void addReply(Reply newReply) {
		super.addReply(newReply);
	}
	
	//TODO: Implement in Project Part 3, Week 2
	public void pushToLive(){
		return;
	}
}
