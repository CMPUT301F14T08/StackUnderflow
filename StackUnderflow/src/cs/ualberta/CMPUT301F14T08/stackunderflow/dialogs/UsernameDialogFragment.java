
package cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.drawable;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.id;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.layout;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.string;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
/**
 * UsernameDiaglogFragment - When a user attempts to input a question or answer they will be prompted with a window asking for them for a user name that will
 * then be saved on the device locally so that they may post with that same user name every time.
 * @author Cmput301 Winter 2014 Group 8
 */
public class UsernameDialogFragment extends DialogFragment{
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View v = inflater.inflate(R.layout.username_prompt, null);
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                .setIcon(R.drawable.user_large)
                // Set Dialog Title
                .setTitle("Enter a username")
                // Set Dialog Message
                .setMessage(R.string.dialogDescText)
                .setView(v)
                // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edit = (EditText) v.findViewById(R.id.enterUsername);
                        String name = edit.getText().toString();
                        
                        if (name.length() > 0) {
                        	//Refactor this out, too many dots
                        	UserProfileManager.getInstance(getActivity()).setUsername(name);
                        }
                    }
                })
 
                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
                        //Fix this to not close the profile page when hitting cancel
                    	//if (getActivity().findViewById(R.id.answersTextView) != null)
                    		getActivity().finish();
                    }
                }).create();
    }
}
