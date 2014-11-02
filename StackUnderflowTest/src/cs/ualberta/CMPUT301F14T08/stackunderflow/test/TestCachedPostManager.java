package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.CachedPostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;

public class TestCachedPostManager extends ActivityInstrumentationTestCase2<MainActivity>  {
    public TestCachedPostManager() {
        super(MainActivity.class);
    }
    
    private void loadTestQuestions(ArrayList<Post> posts) {
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
        Answer a4 = new Answer("Attached is a picture of how much I don't want you to call me DJ Hindle", "djhindle", "hindlepic.jpg");
        
        q3.addAnswer(a3);
        q3.addAnswer(a4);
        
        posts.add(q1);
        posts.add(a1);
        posts.add(a2);
        posts.add(q2);
        posts.add(q3);
        posts.add(a3);
        posts.add(a4);
    }
    
    public void testPosts() throws IOException {
        ArrayList<Post> comparisonPosts = new ArrayList<Post>();
        CachedPostManager manager = CachedPostManager.getInstance(getActivity());
        
        assertNotNull(manager);
        ArrayList<Post> posts = manager.getPosts();
        posts.clear();
        
        assertEquals(manager.getPosts(), comparisonPosts);
        
        loadTestQuestions(posts);
        loadTestQuestions(comparisonPosts);
        assertEquals(manager.getPosts().size(), comparisonPosts.size());

        manager.save();
        manager.loadFromFile();
        
        assertEquals(manager.getPosts().size(), comparisonPosts.size());
        }
}
