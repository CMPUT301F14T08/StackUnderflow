package cs.ualberta.CMPUT301F14T08.stackunderflow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainFragment extends Fragment {
	
	private int index;
	
	private PostController sPostController;
	//private ArrayAdapter<Post> adapter;
	//private ArrayList<Post> ar;
	private PostAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		//setHasOptionsMenu(true);
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
		
		//sPostController = PostController.getInstance(getActivity());
		switch(index){
		case 0:
			
			sPostController = PostController.getInstance(getActivity());
			// JON REMOVE COMMENTED LINE BELOW TO TEST ANSWER FILTERING
			sPostController.getPostManager().filterOutAnswers();
			sPostController.getPostManager().sortByDate();

			/* JON REMOVE COMMENTS ON THIS BLOCK TO TEST ANSWER FILTERING
			for (int i=0; i< sPostController.getPostManager().mPosts.size(); i++) {
				Post item = sPostController.getPostManager().mPosts.get(i);
				if (item.getIsFiltered()){
					sPostController.getPostManager().mPosts.remove(item);
				}
			}
			*/
			
			adapter = new PostAdapter(getActivity(), sPostController);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();			
			break;
			
		case 1:

			sPostController = PostController.getInstance(getActivity());
			sPostController.getPostManager().filterOutAnswers();
			sPostController.getPostManager().sortByScore();
			
			/* JON REMOVE COMMENTS ON THIS BLOCK TO TEST ANSWER FILTERING
			for (int i=0; i< sPostController.getPostManager().mPosts.size(); i++) {
				Post item = sPostController.getPostManager().mPosts.get(i);
				if (item.getIsFiltered()){
					sPostController.getPostManager().mPosts.remove(item);
				}
			}
			*/
	
			adapter = new PostAdapter(getActivity(), sPostController);			
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			break;
			
		default:
			break;
		
		}		
		
		return view;
	}
	    

}
