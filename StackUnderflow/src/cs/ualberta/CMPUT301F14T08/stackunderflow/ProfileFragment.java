
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
/**
 * MainFragment This is where the sorting on the main screen appears so the user may view by most popular or most recent.
 * this also allows the user to move to questions that they would like to see.
 * @author Cmput301 Winter 2014 Group 8
 */
public class ProfileFragment extends Fragment {
	
	private static final String FILTER_MY_POSTS = "MY_POSTS";
	private static final String FILTER_MY_FAVORITES = "MY_FAVORITES";
	private static final String FILTER_READ_LATER = "READ_LATER";
	
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
			createView(FILTER_MY_POSTS);
			lastSort = FILTER_MY_POSTS;
			break;
			
		case 1:
			createView(FILTER_MY_FAVORITES);
			lastSort = FILTER_MY_FAVORITES;
			break;
		case 2:
			createView(FILTER_READ_LATER);
			lastSort = FILTER_READ_LATER;
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
	         
	         sPostController.getPostManager().sortByDate();
	         
	         adapter.clear();
	         
	         //FILTER_MY_POSTS is currently blank list: needs posts with username set (UserProfile implementation)
	         for (Post post : sPostController.getPostManager().getQuestions()) {
            	 if (
            			 (lastSort.equals(FILTER_MY_POSTS) && post.getUserAttributes().getIsUsers())
            			 || (lastSort.equals(FILTER_MY_FAVORITES) && post.getUserAttributes().getIsFavorited())
            			 || (lastSort.equals(FILTER_READ_LATER) && post.getUserAttributes().getIsReadLater())
            		 )
            	 {
            		 adapter.add(post);
            	 }
             }
             

	         adapter.notifyDataSetChanged();
	         loadingPanel.setVisibility(View.GONE);
	     }

	 }

}
