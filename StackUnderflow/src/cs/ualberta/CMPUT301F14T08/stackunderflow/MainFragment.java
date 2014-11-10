/**
 * MainFragment This is where the sorting on the main screen appears so the user may view by most popular or most recent.
 * this also allows the user to move to questions that they would like to see.
 * @author Cmput301 Winter 2014 Group 8
 */
package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MainFragment extends Fragment {
	
	private static final String SORT_DATE = "DATE";
	private static final String SORT_SCORE = "SCORE";

	private int index;
	
	protected PostController sPostController;
	protected PostAdapter adapter;
	private ListView listview;
	private View loadingPanel;
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
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.list_fragment, null);
		listview = (ListView) view.findViewById(R.id.list_view);
		loadingPanel = view.findViewById(R.id.loadingPanel);
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> l, View v, int position, long id) {
                Post p = ((PostAdapter)adapter).getItem(position);
       
                p.toggleIsSelected();
                int color = p.getIsSelected() ? R.color.selection_blue : R.color.off_white;
                v.setBackgroundResource(color);
                Log.d("Debug", "Is Selected: " + p.getIsSelected());
                return true;
            }
		    
        });
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			
			// Opens Question or Answer Fragment based upon list item clicked 
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				// TODO: Update
				Intent i;
				
				Post p = ((PostAdapter)adapter).getItem(position);
				// Move the putExtra & startActivity out once AnswerActivity is created
				if (p instanceof Question) {
				    Log.d("Debug", "Question Clicked: " + p.getID());
					i = new Intent(getActivity(), QuestionActivity.class);
					i.putExtra(PostFragment.EXTRA_POST_ID, p.getID());
	                startActivity(i);
				}
				else if (p instanceof Answer) {
				    Log.d("Debug", "Answer Clicked: " + p.getID());
					i = new Intent(getActivity(), AnswerActivity.class);
		            i.putExtra(PostFragment.EXTRA_POST_ID, p.getID());
		            startActivity(i);
				}

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
        loadingPanel.setVisibility(View.VISIBLE);
		new DownloadPostsTask().execute();
		adapter = new PostAdapter(getActivity(), new ArrayList<Post>());
		listview.setAdapter(adapter);
	}

	
	private class DownloadPostsTask extends AsyncTask<Void, PostController, PostController> {
	    
	     protected PostController doInBackground(Void... params) {
	         return PostController.getInstanceForList(getActivity());

	         
	     }
	     
	     @Override
	     protected void onPreExecute() {
	         
	     }
	     
	     @Override
	     protected void onPostExecute(PostController result) {
	         sPostController = result;
	         
             if (lastSort.equals(SORT_DATE))
                 sPostController.getPostManager().sortByDate();
              else if (lastSort.equals(SORT_SCORE)) 
                 sPostController.getPostManager().sortByScore();
             
	         adapter.clear();
             for (Post post : sPostController.getPostManager().getQuestions()) {
                 adapter.add(post);
             }

	         adapter.notifyDataSetChanged();
	         loadingPanel.setVisibility(View.GONE);
	     }

	 }

}
