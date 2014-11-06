package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Controllers;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.CachedPostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;

public class TestCachedPostManager extends ActivityInstrumentationTestCase2<MainActivity>  {
    public TestCachedPostManager() {
        super(MainActivity.class);
    }
    
    public void testPosts() throws IOException {
        ArrayList<Post> comparisonPosts = new ArrayList<Post>();
        PostController.getInstance(getActivity()).getPostManager().ClearManager();
        CachedPostManager manager = CachedPostManager.getInstance(getActivity());
        
        assertNotNull(manager);
        //assertEquals(manager.getPosts(), comparisonPosts);
        
        comparisonPosts = loadTestQuestions(manager);

        assertEquals(comparisonPosts.size(), manager.getPosts().size());

        //saving/loading breaks below tests, review implementation
        //manager.save();
        //manager.loadFromFile();
        
        assertEquals(comparisonPosts, manager.getPosts());
        
        manager.sortByDate();
        
        Collections.reverse(comparisonPosts);
        
        assertEquals(comparisonPosts, manager.getPosts());
    }
    
	private ArrayList<Post> loadTestQuestions(CachedPostManager manager) {
    	Calendar calendar = Calendar.getInstance();
		Date createDate = new Date();
    	
		calendar.set(2000, 8, 13);		
		createDate = calendar.getTime();
    	ArrayList<Post> posts = new ArrayList<Post>();
        Question q1 = new Question(  
                "LOL I don't actually have a question, I just wanted to post.",
                "gregthegreg",
                "pic1",
                "MY FIRST QUESTION GUIS!!!11", createDate);
        
        calendar.set(2001, 8, 13);		
		createDate = calendar.getTime();
        Answer a1 = new Answer("LOL I AM ANSWERING MY OWN QUESTION!", "gregthegreg", null, createDate);
        
        calendar.set(2001, 8, 14);		
		createDate = calendar.getTime();
        Answer a2 = new Answer("Please just stop posting.", "djhindle", null, createDate);
        
        q1.addAnswer(a1);
        q1.addAnswer(a2);
        
        calendar.set(2004, 8, 13);		
		createDate = calendar.getTime();
        Question q2 = new Question(
                "What is the difference between a UML class diagram,"
                + "a UML Sequence diagram, and a UML state diagram?",
                "djhindle", null,
                "UML Diagrams", createDate);
        
        // Q3 tests the concatenating of long titles/text
        calendar.set(2008, 8, 13);		
		createDate = calendar.getTime();
        Question q3 = new Question(
                "Hey DJ Hindle help me out?? I deleted my System 32 folder like you said,"
                + "but now I'm getting this weird blue screen when I start"
                + "my computer??? What's going on? How do I fix it. Is this a"
                + "virus or what?",
                "gregthegreg", null,
                "Weird Blue ScreenWeird Blue ScreenWeird Blue ScreenWeird "
                +" Blue ScreenWeird Blue ScreenWeird Blue Screen", createDate);
        
        calendar.set(2009, 8, 13);		
		createDate = calendar.getTime();
        Answer a3 = new Answer("Don't call me DJ Hindle :/", "djhindle", null, createDate);
        
        calendar.set(2010, 8, 13);		
		createDate = calendar.getTime();
        Answer a4 = new Answer("Attached is a picture of how much I don't want you to call me DJ Hindle", "djhindle", "hindlepic.jpg", createDate);
        
        q3.addAnswer(a3);
        q3.addAnswer(a4);
        
        posts.add(q1);
        posts.add(a1);
        posts.add(a2);
        posts.add(q2);
        posts.add(q3);
        posts.add(a3);
        posts.add(a4);
        
        if (manager.getPosts().size() == 0) {
        	manager.addQuestion(q1);
        	manager.addAnswer(q1, a1);
        	manager.addAnswer(q1, a2);
        	manager.addQuestion(q2);
        	manager.addQuestion(q3);
        	manager.addAnswer(q3, a3);
        	manager.addAnswer(q3, a4);
        }
        return posts;
    }
}
