package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;
import java.util.ArrayList;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainFragment extends Fragment {
	
	private static final String SORT_DATE = "DATE";
	private static final String SORT_SCORE = "SCORE";

	private int index;
	
	protected PostController sPostController;
	protected PostAdapter adapter;
	private ListView listview;
	private String lastSort;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Bundle data = getArguments();
		index = data.getInt("idx");

	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		createView(lastSort);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.list_fragment, null);
		listview = (ListView) view.findViewById(R.id.list_view);

		listview.setOnItemClickListener(new OnItemClickListener() {
			
			// Opens Question or Answer Fragment based upon list item clicked 
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				// TODO: Update
				Intent i;
				
				Post p = ((PostAdapter)adapter).getItem(position);
				
				// Move the putExtra & startActivity out once AnswerActivity is created
				if (p instanceof Question) {
					i = new Intent(getActivity(), QuestionActivity.class);
				}
				else {
					i = new Intent(getActivity(), AnswerActivity.class);
				}
				i.putExtra(PostFragment.EXTRA_POST_ID, p.getID());
				startActivity(i);
				// place putExtra and start activity down here, remove braces on statements			
						
			}
			
		});		
		
		// Implements tab-switching between sorted list views for "Newest" and "Popular" Main Activity tabs
		switch(index){
		case 0:
			createView(SORT_DATE);
			lastSort = SORT_DATE;
			break;
			
		case 1:
			createView(SORT_SCORE);
			lastSort = SORT_SCORE;
			break;
			
		default:
			break;
		
		}		
		
		return view;

	}
	
	public void createView(String sort) {
		sPostController = PostController.getInstance(getActivity());
		if (sort.equals(SORT_DATE))
			sPostController.getPostManager().sortByDate();
		else if (sort.equals(SORT_SCORE)) 
			sPostController.getPostManager().sortByScore();
		
		
		adapter = new PostAdapter(getActivity(), sPostController.getPostManager().getQuestions());
		listview.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

}
