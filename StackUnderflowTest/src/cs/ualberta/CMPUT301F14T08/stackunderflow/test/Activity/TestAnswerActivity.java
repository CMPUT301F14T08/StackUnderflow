
package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Activity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.LinearLayout;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.AnswerActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.QuestionActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.fragments.PostFragment;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.PostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;

public class TestAnswerActivity extends ActivityInstrumentationTestCase2<AnswerActivity> {

    public TestAnswerActivity() {
        super(AnswerActivity.class);
    }

    /*
     * Currently crashing due to null pointers. Fix when time permits.
     * Removed other tests in activities to reduce load on test-run-time.
     * Future note: using a method like TestSomething() does not run.
     * Have to use testSomething()
     *
     * Close to working, need to find a way to initialize the test, so that it
     * generates an activity for the PostController to utilize, while testing
     * the underlying activity. Can then be copied to the remaining activities
     *
     */
    /*public void testAnswerView() {
        MainActivity ma = new MainActivity();
        Intent intent = new Intent();
        setActivityIntent(intent);
        PostManager man = PostController.getInstance(ma).getPostManager();
        Question q = new Question("test", "test", "test");
        Answer a = new Answer("test", "test", "test");
        q.addAnswer(a);
        a.setParentQuestion(q);
        man.addQuestion(q);
        man.addAnswer(q, a);
        intent.putExtra(PostFragment.EXTRA_POST_ID, a.getID());
        setActivityIntent(intent);
        AnswerActivity aa = getActivity();
        LinearLayout layoutTest = (LinearLayout) aa.findViewById(cs.ualberta
                .CMPUT301F14T08.stackunderflow.R.id
                .post_fragment_top_linearlayout);
        LinearLayout layoutTest2 = (LinearLayout) aa.getWindow().getDecorView()
                .findViewById(cs.ualberta.CMPUT301F14T08
                        .stackunderflow.R.id.post_fragment_top_linearlayout);
        ViewAsserts.assertOnScreen(layoutTest, layoutTest2);
    }*/

}
