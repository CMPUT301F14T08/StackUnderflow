package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class NewQuestionFragment extends Fragment {

	protected TextView mQuestionTitle;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.new_question_title);
		// TODO: This should fail gracefully if getPost returns null
	}
	
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		
		View v = inflater.inflate(R.layout.new_question_fragment, parent, false);
		
		//Test
		mQuestionTitle = (TextView)v.findViewById(R.id.new_question_fragment_edittext_title);
		mQuestionTitle.setText("TESTING");
		
		
		return v;		
	}
}
