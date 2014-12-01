
package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;

/**
 * NewQuestionFragment - Called from NewQuestionACtivity - User to allow a user to input a question.
 * Takes input and saves the information to the correct post manager. Will not allow a user to input
 * blank fields.
 * 
 * @author Cmput301 Winter 2014 Group 8
 */
public class NewQuestionFragment extends NewPostFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Call NewPostFragment onCreateView
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        // Set up onClickListner for Adding new Questions
        // For adding images see the super class onCreateView

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String title = mPostTitle.getText().toString();
                String author = UserProfileManager.getInstance(getActivity()).getUsername();
                String body = mPostBody.getText().toString();

                // Checks if fields are left blank
                if (title.equalsIgnoreCase("") || body.equalsIgnoreCase("")
                        || (title.equalsIgnoreCase("") && body.equalsIgnoreCase(""))) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please Fill All Fields",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    Intent msg = new Intent();
                    msg.putExtra("question.title", title);
                    msg.putExtra("question.author", author);
                    msg.putExtra("question.body", body);
                    msg.putExtra("question.picture", mJPEGByteArray);
                    msg.putExtra("question.latitude", mLatitude);
                    msg.putExtra("question.longitude", mLongitude);

                    getActivity().setResult(Activity.RESULT_OK, msg);
                    getActivity().finish();
                }
            }
        });

        return v;

    }

}
