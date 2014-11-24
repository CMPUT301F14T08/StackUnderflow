
package cs.ualberta.CMPUT301F14T08.stackunderflow;

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
import cs.ualberta.CMPUT301F14T08.stackunderflow.SearchObject;
/**
 * 
 * @author Cmput301 Winter 2014 Group 8
 */
public class SearchDialogFragment extends DialogFragment{
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View v = inflater.inflate(R.layout.search_prompt, null);
		final RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.SearchPostRadioButtons);
		final CheckBox checkbox = (CheckBox) v.findViewById(R.id.search_picture_button);
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
                		//default to search only questions
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
                		boolean searchPics = checkbox.isChecked();
                		String searchTerms = text.getText().toString();
                		
                		Intent intent = new Intent(getActivity(), SearchActivity.class);
                		intent.putExtra(SearchObject.SEARCH_TYPE, searchType);
                		intent.putExtra(SearchObject.SEARCH_PICS, searchPics);
                		intent.putExtra(SearchObject.SEARCH_STRING, searchTerms);
                		startActivityForResult(intent, 0);
                    }
                })
 
                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
            			String toast_text = "Search canceled";
            			Toast toast = Toast.makeText(getActivity(), toast_text, Toast.LENGTH_SHORT);
            			toast.show();
                    }
                }).create();
    }

}
