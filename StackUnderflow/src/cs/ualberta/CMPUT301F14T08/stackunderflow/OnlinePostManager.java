package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cs.ualberta.CMPUT301F14T08.stackunderflow.es.ElasticSearchCommand;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.SearchHits;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.MatchAllCommand;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.Hit;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.SearchResponse;
import android.content.Context;
import android.util.Log;

public class OnlinePostManager extends PostManager {
    protected static OnlinePostManager sPostManager;
    private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t08/question/_search";
    private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t08/question/";
    private static final String TAG = "ELASTICSEARCH";
    private CachedPostManager mCachedPostManager;
    private Gson gson;
    
    protected OnlinePostManager(Context context) {
        super(context);
        gson = new Gson();
        mCachedPostManager = CachedPostManager.getInstance(context);
    }
    
    
    // 1. Gets questions from online
    // 2. Updates cache
    // TODO: Call strip/populate user attributes
    private ArrayList<Post> loadFromServer() {
        ArrayList<Question> questions = new ArrayList<Question>();
        HttpClient httpClient = new DefaultHttpClient();
        
        try {
            HttpPost request = new HttpPost(SEARCH_URL);
            ElasticSearchCommand command = new MatchAllCommand();
            
            request.setHeader("Accept", "application/json");
            request.setEntity(new StringEntity(command.getJsonCommand()));
            HttpResponse response = httpClient.execute(request);
            
            String status = response.getStatusLine().toString();
            Log.i(TAG, status);
            
            SearchResponse<Question> parsedResponse = parseSearchResponse(response);
            SearchHits<Question> searchHits = parsedResponse.getHits();
            
            if (searchHits != null && searchHits.getHits() != null) {
                for (Hit<Question> hit : searchHits.getHits()) {
                    questions.add(hit.getSource());
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ArrayList<Post> posts = castToPosts(questions);
        updateCache();
        return posts;
    }
    
    //TODO: implement when adding user attributes
    private void stripUserAttributes() {
        return;
    }
    
    //TODO: implement when adding user attributes
    private void populateUserAttributes() {
        return;
    }
    
    //TODO: implement when adding user attributes
    private void saveReadLaterToCachePostManager() {
        return;
    }
    
    //TODO: implement when adding user attributes
    private void saveReadToCachePostManager() {
        return;
    }
    
    private void updateCache() {
        for (Post post : getPosts()) {
            mCachedPostManager.updateIfExists(post);
        }
        mCachedPostManager.save();
    }
    
    public void refreshAll() {
        try {
            mPosts = loadFromServer();
        } catch (Exception e) {
            mPosts = new ArrayList<Post>();
        }
    }
    
    // Get Post with specific ES ID
    public void refreshQuestion(Question question) {
        updateIfExists(getOnlineQuestion(question));
        boolean updated = mCachedPostManager.updateIfExists(getOnlineQuestion(question));
        if (updated)
            mCachedPostManager.save();
    }
    
    // Get Post with specific ES ID
    public Question getOnlineQuestion(Question question) {

        Question onlineQuestion = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(RESOURCE_URL + question.getID());

        HttpResponse response;

        try {
            response = httpClient.execute(httpGet);
            Hit<Question> hit = parseGetResponse(response);
            onlineQuestion = hit.getSource();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return onlineQuestion;
    }
    
    
    private Hit<Question> parseGetResponse(HttpResponse response) {
        
        try {
            String json = getHttpResponseContent(response);
            Type hitType = new TypeToken<Hit<Question>>() {}.getType();
            
            Hit<Question> hit = gson.fromJson(json, hitType);
            return hit;
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    // Parses the response of a search
    private SearchResponse<Question> parseSearchResponse(HttpResponse response) throws IOException {
        String json;
        json = getHttpResponseContent(response);

        Type parsedResponseType = new TypeToken<SearchResponse<Question>>() {}.getType();
        SearchResponse<Question> parsedResponse = gson.fromJson(json, parsedResponseType);

        return parsedResponse;
    }

    // Gets content from an HTTP response
    private String getHttpResponseContent(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }
    
    // Updates question on ES server
    private void updateEsQuestion(Question existingQuestion, String updateString) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost updateRequest = new HttpPost(RESOURCE_URL + existingQuestion.getID() + "/_update");
       
        try {
            StringEntity stringEntity = new StringEntity(updateString);
            updateRequest.setEntity(stringEntity);
            updateRequest.setHeader("Accept", "application/json");
    
            HttpResponse response = httpClient.execute(updateRequest);
            String status = response.getStatusLine().toString();
            Log.i(TAG, status);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateEsQuestionVotes(Question existingQuestion, int voteChange) {
        String update = "{ \"script\" : \"ctx._source.mVotes += count\","
                + "\"params\" : { \"count\" : " + voteChange + "}}";
        updateEsQuestion(existingQuestion, update);
    }
    
    private void addEsQuestionReply(Question existingQuestion, Reply reply) {
        String update = "{ \"script\" : \"ctx._source.mReplies += reply\","
                + "\"params\" : { \"reply\" : "
                + gson.toJson(reply) + "}}";
        updateEsQuestion(existingQuestion, update);
    }
    
    private void addEsQuestionAnswer(Question existingQuestion, Answer answer) {
        String update = "{ \"script\" : \"ctx._source.mAnswers += answer\","
                + "\"params\" : { \"answer\" : "
                + gson.toJson(answer) + "}}";
        updateEsQuestion(existingQuestion, update);
    }
    
    // TODO
    private void updateEsFullQuestion(Question existingQuestion) {
        String update = gson.toJson(existingQuestion);
        updateEsQuestion(existingQuestion, update);
    }
    
    private void insertEsQuestion(Question newQuestion) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost addRequest = new HttpPost(RESOURCE_URL + newQuestion.getID());
        
        try {
            StringEntity stringEntity = new StringEntity(gson.toJson(newQuestion));
            addRequest.setEntity(stringEntity);
            addRequest.setHeader("Accept", "application/json");
    
            HttpResponse response = httpClient.execute(addRequest);
            String status = response.getStatusLine().toString();
            Log.i(TAG, status);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void pushOfflineUpdates() {
        for (Post post : mCachedPostManager.getPosts()) {
            if (!isQuestion(post))
                continue;
            
            // if the question is new then all associated
            // data including questions/replies are new and we
            // just have to insert the new question
            Question question = (Question) post;
            if (!question.getExistsOnline()) {
                question.setExistsOnline(true);
                insertEsQuestion(question);
                continue;
            }
            
            if(question.getUpvotesChangedOffline() != 0) {
                updateEsQuestionVotes(question, question.getUpvotesChangedOffline());
                question.setUpvotesChangedOffline(0);
            }

            for (Reply reply : question.getReplies()) {
                if (!reply.getExistsOnline()) { 
                    Question onlineQuestion = getOnlineQuestion(question);
                    if (onlineQuestion == null)
                        continue;
                    onlineQuestion.addReply(reply);
                    updateEsFullQuestion(onlineQuestion);
                    reply.setExistsOnline(true);
                }
            }
            
            for (Answer answer : question.getAnswers()) {
                if (!answer.getExistsOnline()) { 
                    Question onlineQuestion = getOnlineQuestion(question);
                    if (onlineQuestion == null)
                        continue;
                    onlineQuestion.addAnswer(answer);
                    updateEsFullQuestion(onlineQuestion);
                    answer.setExistsOnline(true);
                    continue;
                }
                if(answer.getUpvotesChangedOffline() != 0) {
                    Question onlineQuestion = getOnlineQuestion(question);
                    if (onlineQuestion == null)
                        continue;
                    Answer onlineAnswer = onlineQuestion.getAnswer(answer.getID());
                    if (onlineAnswer == null)
                        continue;
                    onlineAnswer.setVotes(answer.getUpvotesChangedOffline());
                    updateEsFullQuestion(onlineQuestion);
                    answer.setUpvotesChangedOffline(0);
                }
            }
        }
    }
    
    // 1. pushes updates to server
    // 2. get posts from server
    // TODO: 3. strips/loads user attributes
    // Static initializer, use this to get the active instance.
    // This insures we only ever have one copy going at once!
    public static OnlinePostManager getInstance(Context context) {
        if (sPostManager != null) {
            return sPostManager;
        }
        
        // initialize instance
        sPostManager = new OnlinePostManager(context.getApplicationContext());

        // push updates to server
        sPostManager.pushOfflineUpdates();
        
        // get posts from server
        try {
            sPostManager.mPosts = sPostManager.loadFromServer();
        } catch (Exception e) {
            sPostManager.mPosts = new ArrayList<Post>();
        }
        
        return sPostManager;
    }
    
    // Saves individual question
    @Override
    public void addQuestion(Question newQuestion) {
        newQuestion.setExistsOnline(true);
        super.addQuestion(newQuestion);
        insertEsQuestion(newQuestion);
        mCachedPostManager.addQuestion(newQuestion);
    }
    
    // Saves individual answer by updating associated question on ES server
    @Override
    public void addAnswer(Question parent, Answer newAnswer) {
        newAnswer.setExistsOnline(true);
        super.addAnswer(parent, newAnswer);
        refreshQuestion(parent);
        updateEsFullQuestion(parent);
        mCachedPostManager.addAnswer(parent, newAnswer);
    }
    
    //TODO: Implement in Project Part 4
    @Override
    public void addReply(Question parent, Reply newReply) {
        newReply.setExistsOnline(true);
        super.addReply(parent, newReply);
        mCachedPostManager.addReply(parent, newReply);
    }
    
    //TODO: Update with implementation of user attributes
    // For now this will just increment votes
    @Override
    public void toggleUpvote(Post post) {
        post.incrementVotes();
        Post cachedPost = mCachedPostManager.getPost(post.getID());
        cachedPost.setVotes(post.getVotes());
        mCachedPostManager.save();
    }
    
    //TODO: Update with implementation of user attributes
    @Override
    public void toggleReadLater(Post post) {
        super.toggleReadLater(post);
        
        // add the post to the cached post manager
        // if it is not already present
        Question question;
        
        if (isQuestion(post)) {
            question = (Question) post;
        }
        else {
            question = (Question)getPost(((Answer)post).getParentID());
        }
        
        if (mCachedPostManager.getPost(question.getID()) == null) {
            mCachedPostManager.addQuestion(question);
        }
            
        return;
    }
    public CachedPostManager getCachedPostManager()
    {
    	return mCachedPostManager;
    }
    
}
