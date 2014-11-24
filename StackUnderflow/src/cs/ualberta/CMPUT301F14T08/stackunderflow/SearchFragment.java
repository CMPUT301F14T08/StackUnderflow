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
import cs.ualberta.CMPUT301F14T08.stackunderflow.SearchObject;

public class SearchFragment extends Fragment {		
    protected ArrayList<Post> searchResult;
    protected PostAdapter adapter;
    private ListView listview;
    private View loadingPanel;

    private int searchType = 2;
    private boolean searchPics = false;
    private String searchTerms = "question";

    @Override
    public void onCreate(Bundle savedInstanceState) {		
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.search);
        Bundle b = getActivity().getIntent().getExtras();
        //savedInstanceState.getBoolean(SearchObject.SEARCH_PICS);
        searchType = b.getInt(SearchObject.SEARCH_TYPE);
        searchPics = b.getBoolean(SearchObject.SEARCH_PICS); //getArguments().getBoolean(SearchObject.SEARCH_PICS);
        searchTerms = b.getString(SearchObject.SEARCH_STRING); //getArguments().getString(SearchObject.SEARCH_STRING);
    }

    @Override
    public void onResume(){
        super.onResume();
        createView();
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

            }

        });		

        return view;

    }

    public void createView() {
        loadingPanel.setVisibility(View.VISIBLE);
        new DownloadPostsTask().execute();
        searchResult = new ArrayList<Post>(); //new SearchPosts().loadFromServer(searchType, searchPics, searchTerms);
        adapter = new PostAdapter(getActivity(), searchResult);
        listview.setAdapter(adapter);
    }


    private class DownloadPostsTask extends AsyncTask<Void, SearchPosts, SearchPosts> {

        protected SearchPosts doInBackground(Void... params) {
            return new SearchPosts();
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(SearchPosts result) {
            searchResult = result.loadFromServer(searchType, searchPics, searchTerms);

            adapter.clear();
            for (Post post : searchResult) {
                adapter.add(post);
            }

            adapter.notifyDataSetChanged();
            loadingPanel.setVisibility(View.GONE);
        }

    }
}
