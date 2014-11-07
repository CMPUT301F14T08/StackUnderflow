package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Controllers;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.PostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;

public class TestPostManager extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public TestPostManager() {
		super(MainActivity.class);
	}

	public void testSortByScore() {
		ArrayList<Post> list = new ArrayList<Post>();
		ArrayList<Post> tmp = new ArrayList<Post>();
		PostController.getInstance(getActivity()).getPostManager().ClearManager();
		PostManager man = PostController.getInstance(getActivity()).getPostManager();
		
		Question q1 = new Question("Q1", "Author1", "Q1 Auth1");
		q1.incrementVotes();
		q1.incrementVotes();
		q1.incrementVotes();
		q1.incrementVotes();
		q1.incrementVotes();
		
		Question q2 = new Question("Q2", "Author2", "Q2 Auth2 Votes");
		q2.incrementVotes();
		q2.incrementVotes();
		q2.incrementVotes();
		
		Question q3 = new Question("Q3", "Author3", "hello.jpg", "Q3 Auth3 Pic");
		q3.incrementVotes();
		
		Answer a1 = new Answer("A1", "Author1");
		a1.incrementVotes();
		a1.incrementVotes();
		a1.setParentQuestion(q1);
		
		Answer a2 = new Answer("A3", "Author2 V");
		a2.incrementVotes();
		a2.incrementVotes();
		a2.incrementVotes();
		a2.incrementVotes();
		a2.setParentQuestion(q2);
		
		Answer a3 = new Answer("A3", "Author3 P", "hindle.jpg");
		a3.incrementVotes();
		a3.incrementVotes();
		a3.incrementVotes();
		a3.incrementVotes();
		a3.incrementVotes();
		a3.incrementVotes();
		a3.setParentQuestion(q3);
		
		man.addQuestion(q1);
		man.addQuestion(q2);
		man.addQuestion(q3);
		man.addAnswer(q1, a1);
		man.addAnswer(q2, a2);
		man.addAnswer(q3, a3);

		man.sortByScore();
		
		tmp = man.getPosts();
		
		list.add(a3);
		list.add(q1);
		list.add(a2);
		list.add(q2);
		list.add(a1);
		list.add(q3);
		
		assertEquals(list, tmp);
	}

	// Test sort by date tested in TestCachedPostManager

	public void testFilterOutNoPicture() {
		ArrayList<Post> list = new ArrayList<Post>();
		PostController.getInstance(getActivity()).getPostManager().ClearManager();
		PostManager man = PostController.getInstance(getActivity()).getPostManager();
		
		Question q1 = new Question("Q1", "Author1", "Q1 Auth1");	
		Question q2 = new Question("Q3", "Author3", "hello.jpg", "Q3 Auth3 Pic");
		
		Answer a1 = new Answer("A3", "Author2 V");
		Answer a2 = new Answer("A3", "Author3 P", "hindle.jpg");
		
		q1.addAnswer(a1);
		q2.addAnswer(a2);
		
		man.addQuestion(q1);
		man.addQuestion(q2);
		man.addAnswer(q1, a1);
		man.addAnswer(q2, a2);
		
		man.filterOutNoPicture();
		
		list.add(q2);
		list.add(a2);
		
		assertEquals(list, man.getUnFilteredPosts());
	}

	public void testFilterOutQuestions() {
		ArrayList<Post> list = new ArrayList<Post>();
		PostController.getInstance(getActivity()).getPostManager().ClearManager();
		PostManager man = PostController.getInstance(getActivity()).getPostManager();
		
		Question q1 = new Question("Q1", "Author1", "Q1 Auth1");	
		Question q2 = new Question("Q3", "Author3", "hello.jpg", "Q3 Auth3 Pic");
		
		Answer a1 = new Answer("A3", "Author2 V");
		Answer a2 = new Answer("A3", "Author3 P", "hindle.jpg");
		
		q1.addAnswer(a1);
		q2.addAnswer(a2);
		
		man.addQuestion(q1);
		man.addQuestion(q2);
		man.addAnswer(q1, a1);
		man.addAnswer(q2, a2);
		
		man.filterOutQuestions();
		
		list.add(a1);
		list.add(a2);
		
		assertEquals(list, man.getUnFilteredPosts());
	}

	public void testFilterOutAnswers() {
		ArrayList<Post> list = new ArrayList<Post>();
		PostController.getInstance(getActivity()).getPostManager().ClearManager();
		PostManager man = PostController.getInstance(getActivity()).getPostManager();
		
		Question q1 = new Question("Q1", "Author1", "Q1 Auth1");	
		Question q2 = new Question("Q3", "Author3", "hello.jpg", "Q3 Auth3 Pic");
		
		Answer a1 = new Answer("A3", "Author2 V");
		Answer a2 = new Answer("A3", "Author3 P", "hindle.jpg");
		
		q1.addAnswer(a1);
		q2.addAnswer(a2);
		
		man.addQuestion(q1);
		man.addQuestion(q2);
		man.addAnswer(q1, a1);
		man.addAnswer(q2, a2);
		
		man.filterOutAnswers();
		
		list.add(q1);
		list.add(q2);
		
		assertEquals(list, man.getUnFilteredPosts());
	}

	public void testClearFilters() {
		ArrayList<Post> list = new ArrayList<Post>();
		PostController.getInstance(getActivity()).getPostManager().ClearManager();
		PostManager man = PostController.getInstance(getActivity()).getPostManager();
		
		Question q1 = new Question("Q1", "Author1", "Q1 Auth1");	
		Question q2 = new Question("Q3", "Author3", "hello.jpg", "Q3 Auth3 Pic");
		
		Answer a1 = new Answer("A3", "Author2 V");
		Answer a2 = new Answer("A3", "Author3 P", "hindle.jpg");
		
		q1.addAnswer(a1);
		q2.addAnswer(a2);
		
		man.addQuestion(q1);
		man.addQuestion(q2);
		man.addAnswer(q1, a1);
		man.addAnswer(q2, a2);
		
		man.filterOutAnswers();
		
		list.add(q1);
		list.add(q2);
		
		assertEquals(list, man.getUnFilteredPosts());
		
		man.clearFilters();
		list.add(a1);
		list.add(a2);
		
		assertEquals(list, man.getUnFilteredPosts());
	}

	public void testKeywordSearch() {
		//fail("Not yet implemented");
	}

}
