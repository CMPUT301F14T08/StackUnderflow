
package cs.ualberta.CMPUT301F14T08.stackunderflow.test.Model;

import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.MainActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;

public class TestUserAttributes extends ActivityInstrumentationTestCase2<MainActivity> {

    public TestUserAttributes() {
        super(MainActivity.class);
    }

    public void testUserAttributes() {
        UserProfileManager upm = UserProfileManager.getInstance(getActivity());
        assertNotNull(upm);
        
        assertNotNull(upm.getUsername());
        
        upm.setUsername("Jon");
        assertEquals(upm.getUsername(), "Jon");
        
        upm.setUsername("Guest");
    }

}
