
package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Model;

import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Reply;
import android.test.ActivityInstrumentationTestCase2;

public class TestAnswer extends ActivityInstrumentationTestCase2<MainActivity> {

    public TestAnswer() {
        super(MainActivity.class);
    }

    public void testAddReply() {
        String text = "sample reply 1";
        String author = "author 1";

        Answer a1 = new Answer(text, author);
        Reply r1 = new Reply(text, author);

        a1.addReply(r1);
        assertEquals(a1.getReplies().get(0), r1);
    }

    public void testParent() {
        Answer a1 = new Answer("Test1", "Author1");
        Question q1 = new Question("Test1", "Author1", "Test1");

        q1.addAnswer(a1);
        a1.setParentQuestion(q1);

        assertEquals(q1.getID(), a1.getParentID());
        // Remove if we change how questions store answers
        assertEquals(q1.getAnswers().get(0), a1);
    }

}
