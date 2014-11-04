/**
 * TODO: Add nice comment here
 */


package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import android.content.Context;

public abstract class PostManager {
	protected ArrayList<Post> mPosts;
	protected Context mContext;
	
	protected PostManager(Context context){
		mContext = context;
		mPosts = new ArrayList<Post>();
	}
	

	// Strips 'selected' from post objects.
	protected void clearSelected() {
		for (int i=0; i< mPosts.size(); i++) {
			Post item = mPosts.get(i);
			item.setIsSelected(false);
		}
	}
	
	// TODO: This logic might be better in the Question/Answer class?
	// Detection for subclass
	protected boolean isQuestion(Post obj) {
		if(obj instanceof Question) 
			return true;
		if(obj instanceof Answer)
			return false;
		throw new InvalidPostTypeException();
	}
	
	// Returns a list of the Questions in mPosts
	// Used for saving Posts.
	protected ArrayList<Question> castToQuestions() {
		ArrayList<Question> questions = new ArrayList<Question>();
		for (int i=0; i< mPosts.size(); i++) {
			Post item = mPosts.get(i);
			if (isQuestion(item))
				questions.add((Question)item);
		}
		return questions;
	}

	// Given a list of Questions returns a list of Posts
	// Used for loading.
	// -- Each Answer is added as a Post as well
	protected ArrayList<Post> castToPosts(ArrayList<Question> questions) {
		ArrayList<Post> posts = new ArrayList<Post>();
		if (questions == null) 
			posts = new ArrayList<Post>();

		for (int i=0; i<questions.size(); ++i) {
			
			Question item = questions.get(i);
			ArrayList<Answer> answers = item.getAnswers();
			posts.add(item);
			
			for (int j=0; j<answers.size(); ++j) {
				posts.add(answers.get(j));
			}
		}
		
		return posts;
	}
	
	public Post getPost(UUID uuid) {
		for (int i=0; i< mPosts.size(); i++) {
			Post item = mPosts.get(i);
			if (item.getID().equals(uuid))
				return item;
		}
		return null;
	}
	
   protected boolean updateIfExists(Post post) {
       if (post == null) {
           return false;
       }
       
        for (int i=0; i< mPosts.size(); i++) {
            Post item = mPosts.get(i);
            if (item.getID() == post.getID()) {
                mPosts.set(i, post);
                return true;
            }
        }
        return false;
    }

	public ArrayList<Post> getPosts(){
		return mPosts;
	}
	
	// adds a question to our list of posts
	public void addQuestion(Question newQuestion){
		mPosts.add(newQuestion);
	}
	
	// adds an answer to our list of posts
	public void addAnswer(Question parent, Answer newAnswer){
		parent.addAnswer(newAnswer);
		mPosts.add(newAnswer);
	}
	
	//TODO: Implement in Project Part 4
	public  void addReply(Question parent, Reply newReply) {
	};
	
	//TODO: Implement with user attributes
	public void toggleFavorite(Post post) {
	}
	
	//TODO: Update with implementation of user attributes
	// For now this will just increment votes
	public void toggleUpvote(Post post) {
	    post.incrementVotes();
	}
	
	//TODO: Update with implementation of user attributes
	public void toggleReadLater(Post post) {
	    
	}
	
	// Sorts posts by number of votes (Descending Order)
	public void sortByScore() {
		Collections.sort(mPosts, new Comparator<Post>() {
			  public int compare(Post lhs, Post rhs) {
			      return (new Integer(rhs.getVotes())).compareTo(new Integer(lhs.getVotes()));
			  }
		});
	}
	
	// Sorts posts by most recent date first (Descending Order)
	public void sortByDate(){
		Collections.sort(mPosts, new Comparator<Post>() {
			  public int compare(Post lhs, Post rhs) {
			      return rhs.getDate().compareTo(lhs.getDate());
			  }
		});
	}
	
	// Marks posts without pictures as filtered
	public void filterOutNoPicture(){
		for (int i=0; i< mPosts.size(); i++) {
			Post item = mPosts.get(i);
			if (!item.hasPicture())
				item.setIsFiltered(true);
		}
	}
	
	// Marks questions as filtered
	public void filterOutQuestions(){
		for (int i=0; i< mPosts.size(); i++) {
			Post item = mPosts.get(i);
			if (isQuestion(item))
				item.setIsFiltered(true);
		}
	}
	
	// Marks answers as filtered
	public void filterOutAnswers(){
		for (int i=0; i< mPosts.size(); i++) {
			Post item = mPosts.get(i);
			if (!isQuestion(item))
				item.setIsFiltered(true);
		}
	}
	
	// Clears filters
	// -- Should be called upon opening the Search Dialog
	public void clearFilters(){
		for (int i=0; i< mPosts.size(); i++) {
			Post item = mPosts.get(i);
			item.setIsFiltered(false);
		}
	}
	
	//TODO: Implement in Project Part 4 with ElasticSearch
	public ArrayList<Post> keywordSearch(String keyword){
		ArrayList<Post> matchingPosts = new ArrayList<Post>();
		return matchingPosts;
	}
	
   public int getPositionOfAnswer(Question question, Answer answer) {
        return question.getPositionOfAnswer(answer.getID());
   }
}
