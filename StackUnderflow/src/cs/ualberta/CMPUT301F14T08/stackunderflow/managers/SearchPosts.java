package cs.ualberta.CMPUT301F14T08.stackunderflow.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cs.ualberta.CMPUT301F14T08.stackunderflow.es.ElasticSearchCommand;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.Hit;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.MatchAllCommand;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.MatchSearchCommand;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.SearchHits;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.SearchResponse;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;

public class SearchPosts {
    private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t08/question/_search/?size=100";
    private Gson gson;

    public SearchPosts() {
        gson = new Gson();
    }

    /**
     * 1. Gets questions from online using search terms
     * 2. Returns a list of posts to the calling fragment
     * @return status a String that tells us if the files were successfully loaded from the server
     */
    public ArrayList<Post> loadFromServer(int type, boolean pics, String terms) {
        ArrayList<Post> posts = new ArrayList<Post>();
        HttpClient httpClient = new DefaultHttpClient();
        String status = null;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            HttpPost request = new HttpPost(SEARCH_URL);
            ElasticSearchCommand command = new MatchSearchCommand(type, pics, terms);

            request.setHeader("Accept", "application/json");
            request.setEntity(new StringEntity(command.getJsonCommand()));
            HttpResponse response = httpClient.execute(request);

            status = response.getStatusLine().toString();
            Log.d("Debug", status);

            SearchResponse<Question> parsedResponse = parseSearchResponse(response);
            SearchHits<Question> searchHits = parsedResponse.getHits();

            if (searchHits != null && searchHits.getHits() != null) {
                for (Hit<Question> hit : searchHits.getHits()) {
                    posts.add((Question)hit.getSource());
                }
            }

        } catch (Exception e) {
            status = "Exception " + e.getClass() + ": " + e.getMessage();
            Log.d("Debug", status);
        }

        //httpClient.getConnectionManager().shutdown();

        return posts;
    }

    private SearchResponse<Question> parseSearchResponse(HttpResponse response) throws IOException {
        String json;
        json = getHttpResponseContent(response);

        Type parsedResponseType = new TypeToken<SearchResponse<Question>>() {}.getType();
        SearchResponse<Question> parsedResponse = gson.fromJson(json, parsedResponseType);

        return parsedResponse;
    }

    /**
     *  Gets content from an HTTP response
     * @param response used to connect to ES
     * @return content from an HTTP response as a sting
     * @throws IOException if it cannot connect
     */
    private String getHttpResponseContent(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

}
