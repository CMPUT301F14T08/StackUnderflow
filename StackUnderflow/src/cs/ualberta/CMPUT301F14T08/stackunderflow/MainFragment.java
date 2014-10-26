package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainFragment extends ListFragment implements ActionBar.TabListener{
	
	private ArrayList<Question> mQuestions;
	private ArrayList<Post> mQandA;
	public PostController sPostController;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		setHasOptionsMenu(true);
	    final ActionBar actionBar = getActivity().getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText(R.string.newest)
	            .setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText(R.string.popular)
	            .setTabListener(this));
	    
	    sPostController = PostController.getInstance(getActivity());
	    
	    mQuestions = sPostController.getPostManager().castToQuestions();
		//mQuestions = getQuestions();
		//ArrayList<Answer> mAnswers = mQuestions.getQuestions().;
		//ArrayList<Post> m
		//for (Question q : mQuestions){
		//	mQandA.addAll(q.getAnswers());
		//}
		QuestionAdapter adapter = new QuestionAdapter(mQuestions);
				setListAdapter(adapter);
	    
	}
	
	//Options Menu
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_main_menu, menu);

	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	
	}
	
	//Action Bar Tabs : Newest and Popular
	@Override
	public void onTabSelected(ActionBar.Tab tab,
		FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, show the tab contents in the
		// container view.
		
		//TODO: implement switching/filtering of list view
		  
		/*TODO Remove: Testing Dummy section
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
		    tab.getPosition() + 1);
		fragment.setArguments(args);
		getFragmentManager().beginTransaction()
		    .replace(R.id.base_container, fragment).commit();
		 */		  
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		//TODO implement
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		//TODO implement
	}
	  

	//List click listener
	//uses ListFragment extension of class
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Question q = ((QuestionAdapter)getListAdapter()).getItem(position);
		Intent i = new Intent(getActivity(), QuestionActivity.class);
		i.putExtra(PostFragment.EXTRA_POST_ID, q.getID());
		startActivity(i);

	}
	
	//ListView Adapter (move to PostAdapter class, or use Jon's)
	private class QuestionAdapter extends ArrayAdapter<Question> {
		
		public QuestionAdapter(ArrayList<Question> questions) {
			super(getActivity(), 0, questions);
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
				.inflate(R.layout.fragment_main_list_item, null);
			}
			// Configure the view for this Question
			Question q = getItem(position);
			
			// Question text
			TextView titleTextView =
					(TextView)convertView.findViewById(R.id.main_question_text);
			titleTextView.setText(q.getTitle());
			
			// Subtitle: author, date, upvotes text
			TextView subtitleTextView =
					(TextView)convertView.findViewById(R.id.main_question_subtitle_text);	

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String tmp = sdf.format(q.getDate());
			
			final String formattedSubTitle = String.format("by %s | %s | %s votes", 
					q.getSignature(), 
					tmp, q.getVotes()
				);
			subtitleTextView.setText(formattedSubTitle);

			// Answer count text (requires drawable/rounded_corners.xml)
			TextView answerCountTextView =
					(TextView)convertView.findViewById(R.id.main_answer_count_text);
			final String formattedAnswerCount = String.format("%s\nAnswers", Integer.toString(q.getAnswers().size()));
			Spannable ss1 = new SpannableString(formattedAnswerCount);
			ss1.setSpan(new RelativeSizeSpan(0.4f), q.getAnswers().size()/10+1, ss1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			answerCountTextView.setText(ss1);

			return convertView;
		}
	}
	
	//TEST CODE BELOW *************************************************************
	
	//TODO JUST FOR TESTING, Remove. Test sections for tab switching
	public static class DummySectionFragment extends Fragment {
		public static final String ARG_SECTION_NUMBER = "placeholder_text";
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		    Bundle savedInstanceState) {
		  TextView textView = new TextView(getActivity());
		  textView.setGravity(Gravity.CENTER);
		  textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		  return textView;
		}
	}
	
	
	//TODO JUST FOR TESTING, Remove. Creates list of questions for testing
    private ArrayList<Question> getQuestions() {
	  	
    	final ArrayList<Question> entries = new ArrayList<Question>();
    	
    	for(int i = 1; i < 50; i++) {
    		Question q = new Question("Q: " + i + " Lorem ipsum dolor sit amet, consectetur adipiscing elit", "Author "+i, "Title" + i);    		
    		
//    		Random rand = new Random();
//    		int rn = rand.nextInt(10) + 1;
//    		for (int j = 0; j < rn; ++j){
//    			Answer a = new Answer("1","1");
//    			q.addAnswer(a);
//    		}
    		
    		entries.add(q);
    	}
    	
    	return entries;
    }
    
}
