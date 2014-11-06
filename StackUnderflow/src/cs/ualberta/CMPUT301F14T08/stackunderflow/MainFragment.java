package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainFragment extends Fragment {
	
	private int index;
	
	private PostController sPostController;
	protected PostAdapter adapter;
	
	private ArrayList<Post> actualList;
	private ArrayList<Post> questionList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Bundle data = getArguments();
		index = data.getInt("idx");
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.list_fragment, null);
		ListView listview = (ListView) view.findViewById(R.id.list_view);

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
			
			sPostController = PostController.getInstance(getActivity());
			sPostController.getPostManager().sortByDate();
			
			actualList = sPostController.getPostManager().getPosts();
			questionList = new ArrayList<Post>();
		
			for (int i = 0; i < actualList.size(); i++) {
			    Post p = actualList.get(i);
			    if (p instanceof Question)
			        questionList.add(p);
			}
			
			adapter = new PostAdapter(getActivity(), questionList);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();			
			break;
			
		case 1:

			sPostController = PostController.getInstance(getActivity());
			sPostController.getPostManager().filterOutAnswers();
			sPostController.getPostManager().sortByScore();
			
			actualList = sPostController.getPostManager().getPosts();
			questionList = new ArrayList<Post>();
			
			for (int i = 0; i < actualList.size(); i++) {
			    Post p = actualList.get(i);
			    if (p instanceof Question)
			        questionList.add(p);
			}
			
			adapter = new PostAdapter(getActivity(), questionList);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();			
			break;
			
		default:
			break;
		
		}		
		
		return view;
	}
	    

}
