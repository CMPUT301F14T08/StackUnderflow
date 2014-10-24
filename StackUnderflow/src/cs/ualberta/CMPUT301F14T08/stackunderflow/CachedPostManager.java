package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

import android.content.Context;

public class CachedPostManager extends PostManager{
	private String ARCHIVE_FILE;
	
	private void sendToFile(){
		
	}
	private void loadFromFile(){
		
	}
	
	public CachedPostManager getInstance(Context context){
		CachedPostManager cachedPostManager = null;
		return cachedPostManager;
	}
	public boolean save(){
		return false;
	}
	public CachedPostManager pushToOnline(Context context){
		CachedPostManager onlinePostManager = null;
		return onlinePostManager;
	}
	
	public void addQuestion(Question newQuestion){
		
	}
	public void addAnswer(Answer newAnswer){
		
	}
	public void addReply(Reply newReply, Post parent){
		
	}
	
}
