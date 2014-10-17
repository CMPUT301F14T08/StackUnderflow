package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import java.util.ArrayList;
import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.QuestionList;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;
import junit.framework.TestCase;

/*
 * Author: Jonathan Emery
 * Modified by: 
 */
public class TestSave extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public TestSave() {
		super(StackUnderflowActivity.class);
	}
	
	/*
	 * Tests the ability to save a user profile after various changes to the
	 * data. Will fail if a save is not pushed after a change is made. 
	 * 
	 * Whenever a change is made, the user profile controller should be
	 * notified and thus the new data is saved. This will test this aspect
	 * and fail if it doesn't work properly
	 * 
	 * Uses a generic name for the loading for now
	 */
	public void TestSaveUserProfile() {
		Load loader = new Loader();
		UserProfile user = new UserProfile();
		UserProfile verifyUser = new UserProfile();
		
		user.setUsername("Jon");
		verifyUser = loader.loadUser();
		assertEquals(verifyUser, user);
		
		user.incrementQuestionPostedCount();
		verifyUser = loader.loadUser();
		assertEquals(verifyUser, user);
		
		user.incrementAnswersPostedCount();
		verifyUser = loader.loadUser();
		assertEquals(verifyUser, user);
	}
	
	/*
	 * Tests the ability to save a post that a user has indicated they would
	 * like to read later, does this twice to ensure the list is returned
	 * in the order in which they saved them. IE. older saves first, compared
	 * to more recent "read later" posts.
	 * 
	 * Uses a generic name for the saving/loading for now **UPDATE LATER**
	 */
	public void TestSaveReadLater() {
		Save saver = new Save();
		Load loader = new Loader();
		QuestionList readLater = new QuestionList();
		ArrayList<Question> testLoaded = new ArrayList<Question>();
		
		Question q1 = new Question("Test1", "Test1");
		saver.saveReadLater(q1);
		
		Question q2 = new Question("Test2", "Test2");
		saver.saveReadLater(q2);

		readLater.addQuestion(q1);
		readLater.addQuestion(q2);
		
		testLoaded = loader.loadReadLater();
		
		assertEquals(readLater, testLoaded);		
	}
	
	

}
