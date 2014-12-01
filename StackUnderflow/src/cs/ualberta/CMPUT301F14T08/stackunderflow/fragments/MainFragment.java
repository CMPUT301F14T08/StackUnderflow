package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostAdapter;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
/**
 * MainFragment This is where the sorting on the main screen appears so the user may view by most popular or most recent.
 * this also allows the user to move to questions that they would like to see.
 * @author Cmput301 Winter 2014 Group 8
 */
public class MainFragment extends ListFragment {

    private static final String SORT_DATE = "DATE";
    private static final String SORT_SCORE = "SCORE";

    private int index;
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
        currFrag = "main";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

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
