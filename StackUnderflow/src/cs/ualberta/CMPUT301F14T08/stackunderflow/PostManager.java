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
	protected ArrayList<Post> mQuestions;
	protected Context mContext;
	
	protected PostManager(Context context){
		mContext = context;
	}
	

	// Strips 'selected' from post objects.
	protected void clearSelected() {
		for (int i=0; i< mQuestions.size(); i++) {
			Post item = mQuestions.get(i);
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
	
    public Post getPost(UUID uuid) {
        for (int i=0; i< mQuestions.size(); i++) {
            Question question = (Question)mQuestions.get(i);
            
            if (question.getID().equals(uuid))
                return question;
            
            for (int j=0; j < question.getAnswers().size(); j++) {
                Answer answer = question.getAnswers().get(j);
                if (answer.getID().equals(uuid))
                    return answer;
            }
        }
        return null;
    }
    
    // Given a list of Questions returns a list of Posts
    // Used for loading.
    protected ArrayList<Post> castToPosts(ArrayList<Question> questions) {
        ArrayList<Post> posts = new ArrayList<Post>();

        for (int i=0; i<questions.size(); ++i) {
            
            Question item = questions.get(i);
            posts.add(item);
        }
        
        return posts;
    }
    
   /*protected boolean updateIfExists(Post post) {
       if (post == null) {
           return false;
       }
       
        for (int i=0; i< mQuestions.size(); i++) {
            Post item = mQuestions.get(i);
            if (item.getID() == post.getID()) {
                mQuestions.set(i, post);
                return true;
            }
        }
        return false;
    }*/

	public ArrayList<Post> getQuestions(){
		return mQuestions;
	}
	
	// adds a question to our list of posts
	public void addQuestion(Question newQuestion){
		mQuestions.add(newQuestion);
	}
	
	// adds an answer to our list of posts
	public void addAnswer(Question parent, Answer newAnswer){
		parent.addAnswer(newAnswer);
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
		Collections.sort(mQuestions, new Comparator<Post>() {
			  public int compare(Post lhs, Post rhs) {
			      return (Integer.valueOf(rhs.getVotes()).compareTo(Integer.valueOf(lhs.getVotes())));
			  }
		});
	}
	
	// Sorts posts by most recent date first (Descending Order)
	public void sortByDate(){
		Collections.sort(mQuestions, new Comparator<Post>() {
			  public int compare(Post lhs, Post rhs) {
			      return (Integer.valueOf(rhs.getTimeStamp()).compareTo(Integer.valueOf(lhs.getTimeStamp())));
			  }
		});
	}
	
	// Marks posts without pictures as filtered
	public void filterOutNoPicture(){
		for (int i=0; i< mQuestions.size(); i++) {
			Post item = mQuestions.get(i);
			if (!item.hasPicture())
				item.setIsFiltered(true);
		}
	}
	
	// Marks questions as filtered
	public void filterOutQuestions(){
		for (int i=0; i< mQuestions.size(); i++) {
			Post item = mQuestions.get(i);
			if (isQuestion(item))
				item.setIsFiltered(true);
		}
	}
	
	// Marks answers as filtered
	public void filterOutAnswers(){
		for (int i=0; i< mQuestions.size(); i++) {
			Post item = mQuestions.get(i);
			if (!isQuestion(item))
				item.setIsFiltered(true);
		}
	}
	
	// Clears filters
	// -- Should be called upon opening the Search Dialog
	public void clearFilters(){
		for (int i=0; i< mQuestions.size(); i++) {
			Post item = mQuestions.get(i);
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
