package cs.ualberta.CMPUT301F14T08.stackunderflow;

//import com.survivingwithandroid.actionbartabnavigation.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class TestFragment extends Fragment {
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
		Bundle data = getArguments();
		index = data.getInt("idx");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View v = inflater.inflate(R.layout.test_fragment_1, null);
		//TextView tv = (TextView) v.findViewById(R.id.msg);
		ListView lv = (ListView) v.findViewById(R.id.list_view);
		//tv.setText("Fragment " + (index + 1));
		
		
		
	    //sPostController = PostController.getInstance(getActivity());
		switch(index){
		case 1:
			sPostController = PostController.getInstance(getActivity());
			sPostController.getPostManager().sortByDate();
			//sPostController.getPostManager().getPosts().get(0);
			adapter = new PostAdapter(getActivity(), sPostController);
			lv.setAdapter(adapter);
			
			break;
		case 2:
			break;
		default:
			break;
		
		}

		
		return v;
	}
    

}
