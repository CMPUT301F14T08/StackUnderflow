package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

/*
 * Test class to test some basic functionality about what we expect to happen
 * when viewing a new post, or a series of new posts. Tests the save, load, and
 * sort functionality again, in sequential order. 
 * 
 * 
 * Future work: Potentially test the controller functionality in here
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
public class TestReadPost extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public TestReadPost() {
		super(StackUnderflowActivity.class);
	}
	
	/*
	 * Simulates the controller archiving a post a user has read.
	 * Could change this to use the actual controller class later on to achieve
	 * the same end goal, and test that functionality. Saves, then loads, then
	 * sorts the loaded list, and compares it to expected output of the view
	 * 
	 * Expected viewable list: Newest Qs before older Qs.
	 * 
	 */
	public void TestReadPostArchive() {
		QuestionList testList = new QuestionList();
		QuestionList archiveList = new QuestionList();
		Sort sorter = new Sort();
		
		Question q1 = new Question();
		Question q2 = new Question();

		testList.addQuestion(q2);
		testList.addQuestion(q1);
				
		//some save function, change later
		Save.saveArchive(q1);
		Save.saveArchive(q2);
		
		//some load function, change later
		archiveList = Load.loadArchive();
		
		archiveList = sorter.sortQuestionDate(archiveList);
		
		assertEquals(archiveList, testList);
	}

}
