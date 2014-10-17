package cs.ualberta.CMPUT301F14T08.stackunderflow.test;

import java.util.ArrayList;
import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.QuestionList;
import cs.ualberta.CMPUT301F14T08.stackunderflow.StackUnderflowActivity;
import junit.framework.TestCase;

/***
 * 
 * Class created for testing searching methods
 * Search may be for questions, answers, or posts containing pictures
 * 
 *  
 * @Author: Michael Williams
 * Modified by: 
 */
public class TestSearch extends ActivityInstrumentationTestCase2<StackUnderflowActivity> {
	public TestSearch() {
		super(StackUnderflowActivity.class);
	}
	
	/**
	 *  Test search for questions containing search string
	 *
	 */
	public void TestSearchQuestions() {
		QuestionList qlist = new QuestionList();
		
		Question q1 = new Question();
		Question q2= new Question();
		Question q3= new Question();
	
		String text1 = "question 1 aaaaa";
		String text2 = "question 2 bbbbb";
		String text3 = "question 3 aaaaa";
	
		q1.setText(text1);
		q2.setText(text2);
		q2.setText(text3);
		
		qlist.addQuestion(q1);
		qlist.addQuestion(q2);
		qlist.addQuestion(q3);
		
		// Search string
		String search = "aaaaa";
		
		// ArrayList listview simulates UI ListView adapter
		ArrayList<Question> listview = new ArrayList<Question>();
		// Populate listview with questions containing search string
		for (Question q: qlist.getQuestions()){
			if (q.getText().contains(search)) {
				listview.add(q);
			}
		}
		
		assertTrue(listview.contains(q1));
		assertTrue(listview.contains(q3));
		assertFalse(listview.contains(q2)); // question 2 does not contain search string
		
	}
	
	/**
	 *  Test search for answers containing search string
	 *
	 */
	public void TestSearchAnswers() {
		
		QuestionList qlist = new QuestionList();
		
		Question q1 = new Question();
		Question q2= new Question();
	
		String text1 = "question 1 aaaaa";
		String text2 = "question 2 bbbbb";
		
		q1.setText(text1);
		q2.setText(text2);

		Answer a1 = new Answer();
		Answer a2 = new Answer();
		Answer a3 = new Answer();
		String atext1 = "answer 1 ccccc";
		String atext2 = "answer 2 ddddd";
		String atext3 = "answer 3 ccccc";
		a1.setText(atext1);
		a2.setText(atext2);
		a3.setText(atext3);

		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q2.addAnswer(a3);
		
		qlist.addQuestion(q1);
		qlist.addQuestion(q2);
		
		String search = "ccccc";
		
		// ArrayList listview simulates UI ListView adapter
		ArrayList<Answer> listview = new ArrayList<Answer>();
		// Populate listview with answers containing search string
		for (Question q: qlist.getQuestions()){
			for(Answer a: q.getAnswers()){
				if (a.getText().contains(search)) {
					listview.add(a);
				}
			}
		}
		
		assertTrue(listview.contains(a1));
		assertTrue(listview.contains(a3));
		assertFalse(listview.contains(a2)); // answer 2 (question 1) does not contain search string
		
	}

	/**
	 *  Test search/filter for posts containing images
	 *
	 */
	public void TestSearchPictures() {
		
		QuestionList qlist = new QuestionList();
		
		Question q1 = new Question();
		Question q2= new Question();
		Question q3= new Question();
	
		String text1 = "question 1 aaaaa";
		String text2 = "question 2 bbbbb";
		String text3 = "question 3 aaaaa";
		String image1 = "image placeholder 1 aaaaa"; //string as placeholder for image format	
		String image2 = "image placeholder 1 bbbbb"; 
		
		q1.setText(text1);
		q1.setPicture(image1);
		q2.setText(text2);
		q2.setPicture(image2);
		q3.setText(text3);
		q3.setPicture(null); // question 3 does not have an image
		
		Answer a1 = new Answer();
		Answer a2 = new Answer();
		String atext1 = "answer 1 ccccc";
		String atext2 = "answer 2 ddddd";
		String aimage1 = "image placeholder 1 ccccc";

		a1.setText(atext1);
		a1.setPicture(image1);
		a2.setText(atext2);
		a2.setPicture(null); // answer 2 (question 2) does not have an image

		q1.addAnswer(a1);
		q2.addAnswer(a2);
		
		qlist.addQuestion(q1);
		qlist.addQuestion(q2);
		qlist.addQuestion(q3);
		
		// ArrayList listview simulates UI ListView adapter
		ArrayList<Post> listview = new ArrayList<Post>();
		// Add questions and answers with images (ie all posts with images) to listview 
		for (Question q: qlist.getQuestions()){
			if (q.getPicture() != null) {
				listview.add((Post)q);
			}
			for (Answer a: q.getAnswers()){
				if (a.getPicture() != null) {
					listview.add((Post)a);
				}
			}
		}
		
		assertTrue(listview.contains((Post)q1));
		assertTrue(listview.contains((Post)q2));
		assertTrue(listview.contains((Post)a1));	
		
		assertFalse(listview.contains((Post)q3)); // question 3 does not have an image
		assertFalse(listview.contains((Post)a2)); // answer 2 (question 2) does not have an image
		
		
	}
}
