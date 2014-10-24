package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

import android.content.Context;

public abstract class PostManager {
	private PostManager sPostmanager;
	private ArrayList<Post> mPosts;

	private void PostManager(Context context){

	}
	public PostManager getInstance(Context context){
		return sPostmanager;
	}
	public boolean save(){
		return false;
		
	}
	
	public void addQuestion(Question newQuestion){
		
	}
	public void addAnswer(Answer newAnswer){
		
	}
	public void addReply(Reply newReply, Post Parent){
		
	}
	
	public ArrayList<Post> getPosts(){
		return mPosts;
	}
	
	public void sortByScore() {
		
	}
	public void sortByDate(){
		
	}
	public void filterOutNoPicture(){
		
	}
	public void filterOutQuestions(){
		
	}
	public void filterOutAnswers(){
		
	}
	
	public ArrayList<Question> findQuestions(String keyword){
		ArrayList<Question> foundQuestions = null;
		return foundQuestions;
	}


}
