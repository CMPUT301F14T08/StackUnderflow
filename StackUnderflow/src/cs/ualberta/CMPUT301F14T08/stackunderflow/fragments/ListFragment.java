
package cs.ualberta.CMPUT301F14T08.stackunderflow.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.AnswerActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.activities.QuestionActivity;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostAdapter;
import cs.ualberta.CMPUT301F14T08.stackunderflow.controllers.PostController;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;

public class ListFragment extends Fragment {
    protected ArrayList<Post> searchResult;
    public PostAdapter adapter;
    protected ListView listview;
    protected View loadingPanel;
    public PostController sPostController;
    protected String currFrag = "main";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
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
                Post p = ((PostAdapter) adapter).getItem(position);

                if (currFrag == "search") {
                    sPostController.getPostManager().getPost(p.getID()).toggleIsSelected();
                }
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
                Intent i;

                Post p = ((PostAdapter) adapter).getItem(position);
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
}
