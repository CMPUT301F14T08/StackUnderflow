package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;


import java.util.ArrayList;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostAdapter;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.SearchPosts;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.UserProfileManager;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.SearchObject;
/**
 * 	Search view. This is where the search details are viewed.
 *  @author Cmput301 Winter 2014 Group 8
 */
public class SearchFragment extends ListFragment {

    private int searchType = 2;
    private boolean searchPics = false;
    private boolean searchLoc = false;
    private String searchTerms = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {		
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.search);
        Bundle b = getActivity().getIntent().getExtras();
        sPostController = PostController.getInstanceNoRefresh(getActivity());
        searchType = b.getInt(SearchObject.SEARCH_TYPE);
        searchPics = b.getBoolean(SearchObject.SEARCH_PICS);
        searchTerms = b.getString(SearchObject.SEARCH_STRING);
        searchLoc = b.getBoolean(SearchObject.SEARCH_LOCATION);
        
        currFrag = "search";
    }

    @Override
    public void onResume(){
        super.onResume();
        createView();
        currFrag = "search";
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
            searchResult = result.loadFromServer(searchType, searchPics, searchTerms, searchLoc);

            adapter.clear();
            
            boolean errorShown = false;
            if(searchLoc){
            	LatLng myLatLng = UserProfileManager.getInstance(getActivity()).getLocation();
            	if(myLatLng == null){
	            	Toast toast = Toast.makeText(getActivity(), "Cannot find your location to load nearby posts", Toast.LENGTH_LONG);
	            	toast.setGravity(Gravity.CENTER, 0, 0);
	            	toast.show();
	            	searchResult.clear();
	            	errorShown = true;
            	}
            	else{
            		Location myLoc = new Location("");
            		myLoc.setLatitude(myLatLng.latitude);
            		myLoc.setLongitude(myLatLng.longitude);
            		for (Post post : searchResult) {
            			if(post.hasLocation()){
	            			Location postLoc = new Location("");
	            			postLoc.setLatitude(post.getLocation().latitude);
	            			postLoc.setLongitude(post.getLocation().longitude);
	            			float distance = myLoc.distanceTo(postLoc);
	            			if(distance <= 100000){
	            				adapter.add(post);
	            			}
            			}
            		}
            	}
            }
            else{
	            for (Post post : searchResult) {
	                adapter.add(post);
	            }
            }
            
            if(adapter.getCount() == 0 && !errorShown){
            	Toast toast = Toast.makeText(getActivity(), "No results found", Toast.LENGTH_LONG);
            	toast.setGravity(Gravity.CENTER, 0, 0);
            	toast.show();
            }

            adapter.notifyDataSetChanged();
            loadingPanel.setVisibility(View.GONE);
        }

    }
}
