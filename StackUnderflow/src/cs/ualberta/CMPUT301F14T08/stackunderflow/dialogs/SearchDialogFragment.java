
package cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.drawable;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.id;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.layout;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.SearchActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.SearchObject;

/**
 * When you choose to search a question this view is called. It allows the user to view questions in
 * a number of ways By search only by question, search only by answer or search by both.
 * 
 * @author Cmput301 Winter 2014 Group 8
 */
public class SearchDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View v = inflater.inflate(R.layout.search_prompt, null);
        final RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.SearchPostRadioButtons);
        final CheckBox picbox = (CheckBox) v.findViewById(R.id.search_picture_button);
        final CheckBox locbox = (CheckBox) v.findViewById(R.id.MyLocationBox);
        final EditText text = (EditText) v.findViewById(R.id.searchEnteredText);

        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                .setIcon(R.drawable.search_large)
                // Set Dialog Title
                .setTitle("Search")
                .setView(v)
                // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // default to search only questions
                        int searchType = 0;

                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.QuestionsOnlyButton:
                                searchType = SearchObject.SEARCH_QUESTIONS;
                                break;
                            case R.id.AnswersOnlyButton:
                                searchType = SearchObject.SEARCH_ANSWERS;
                                break;
                            case R.id.BothButton:
                                searchType = SearchObject.SEARCH_BOTH;
                                break;
                        }
                        boolean searchPics = picbox.isChecked();
                        boolean searchLoc = locbox.isChecked();
                        String searchTerms = text.getText().toString();

                        Intent intent = new Intent(getActivity(), SearchActivity.class);
                        intent.putExtra(SearchObject.SEARCH_TYPE, searchType);
                        intent.putExtra(SearchObject.SEARCH_PICS, searchPics);
                        intent.putExtra(SearchObject.SEARCH_STRING, searchTerms);
                        intent.putExtra(SearchObject.SEARCH_LOCATION, searchLoc);

                        startActivityForResult(intent, 0);
                    }
                })
                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String toast_text = "Search canceled";
                        Toast toast = Toast.makeText(getActivity(), toast_text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }).create();
    }

}
