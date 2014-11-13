/** These Tests Require Access to the Internet! */
package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Controllers;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activity.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.OnlinePostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;

public class TestOnlinePostManager extends ActivityInstrumentationTestCase2<MainActivity>  {

	public TestOnlinePostManager() {
		super(MainActivity.class);

	}
	
	/** Helper Functions **/
	
	// constructor is private, use reflection to access it here
	private OnlinePostManager getOnlinePostManager() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class[] args = {Context.class};
        Constructor<OnlinePostManager> constructor = OnlinePostManager.class.getDeclaredConstructor(args);
        constructor.setAccessible(true);
        return constructor.newInstance(getActivity().getApplicationContext());
	}
	
	// insertEsQuestion in OnlinePostManager is private, use reflection to access it here
    private String insertESQuestion(OnlinePostManager mPostManager, Question newQuestion) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class[] args = {Question.class};
        Method insertEsQuestionMethod = mPostManager.getClass().getDeclaredMethod("insertEsQuestion", args);
        insertEsQuestionMethod.setAccessible(true);
        Object insertEsQuestionReturn = insertEsQuestionMethod.invoke(mPostManager, newQuestion);
        return (String)insertEsQuestionReturn;
    }
    
    // loadFromServer in OnlinePostManager is private, use reflection to access it here
    private String loadFromServer(OnlinePostManager mPostManager) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method loadFromServerMethod = mPostManager.getClass().getDeclaredMethod("loadFromServer", null);
        loadFromServerMethod.setAccessible(true);
        Object loadFromServerReturn = loadFromServerMethod.invoke(mPostManager);
        return (String)loadFromServerReturn;
    }
    
    // getESQuestion in OnlinePostManager is private, use reflection to access it here
    private Question getESQuestion(OnlinePostManager mPostManager, Question question) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Class[] args = {Question.class};
        Method getESQuestionMethod = mPostManager.getClass().getDeclaredMethod("getESQuestion", args);
        getESQuestionMethod.setAccessible(true);
        Object getESQuestionReturn = getESQuestionMethod.invoke(mPostManager, question);
        return (Question)getESQuestionReturn;
    }
	
    // updateEsQuestionVotes is private, use reflection to access it here
    private String updateESQuestionVotes(OnlinePostManager mPostManager, Question existingQuestion, int voteChange) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class[] args = {Question.class, Integer.TYPE};
        Method updateEsQuestionVotesMethod = mPostManager.getClass().getDeclaredMethod("updateESQuestionVotes", args);
        updateEsQuestionVotesMethod.setAccessible(true);
        Object updateEsQuestionVotesReturn = updateEsQuestionVotesMethod.invoke(mPostManager, existingQuestion, voteChange);
        return (String)updateEsQuestionVotesReturn;
    }

    // updateESAnswer is private, use reflection to access it here
    private String updateESAnswer(OnlinePostManager mPostManager, Question existingQuestion) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class[] args = {Question.class};
        Method updateESAnswerMethod = mPostManager.getClass().getDeclaredMethod("updateESAnswer", args);
        updateESAnswerMethod.setAccessible(true);
        Object updateESAnswerReturn = updateESAnswerMethod.invoke(mPostManager, existingQuestion);
        return (String)updateESAnswerReturn;
    }
    
    // updateEsAnswerVotes is private, use reflection to access it here
    private String updateESAnswerVotes(OnlinePostManager mPostManager, Question existingQuestion, Answer existingAnswer, int voteChange) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class[] args = {Question.class, Answer.class, Integer.TYPE};
        Method updateESAnswerVotesMethod = mPostManager.getClass().getDeclaredMethod("updateESAnswerVotes", args);
        updateESAnswerVotesMethod.setAccessible(true);
        Object updateESAnswerVotesReturn = updateESAnswerVotesMethod.invoke(mPostManager, existingQuestion, existingAnswer, voteChange);
        return (String)updateESAnswerVotesReturn;
    }
    
    // addESAnswer is private, use reflection to access it here
    private String addESAnswer(OnlinePostManager mPostManager, Question existingQuestion, Answer existingAnswer) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class[] args = {Question.class, Answer.class};
        Method addESAnswerMethod = mPostManager.getClass().getDeclaredMethod("addESAnswer", args);
        addESAnswerMethod.setAccessible(true);
        Object addESAnswerReturn = addESAnswerMethod.invoke(mPostManager, existingQuestion, existingAnswer);
        return (String)addESAnswerReturn;
    }

    // our current implementation has no deletion, but we want to delete our test questions
	private String deleteQuestion(Question question) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpDelete deleteRequest = new HttpDelete("http://cmput301.softwareprocess.es:8080/cmput301f14t08/question/" + question.getID());
        String status = null;
        
        try {
            deleteRequest.setHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(deleteRequest);
            status = response.getStatusLine().toString();
        
        } catch (Exception e) {
            status = "Exception: " + e.getMessage();
                }
                
            return status;
	}
	
    /** Test Functions **/
	
	// TEST: adding a question to the ES server
	public void testAddQuestion() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        OnlinePostManager mPostManager = getOnlinePostManager();
           
        Question newQuestion = new Question("This is my question.","Author", "My Title");
        Answer newAnswer = new Answer("User","Author");
        newQuestion.addAnswer(newAnswer);
        
        String insertionResult = insertESQuestion(mPostManager, newQuestion);

        // delete the test question from the online database
        String deletionResult = deleteQuestion(newQuestion);
        
        // check for successful creation
        assertEquals("HTTP/1.1 201 Created", insertionResult);
        // check for successful deletion
        assertEquals("HTTP/1.1 200 OK", deletionResult);
    }
   
	// TEST: retrieving questions from ES server
	public void testLoadQuestions() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InterruptedException, InstantiationException {

        // constructor is private, use reflection to test it
        OnlinePostManager mPostManager = getOnlinePostManager();
        
        // insert a question
        Question newQuestion = new Question("This is my question.","Author", "My Title");
        String insertionResult = insertESQuestion(mPostManager, newQuestion);
          
        // give ES some time to save the question before retrieving it
        Thread.sleep(1000);
          
        // load the posts from the server
        String loadResult = loadFromServer(mPostManager);
          
        // delete the test question from the online database
        String deleteResult = deleteQuestion(newQuestion);
        
        // check for successful creation
        assertEquals("HTTP/1.1 201 Created", insertionResult);
        // check for successful get request
        assertEquals("HTTP/1.1 200 OK", loadResult);
        // check for successful deletion
        assertEquals("HTTP/1.1 200 OK", deleteResult);
          
        // check that our list contains our question
        assertFalse(mPostManager.getQuestions().size() == 0);
        assertNotNull((mPostManager.getPost(newQuestion.getID())));
   }
   
	// TEST: retrieving a single question from ES server
    public void testRetrieveQuestion() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InterruptedException, InstantiationException {

        // constructor is private, use reflection to test it
        OnlinePostManager mPostManager = getOnlinePostManager();
        
        // insert a question
        Question newQuestion = new Question("This is my question.","Author", "My Title");
        String insertionResult = insertESQuestion(mPostManager, newQuestion);
          
        // give ES some time to save the question before retrieving it
        Thread.sleep(1000);
          
        // retrieve the question
        Question onlineQuestion = getESQuestion(mPostManager, newQuestion);
          
        // delete the test question from the online database
        String deleteResult = deleteQuestion(newQuestion);
        
        // check for successful creation
        assertEquals("HTTP/1.1 201 Created", insertionResult);
        // check for successful deletion
        assertEquals("HTTP/1.1 200 OK", deleteResult);
        // check that we retrieved a question
        assertNotNull(onlineQuestion);
        // check that we retrieved the appropriate question
        assertEquals(newQuestion.getID(), onlineQuestion.getID());
        
        // check that when retrieving a non-uploaded question we retrieve nothing
        Question newQuestion2 = new Question("This is my question.","Author", "My Title");
        onlineQuestion = getESQuestion(mPostManager, newQuestion2);
        assertNull(onlineQuestion);
   }
   
    // TEST: updating a single questions votes
    public void testUpdateQuestionVotes() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InterruptedException, InstantiationException {

        // constructor is private, use reflection to test it
        OnlinePostManager mPostManager = getOnlinePostManager();
        
        // insert two questions
        Question newQuestion = new Question("This is my question.","Author", "My Title");
        Question newQuestion2 = new Question("This is my question.","Author", "My Title");
        newQuestion2.incrementVotes();
        String insertionResult = insertESQuestion(mPostManager, newQuestion);
        String insertionResult2 = insertESQuestion(mPostManager, newQuestion2);
          
        // give ES some time to save the questions before updating them
        Thread.sleep(1000);
        
        // update the question votes
        String updateResult = updateESQuestionVotes(mPostManager, newQuestion, 1);
        String updateResult2 = updateESQuestionVotes(mPostManager, newQuestion2, -1);
        
        // give ES some time to apply the updates before retrieving the questions
        Thread.sleep(1000);
        
        // retrieve the question
        Question onlineQuestion = getESQuestion(mPostManager, newQuestion);
        Question onlineQuestion2 = getESQuestion(mPostManager, newQuestion2);
          
        // delete the test question from the online database
        String deleteResult = deleteQuestion(newQuestion);
        String deleteResult2 = deleteQuestion(newQuestion2);
        
        // check for successful creation
        assertEquals("HTTP/1.1 201 Created", insertionResult);
        assertEquals("HTTP/1.1 201 Created", insertionResult2);
        // check for successful update
        assertEquals("HTTP/1.1 200 OK", updateResult);
        assertEquals("HTTP/1.1 200 OK", updateResult2);
        // check for successful deletion
        assertEquals("HTTP/1.1 200 OK", deleteResult);
        assertEquals("HTTP/1.1 200 OK", deleteResult2);
        // check for successful get request
        assertNotNull(onlineQuestion);
        assertNotNull(onlineQuestion2);
        assertEquals(newQuestion.getID(), onlineQuestion.getID());
        assertEquals(newQuestion2.getID(), onlineQuestion2.getID());
        // check that our onlineQuestion has one more upvote
        assertEquals(newQuestion.getVotes()+1, onlineQuestion.getVotes());
        assertEquals(newQuestion2.getVotes()-1, onlineQuestion2.getVotes());
   }
    
    // TEST: updating a question's answers
    public void testUpdateAnswer() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InterruptedException, InstantiationException {

        // constructor is private, use reflection to test it
        OnlinePostManager mPostManager = getOnlinePostManager();
        
        // insert question
        Question newQuestion = new Question("This is my question.","Author", "My Title");
        String insertionResult = insertESQuestion(mPostManager, newQuestion);
          
        // give ES some time to save the question before updating it
        Thread.sleep(1000);
        
        // add some answers
        Answer newAnswer = new Answer("User","Author");
        newQuestion.addAnswer(newAnswer);
        String updateResult = updateESAnswer(mPostManager, newQuestion);
        
        // give ES some time to apply the updates before retrieving the questions
        Thread.sleep(1000);
        
        // retrieve the question
        Question onlineQuestion = getESQuestion(mPostManager, newQuestion);
          
        // delete the test question from the online database
        String deleteResult = deleteQuestion(newQuestion);
        
        // check for successful creation
        assertEquals("HTTP/1.1 201 Created", insertionResult);
        // check for successful update
        assertEquals("HTTP/1.1 200 OK", updateResult);
        // check for successful deletion
        assertEquals("HTTP/1.1 200 OK", deleteResult);
        // check for successful get request
        assertNotNull(onlineQuestion);
        assertEquals(newQuestion.getID(), onlineQuestion.getID());
        // check that our onlineQuestion has the newly added answer
        assertNotNull(onlineQuestion.getAnswer(newAnswer.getID()));
   }
    
    public void testUpdateAnswerVotes() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InterruptedException, InstantiationException {

        // constructor is private, use reflection to test it
        OnlinePostManager mPostManager = getOnlinePostManager();
        
        // make a question with some answers and insert it
        Question newQuestion = new Question("This is my question.","Author", "My Title");
        Answer answer1 = new Answer("Answer1","Author");
        Answer answer2 = new Answer("Answer2","Author");
        answer2.setVotes(1);
        newQuestion.addAnswer(answer1);
        newQuestion.addAnswer(answer2);
        String insertionResult = insertESQuestion(mPostManager, newQuestion);
          
        // give ES some time to save the question before updating it
        Thread.sleep(1000);
        
        // add some answers
        String updateResult = updateESAnswerVotes(mPostManager, newQuestion, answer1, 1);
        String updateResult2 = updateESAnswerVotes(mPostManager, newQuestion, answer2, -1);
        
        // give ES some time to apply the updates before retrieving the questions
        Thread.sleep(1000);
        
        // retrieve the question
        Question onlineQuestion = getESQuestion(mPostManager, newQuestion);
          
        // delete the test question from the online database
        String deleteResult = deleteQuestion(newQuestion);
        
        // check for successful creation
        assertEquals("HTTP/1.1 201 Created", insertionResult);
        // check for successful update
        assertEquals("HTTP/1.1 200 OK", updateResult);
        assertEquals("HTTP/1.1 200 OK", updateResult2);
        // check for successful deletion
        assertEquals("HTTP/1.1 200 OK", deleteResult);
        // check for successful get request
        assertNotNull(onlineQuestion);
        assertEquals(newQuestion.getID(), onlineQuestion.getID());
        
        // check that our onlineQuestion has the newly added answer
        Answer onlineAnswer1 = onlineQuestion.getAnswer(answer1.getID());
        Answer onlineAnswer2 = onlineQuestion.getAnswer(answer2.getID());
        assertNotNull(onlineAnswer1);
        assertNotNull(onlineAnswer2);
        //check that our onlineAnswers have the desired upvotes
        assertEquals(answer1.getVotes()+1, onlineAnswer1.getVotes());
        assertEquals(answer2.getVotes()-1, onlineAnswer2.getVotes());
   }
    
    // TEST: updating a question's answers
    public void testAddingAnswer() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InterruptedException, InstantiationException {

        // constructor is private, use reflection to test it
        OnlinePostManager mPostManager = getOnlinePostManager();
        
        // insert question
        Question newQuestion = new Question("This is my question.","Author", "My Title");
        String insertionResult = insertESQuestion(mPostManager, newQuestion);
          
        // give ES some time to save the question before updating it
        Thread.sleep(1000);
        
        // add some answers
        Answer newAnswer = new Answer("User","Author");
        newQuestion.addAnswer(newAnswer);
        String updateResult = addESAnswer(mPostManager, newQuestion, newAnswer);
        
        // give ES some time to apply the updates before retrieving the questions
        Thread.sleep(1000);
        
        // retrieve the question
        Question onlineQuestion = getESQuestion(mPostManager, newQuestion);
          
        // delete the test question from the online database
        String deleteResult = deleteQuestion(newQuestion);
        
        // check for successful creation
        assertEquals("HTTP/1.1 201 Created", insertionResult);
        // check for successful update
        assertEquals("HTTP/1.1 200 OK", updateResult);
        // check for successful deletion
        assertEquals("HTTP/1.1 200 OK", deleteResult);
        // check for successful get request
        assertNotNull(onlineQuestion);
        assertEquals(newQuestion.getID(), onlineQuestion.getID());
        // check that our onlineQuestion has the newly added answer
        assertNotNull(onlineQuestion.getAnswer(newAnswer.getID()));
        // check that we've only added one answer
        assertTrue(onlineQuestion.getAnswers().size() == 1);
   }
}
