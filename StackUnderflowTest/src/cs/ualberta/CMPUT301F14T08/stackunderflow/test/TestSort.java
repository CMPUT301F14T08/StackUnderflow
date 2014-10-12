package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

/*
 * Author: Jonathan Emery
 * Modified by: 
 * 
 * Test class for sorting based on various conditions & expected results.
 * Each test calls the corresponding method in Sort.class located in 
 * StackUnderflow. Makes use of hard-coded sorted lists to compare the
 * returned output, to ensure the proper sorting is happening before
 * the method returns a value.
 * 
 * Additional details above each method.
 * 
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.*;
import junit.framework.TestCase;

public class TestSort extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {

	public TestSort() {
		super(StackUnderflowActivity.class);
	}
	
	/*
	 * Tests the ability to sort based on question date, in descending order
	 * using the method sortQuestionDate(QuestionList questionList).
	 * 
	 * Expected return from the method is in the form of a sorted QuestionList
	 * 
	 */
	public void testSortQuestionDate(QuestionList dateList) {
		Calendar calendar = Calendar.getInstance();
		Date createDate = new Date();
		QuestionList dateQLS = new QuestionList();
		ArrayList<Question> dateQL = new ArrayList<Question>();
		Sort sorter = new Sort();
		
		calendar.set(2000, 8, 13);		
		createDate = calendar.getTime();		
		Question q1 = new Question("Test1", "Test1", createDate);
		
		calendar.set(2010, 5, 20);
		createDate = calendar.getTime();
		Question q2 = new Question("Test2", "Test2", createDate);
		
		calendar.set(2000, 8, 12);
		createDate = calendar.getTime();
		Question q3 = new Question("Test3", "Test3", createDate);
		
		calendar.set(2012, 0, 15);
		createDate = calendar.getTime();
		Question q4 = new Question("Test4", "Test4", createDate);
		
		dateQLS.addQuestion(q1);
		dateQLS.addQuestion(q2);
		dateQLS.addQuestion(q3);
		dateQLS.addQuestion(q4);
		
		dateQLS = sorter.sortQuestionDate(dateQLS);
		
		dateQL.add(q4);
		dateQL.add(q2);
		dateQL.add(q1);
		dateQL.add(q3);
		
		assertEquals(dateQL, dateQLS);
	}
	
	/*
	 * Tests the ability to sort based on answer date, in descending order
	 * using the method sortQuestionDate(QuestionList questionList).
	 * 
	 * Expected return from the method is in the form of a sorted 
	 * ArrayList<Answer>
	 * 
	 * =====Maybe incorporate an AnswerListClass?
	 * =====Check to ensure this is the desired behavior..
	 * 
	 */
	public void testSortAnswerDate(QuestionList dateList) {
		Calendar calendar = Calendar.getInstance();
		Date createDate = new Date();
		QuestionList dateQLS = new QuestionList();
		ArrayList<Answer> retList= new ArrayList<Answer>();
		ArrayList<Answer> dateAL = new ArrayList<Answer>();
		Sort sorter = new Sort();
		
		calendar.set(2000, 8, 13);		
		createDate = calendar.getTime();		
		Question q1 = new Question("Test1", "Test1", createDate);
		
		calendar.set(2010, 5, 20);
		createDate = calendar.getTime();
		Question q2 = new Question("Test2", "Test2", createDate);
		
		calendar.set(2000, 8, 12);
		createDate = calendar.getTime();
		Answer a1 = new Answer();
		a1.setmDate(createDate);
		q1.addAnswer(a1);
		
		calendar.set(2012, 0, 15);
		createDate = calendar.getTime();
		Answer a2 = new Answer();
		a2.setmDate(createDate);
		q1.addAnswer(a2);
		
		calendar.set(2014, 2, 15);
		createDate = calendar.getTime();
		Answer a3 = new Answer();
		a3.setmDate(createDate);
		q2.addAnswer(a3);
		
		calendar.set(1999, 3, 15);
		createDate = calendar.getTime();
		Answer a4 = new Answer();
		a4.setmDate(createDate);
		q2.addAnswer(a4);

		dateQLS.addQuestion(q1);
		dateQLS.addQuestion(q2);		
		retList = sorter.sortAnswerDate(dateQLS);
		
		dateAL.add(a3);
		dateAL.add(a2);
		dateAL.add(a1);
		dateAL.add(a4);
		
		assertEquals(dateAL, dateQLS);
	}
	
	/*
	 * Tests the ability to sort based on Question OR Answer date, 
	 * in descending order using the method 
	 * sortQuestAnsDate(QuestionList questionList).
	 * 
	 * Expected return from the method is in the form of a sorted 
	 * ArrayList<Post>
	 * 
	 */
	public void testSortQuestAnsDate(QuestionList dateList) {
		Calendar calendar = Calendar.getInstance();
		Date createDate = new Date();
		QuestionList dateQLS = new QuestionList();
		ArrayList<Post> datePL = new ArrayList<Post>();
		ArrayList<Post> sortedList = new ArrayList<Post>();
		Sort sorter = new Sort();
		
		calendar.set(2000, 8, 13);		
		createDate = calendar.getTime();		
		Question q1 = new Question("Test1", "Test1", createDate);
		
		calendar.set(2010, 5, 20);
		createDate = calendar.getTime();
		Question q2 = new Question("Test2", "Test2", createDate);
		
		calendar.set(2014, 9, 20);
		createDate = calendar.getTime();
		Question q3 = new Question("Test2", "Test2", createDate);
		
		calendar.set(2000, 8, 12);
		createDate = calendar.getTime();
		Answer a1 = new Answer();
		a1.setmDate(createDate);
		q1.addAnswer(a1);
		
		calendar.set(2012, 0, 15);
		createDate = calendar.getTime();
		Answer a2 = new Answer();
		a2.setmDate(createDate);
		q1.addAnswer(a2);
		
		calendar.set(2014, 2, 15);
		createDate = calendar.getTime();
		Answer a3 = new Answer();
		a3.setmDate(createDate);
		q2.addAnswer(a3);
		
		calendar.set(1999, 3, 15);
		createDate = calendar.getTime();
		Answer a4 = new Answer();
		a4.setmDate(createDate);
		q2.addAnswer(a4);

		dateQLS.addQuestion(q1);
		dateQLS.addQuestion(q2);		
		dateQLS.addQuestion(q3);
		sortedList = sorter.sortQuestAnsDate(dateQLS);
		
		datePL.add(q3);
		datePL.add(a3);
		datePL.add(a2);
		datePL.add(q2);
		datePL.add(q1);
		datePL.add(a1);
		datePL.add(a4);
		
		assertEquals(datePL, dateQLS);
	}
	
	/*
	 * Tests the ability to sort based on Question date, in descending
	 * order using the method sortQuestionVotes(QuestionList questionList).
	 * 
	 * Expected return from the method is in the form of a sorted 
	 * QuestionList
	 * 
	 */
	public void testSortQuestionVotes(QuestionList dateList) {
		QuestionList voteQLS = new QuestionList();
		ArrayList<Question> voteQL = new ArrayList<Question>();
		Sort sorter = new Sort();
		
		Question q1 = new Question("Test1", "Test1");
		q1.setmUpvotes(1);
		Question q2 = new Question("Test2", "Test2");
		q2.setmUpvotes(5);
		Question q3 = new Question("Test3", "Test3");
		q3.setmUpvotes(3);
		Question q4 = new Question("Test4", "Test4");
		q4.setmUpvotes(9);
		
		voteQLS.addQuestion(q1);
		voteQLS.addQuestion(q2);
		voteQLS.addQuestion(q3);
		voteQLS.addQuestion(q4);
		
		voteQLS = sorter.sortQuestionVotes(voteQLS);
		
		voteQL.add(q4);
		voteQL.add(q2);
		voteQL.add(q3);
		voteQL.add(q1);
		
		assertEquals(voteQL, voteQLS);
	}
	
	/*
	 * Tests the ability to sort based on Answer date, in descending
	 * order using the method sortAnswerVotes(QuestionList questionList).
	 * 
	 * Expected return from the method is in the form of a sorted 
	 * ArrayList<Answer>
	 * 
	 */
	public void testSortAnswerVotes(QuestionList dateList) {
		QuestionList voteALS = new QuestionList();
		ArrayList<Answer> voteAL = new ArrayList<Answer>();
		ArrayList<Answer> retList = new ArrayList<Answer>();
		Sort sorter = new Sort();
		
		Question q1 = new Question("Test1", "Test1");
		Question q2 = new Question("Test2", "Test2");
		
		Answer a1 = new Answer();
		a1.setmUpvotes(14);		
		Answer a2 = new Answer();
		a2.setmUpvotes(20);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		
		Answer a3 = new Answer();
		a3.setmUpvotes(1);
		Answer a4 = new Answer();
		a4.setmUpvotes(5);
		
		q2.addAnswer(a3);
		q2.addAnswer(a4);
		
		voteALS.addQuestion(q1);
		voteALS.addQuestion(q2);
		
		retList = sorter.sortAnswerVotes(voteALS);
		
		voteAL.add(a2);
		voteAL.add(a1);
		voteAL.add(a4);
		voteAL.add(a3);
		
		assertEquals(voteAL, voteALS);
	}
	
	/*
	 * Tests the ability to sort based on Question OR Answer date, 
	 * in descending order using the method 
	 * sortQuestAnsVotes(QuestionList questionList).
	 * 
	 * Expected return from the method is in the form of a sorted 
	 * ArrayList<Post>
	 * 
	 */
	public void testSortQuestAnsVotes(QuestionList dateList) {
		QuestionList voteALS = new QuestionList();
		ArrayList<Post> voteQAL = new ArrayList<Post>();
		ArrayList<Post> retList = new ArrayList<Post>();
		Sort sorter = new Sort();
		
		Question q1 = new Question("Test1", "Test1");
		q1.setmUpvotes(3);
		Question q2 = new Question("Test2", "Test2");
		q2.setmUpvotes(40);
		Question q3 = new Question("Test3", "Test3");
		q3.setmUpvotes(0);
		
		Answer a1 = new Answer();
		a1.setmUpvotes(14);		
		Answer a2 = new Answer();
		a2.setmUpvotes(20);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		
		Answer a3 = new Answer();
		a3.setmUpvotes(1);
		Answer a4 = new Answer();
		a4.setmUpvotes(5);
		
		q2.addAnswer(a3);
		q2.addAnswer(a4);
		
		voteALS.addQuestion(q1);
		voteALS.addQuestion(q2);
		
		retList = sorter.sortQuestAnsVotes(voteALS);
		
		voteQAL.add(q2);
		voteQAL.add(a2);
		voteQAL.add(a1);
		voteQAL.add(a4);
		voteQAL.add(q1);
		voteQAL.add(a3);
		voteQAL.add(q3);
		
		assertEquals(voteQAL, voteALS);
	}
}
