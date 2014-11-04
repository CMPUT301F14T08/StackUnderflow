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
	
	public void testPostManager() {
		//fail("Not yet implemented");
	}

	public void testClearSelected() {
		//fail("Not yet implemented");
	}

	public void testIsQuestion() {
		//fail("Not yet implemented");
	}

	public void testCastToQuestions() {
		//fail("Not yet implemented");
	}

	public void testCastToPosts() {
		//fail("Not yet implemented");
	}

	public void testGetPost() {
		//fail("Not yet implemented");
	}

	public void testGetPosts() {
		//fail("Not yet implemented");
	}

	public void testAddQuestion() {
		//fail("Not yet implemented");
	}

	public void testAddAnswer() {
		//fail("Not yet implemented");
	}

	public void testAddReply() {
		//fail("Not yet implemented");
	}

	public void testSortByScore() {
		ArrayList<Post> list = new ArrayList<Post>();
		ArrayList<Post> tmp = new ArrayList<Post>();
		PostManager man = PostController.getInstance(getActivity()).getPostManager();

		Question q1 = new Question("Q1", "Author1", "Q1 Auth1");
		Question q2 = new Question("Q2", "Author2", "Q2 Auth2 Votes");
		q2.incrementVotes();
		q2.incrementVotes();
		Question q3 = new Question("Q3", "Author3", "hello.jpg", "Q3 Auth3 Pic");
		q3.incrementVotes();
		
		Answer a1 = new Answer("A1", "Author1");
		a1.setParentQuestion(q1);
		Answer a2 = new Answer("A3", "Author2 V");
		a2.incrementVotes();
		a2.incrementVotes();
		a2.setParentQuestion(q2);
		Answer a3 = new Answer("A3", "Author3 P", "hindle.jpg");
		a3.incrementVotes();
		a3.setParentQuestion(q3);
		
		man.addQuestion(q1);
		man.addQuestion(q2);
		man.addQuestion(q3);

		man.sortByScore();
		
		tmp = man.getPosts();
		
		list.add(a2);
		list.add(q2);
		list.add(a3);
		list.add(q2);
		list.add(a1);
		list.add(q1);
		
		assertEquals(list, tmp);
	}

	public void testSortByDate() {
		//fail("Not yet implemented");
	}

	public void testFilterOutNoPicture() {
		//fail("Not yet implemented");
	}

	public void testFilterOutQuestions() {
		//fail("Not yet implemented");
	}

	public void testFilterOutAnswers() {
		//fail("Not yet implemented");
	}

	public void testClearFilters() {
		//fail("Not yet implemented");
	}

	public void testKeywordSearch() {
		//fail("Not yet implemented");
	}

	public void testSave() {
		//fail("Not yet implemented");
	}
	
	private ArrayList<Post> createData() {
		ArrayList<Post> list = new ArrayList<Post>();
		
		Question q1 = new Question("Q1", "Author1", "Q1 Auth1");
		Question q2 = new Question("Q2", "Author2", "Q2 Auth2 Votes");
		q2.incrementVotes();
		q2.incrementVotes();
		Question q3 = new Question("Q3", "Author3", "", "Q3 Auth3 Pic");
		q3.incrementVotes();
		
		Answer a1 = new Answer("A1", "Author1");
		a1.setParentQuestion(q1);
		Answer a2 = new Answer("A3", "Author2 V");
		a2.incrementVotes();
		a2.incrementVotes();
		a2.setParentQuestion(q2);
		Answer a3 = new Answer("A3", "Author3 P", "");
		a3.incrementVotes();
		a3.setParentQuestion(q3);
		
		list.add(q1);
		list.add(q2);
		list.add(q3);
		list.add(a1);
		list.add(a2);
		list.add(a3);
		
		return list;
	}

}
