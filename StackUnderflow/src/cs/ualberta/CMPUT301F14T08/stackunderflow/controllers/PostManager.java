


package cs.ualberta.CMPUT301F14T08.stackunderflow.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import cs.ualberta.CMPUT301F14T08.stackunderflow.exception.InvalidPostTypeException;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Reply;

import android.content.Context;
/**
 * This class manages the different the posts into an array list of objects and allows functionality within that array list
 * @author Cmput301 Winter 2014 Group 8
 */
public abstract class PostManager {
	protected ArrayList<Post> mQuestions;
	protected Context mContext;
	
	protected PostManager(Context context){
		mContext = context;
		mQuestions = new ArrayList<Post>();
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
	
	// given a UUID returns the corresponding post object (question or answer)
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
    
    // given a question or answer UUID returns the
    // corresponding question object (ex. for an answer the parent question)
    public Question getQuestion(UUID uuid) {
        for (int i=0; i< mQuestions.size(); i++) {
            Question question = (Question)mQuestions.get(i);
            
            if (question.getID().equals(uuid))
                return question;
            
            for (int j=0; j < question.getAnswers().size(); j++) {
                Answer answer = question.getAnswers().get(j);
                if (answer.getID().equals(uuid))
                    return question;
            }
        }
        return null;
    }
    
   protected boolean updateIfExists(Post post) {
       if (post == null) {
           return false;
       }
       
        for (int i=0; i< mQuestions.size(); i++) {
            Question question = (Question)mQuestions.get(i);
            
            
            if (question.getID().equals(post.getID())) {
                mQuestions.set(i, post);
                return true;
            }
            
            for (int j=0; j < question.getAnswers().size(); j++) {
                Answer answer = question.getAnswers().get(j);
                if (answer.getID().equals(post.getID())) {
                    question.getAnswers().set(i, (Answer)post);
                    return true;
                }
            }
        }
        return false;
    }
   
   // Given a list of Questions returns a list of Posts
   // Used for loading.
   protected ArrayList<Post> castToPosts(ArrayList<Question> questions) {
       ArrayList<Post> posts = new ArrayList<Post>();

       for (int i=0; i<questions.size(); ++i) {
           posts.add((Post)questions.get(i));
       }
       
       return posts;
   }

	public ArrayList<Post> getQuestions(){
		return mQuestions;
	}
	
	public ArrayList<Post> getUnFilteredPosts(){
		ArrayList<Post> list = new ArrayList<Post>();
		for (int i = 0; i < mQuestions.size(); i++){
			Post p = mQuestions.get(i);
			if (!p.getIsFiltered())
				list.add(p);
		}
		return list;
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
	    post.getUserAttributes().toggleIsFavorited();
	}
	
	//TODO: Update with implementation of user attributes
	// For now this will just increment votes
	public void toggleUpvote(Post post) {
	    post.getUserAttributes().toggleIsUpvoted();
	    if (post.getUserAttributes().getIsUpvoted())
	        post.incrementVotes();
	    else
	        post.decrementVotes();
	}
	

	//TODO: Update with implementation of user attributes
	public void toggleReadLater(Post post) {
	    post.getUserAttributes().setIsReadLater(true);
	}
	
	// Sorts posts by number of votes (Descending Order)
	public void sortByScore() {
		Collections.sort(mQuestions, new Comparator<Post>() {
			  public int compare(Post lhs, Post rhs) {
			      return (Long.valueOf(rhs.getVotes()).compareTo(Long.valueOf(lhs.getVotes())));
			  }
		});
	}
	
	// Sorts posts by most recent date first (Descending Order)
	public void sortByDate(){
		Collections.sort(mQuestions, new Comparator<Post>() {
			  public int compare(Post lhs, Post rhs) {
				  return rhs.getDate().compareTo(lhs.getDate());
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
