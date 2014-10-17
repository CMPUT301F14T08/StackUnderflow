package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

/*
 * Simulates the controller saving a post a user has performed an action on.
 * Could change this to use the actual controller class later on to achieve
 * the same end goal, and test that functionality. Saves, then loads, then
 * sorts the loaded list, and compares it to expected output of the view
 * 
 * Expected viewable list: Newest Qs before older Qs.
 * 
 * Comments above each class will say what action is being performed. 
 * All methods inherit descriptions from this initial comment.
 * 
 */

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.QuestionList;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Sort;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;
import junit.framework.TestCase;

/*
 * Author: Jonathan Emery
 * Modified by: 
 */
public class TestViews extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public TestViews() {
		super(StackUnderflowActivity.class);
	}
	
	// Tests the Bookmark functionality, and list view that is displayed 
	public void testViewBookmarked() {
		QuestionList testList = new QuestionList();
		QuestionList bookList = new QuestionList();
		Question q1 = new Question();
		Question q2 = new Question();
		Sort sorter = new Sort();

		testList.addQuestion(q2);
		testList.addQuestion(q1);
		
		//some save function, change later
		Save.saveReadLater(q1);
		Save.saveReadLater(q2);
		
		//some load function, change later
		bookList = Load.loadReadLater();
		bookList = sorter.sortQuestionDate(bookList);
		
		assertEquals(bookList, testList);
	}
	
	// Tests the Favorite functionality, and list view that is displayed
	public void testViewFavorited() {
		QuestionList testList = new QuestionList();
		QuestionList favList = new QuestionList();
		Question q1 = new Question();
		Question q2 = new Question();
		Sort sorter = new Sort();

		favList.addQuestion(q2);
		favList.addQuestion(q1);
		
		//some save function, change later
		Save.saveFavorited(q1);
		Save.saveFavorited(q2);
		
		//some load function, change later
		favList = Load.Bookmarked();
		favList = sorter.sortQuestionDate(favList);
		
		assertEquals(favList, testList);
	}
	
	// Tests the Posted posts functionality, and list view that is displayed
	public void testViewPosted() {
		QuestionList testList = new QuestionList();
		QuestionList postedList = new QuestionList();
		Question q1 = new Question();
		Question q2 = new Question();
		Sort sorter = new Sort();

		testList.addQuestion(q2);
		testList.addQuestion(q1);
		
		//some save function, change later
		Save.savePosted(q1);
		Save.savePosted(q2);
		
		//some load function, change later
		postedList = Load.Posted();
		postedList = sorter.sortQuestionDate(postedList);
		
		assertEquals(postedList, testList);
	}
	
	
}
