package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.FragmentManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.UsernameDialogFragment;

public class UsernameDialog {
	public UsernameDialog() {
		super();
	}
	
	public static void showDialog(FragmentManager fm) {
		UsernameDialogFragment udf = new UsernameDialogFragment();
		udf.show(fm, "Username Dialog Fragment");
		
	}

}
