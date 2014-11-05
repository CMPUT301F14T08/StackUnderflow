package cs.ualberta.CMPUT301F14T08.stackunderflow;

//import com.survivingwithandroid.actionbartabnavigation.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {
	/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.test_fragment_1, container, false);
        return rootView;
    }
    */
	
	private int index;
	
	private PostController sPostController;
	private PostAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		//setHasOptionsMenu(true);
		Bundle data = getArguments();
		index = data.getInt("idx");
	}
	
	/*
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    // TODO Add your menu entries here
	    super.onCreateOptionsMenu(menu, inflater);
	}
	*/
	
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
			sPostController.getPostManager().sortByDate();
			
			adapter = new PostAdapter(getActivity(), sPostController);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			break;
		case 1:
			sPostController = PostController.getInstance(getActivity());
			sPostController.getPostManager().sortByScore();
			
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
