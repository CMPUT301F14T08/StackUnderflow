

package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * An adaptor to return the desired view of a post, for use in a list view.
 * Called by profile fragment and main fragment to populate the list views.
 * @author Cmput301 Winter 2014 Group 8
 */
public class ReplyAdapter extends ArrayAdapter<Reply> {
    
    
    /* uses the passed in controller to create an ArrayAdapter of Posts
     * Once merged with PostController, edit out ArrayList<Post> for
     * PostController, and add controller.getPostManager().getPosts());
     */
    // TODO: read above
    public ReplyAdapter(Context context, ArrayList<Reply> replies) {
        super(context, 0, replies);
    }
    
    // set up the required view, or uses a recycled view instead
    public View getView(int position, View view, ViewGroup parent) {

        TextView replyText;
        Reply currReply;
        
        currReply = getItem(position);
        
        // If the view is null, inflate one
        if (view == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            view = inflator.inflate(R.layout.fragment_reply_list_item, parent, false); //list_item_post, parent, false);
        }
        
        replyText = (TextView) view.findViewById(R.id.reply_text);
        replyText.setText(currReply.getText() + " - " + currReply.getSignature());
        
        return view;
    }

}