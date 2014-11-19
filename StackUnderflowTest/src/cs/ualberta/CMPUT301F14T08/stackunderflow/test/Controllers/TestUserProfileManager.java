package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Controllers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import android.R.string;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.CachedPostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.OnlinePostManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.UserProfile;
import cs.ualberta.CMPUT301F14T08.stackunderflow.UserProfileManager;

public class TestUserProfileManager extends ActivityInstrumentationTestCase2<MainActivity> {

	public TestUserProfileManager() {
		super(MainActivity.class);
	}
	
	private UserProfileManager getUserProfileManager() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class[] args = {Context.class};
        Constructor<UserProfileManager> constructor = UserProfileManager.class.getDeclaredConstructor(args);
        constructor.setAccessible(true);
        return constructor.newInstance(getActivity().getApplicationContext());
	}
    private Boolean save(UserProfileManager u) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method t = u.getClass().getDeclaredMethod("save", null);
        t.setAccessible(true);
        Object result = t.invoke(u);
        return (Boolean) result;
    }
    public void testEmptySave() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	UserProfileManager u = getUserProfileManager();
    	boolean result = save(u).booleanValue();
    	assertTrue(result);
    }
    public void testUserName() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	UserProfileManager u = getUserProfileManager();
    	u.getUserProfile().setUsername("test1");
    	String test=u.getUserProfile().getUsername();
    	assertEquals(test,"test1");
    }
    public void testChangedUserNameSave() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
    	UserProfileManager u = getUserProfileManager();
    	u.getUserProfile().setUsername("test1");
    	u.save();
    	UserProfileManager x = getUserProfileManager();
    	String test=x.getUserProfile().getUsername();
    	assertEquals(test,"test1");
    }
    public void testChangedDataSaveQuestion() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
    	UserProfileManager u = UserProfileManager.getInstance(getActivity());
    	UserProfileManager.getInstance(getActivity());
    	CachedPostManager manager = CachedPostManager.getInstance(getActivity());
        Question q = new Question("a", "a", "a");
        manager.addQuestion(q);
        Log.d("STUFF",""+u.getUserProfile().getUserAttributesForId(q.getID()));

    	assertEquals(u.getUserProfile().getUserAttributesForId(q.getID()),q.getUserAttributes());
    }
    public void testChangedDataSaveAnswer() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
    	UserProfileManager u = UserProfileManager.getInstance(getActivity());
    	UserProfileManager.getInstance(getActivity());
    	CachedPostManager manager = CachedPostManager.getInstance(getActivity());
    	Question q = new Question("a", "a", "a");
        Answer a = new Answer("a", "a", "a");
        manager.addAnswer(q,a);
        Log.d("STUFF",""+u.getUserProfile().getUserAttributesForId(q.getID()));
        
    	assertEquals(u.getUserProfile().getUserAttributesForId(a.getID()),a.getUserAttributes());
    }
}
