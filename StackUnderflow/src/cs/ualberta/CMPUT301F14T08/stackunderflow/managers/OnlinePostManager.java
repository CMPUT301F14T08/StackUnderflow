
package cs.ualberta.CMPUT301F14T08.stackunderflow.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cs.ualberta.CMPUT301F14T08.stackunderflow.es.ElasticSearchCommand;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.Hit;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.MatchAllCommand;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.SearchHits;
import cs.ualberta.CMPUT301F14T08.stackunderflow.es.SearchResponse;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Answer;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Post;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Question;
import cs.ualberta.CMPUT301F14T08.stackunderflow.model.Reply;

/**
 * OnlinePostManager - Saves users questions, answers, posts and statistics that the users post into
 * elastic search. This will manage all elastic search information such as connecting and sending
 * and reserving information from elastic search. This will also load Questions, Answers, Posts, and
 * Statistics on to the phone for the user to view.
 * 
 * @author Cmput301 Winter 2014 Group 8
 */
public class OnlinePostManager extends PostManager {
    protected static OnlinePostManager sPostManager;
    private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t08/question/_search/?size=100";
    private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t08/question/";
    private static final String TAG = "ELASTICSEARCH";
    private CachedPostManager mCachedPostManager;
    private Gson gson;

    protected OnlinePostManager(Context context) {
        super(context);
        gson = new Gson();
        mCachedPostManager = CachedPostManager.getInstance(context);
    }

    /**
     * 1. Gets questions from online 2. Updates cache
     * 
     * @return status a String that tells us if the files were successfully loaded from the server
     */
    private String loadFromServer() {
        ArrayList<Question> questions = new ArrayList<Question>();
        HttpClient httpClient = new DefaultHttpClient();
        String status = null;

        try {
            HttpPost request = new HttpPost(SEARCH_URL);
            ElasticSearchCommand command = new MatchAllCommand();

            request.setHeader("Accept", "application/json");
            request.setEntity(new StringEntity(command.getJsonCommand()));
            HttpResponse response = httpClient.execute(request);

            status = response.getStatusLine().toString();
            Log.d("Debug", status);

            SearchResponse<Question> parsedResponse = parseSearchResponse(response);
            SearchHits<Question> searchHits = parsedResponse.getHits();

            if (searchHits != null && searchHits.getHits() != null) {
                for (Hit<Question> hit : searchHits.getHits()) {
                    questions.add(hit.getSource());
                }
            }

        } catch (Exception e) {
            status = "Exception " + e.getClass() + ": " + e.getMessage();
            Log.d("Debug", status);
        }

        this.mQuestions = castToPosts(questions);
        updateCache();
        return status;
    }

    /**
     * Does exactly what it says : Updates the Cache
     */
    private void updateCache() {
        for (Post post : getQuestions()) {
            Post cachedPost = mCachedPostManager.getPost(post.getID());

            // if the post is not in cache, ignore it
            if (cachedPost == null) {
                continue;
            }

            cachedPost = post;
            mCachedPostManager.save();
        }
    }

    /** Elastic Search Methods **/

    /**
     * Get Post with specific ES ID
     * 
     * @param question which the ES Id is being searched for.
     * @return null if no such question exists
     */
    private Question getESQuestion(Question question) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(RESOURCE_URL + question.getID());
        String status = null;

        try {
            HttpResponse response = httpClient.execute(httpGet);
            Hit<Question> hit = parseGetResponse(response);
            status = response.getStatusLine().toString();
            Log.i(TAG, status);

            if (hit == null) {
                return null;
            }

            question = hit.getSource();

        } catch (Exception e) {
            status = "Exception: " + e.getMessage();
            Log.i(TAG, status);
        }

        return question;
    }

    /**
     * Inserts an question into the ES server
     * 
     * @param the question being added
     * @returns a status string
     */
    private String insertEsQuestion(Question newQuestion) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost addRequest = new HttpPost(RESOURCE_URL + newQuestion.getID());
        String status = null;

        try {
            StringEntity stringEntity = new StringEntity(gson.toJson(newQuestion));
            addRequest.setEntity(stringEntity);
            addRequest.setHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(addRequest);
            status = response.getStatusLine().toString();
            Log.i(TAG, status);

        } catch (Exception e) {
            status = "Exception: " + e.toString() + " Message: " + e.getMessage();
            Log.i(TAG, status);
        }

        return status;
    }

    /**
     * Updates question on ES server
     * 
     * @param existingQuestion the question we will be trying to add to ES
     * @param updateString string if the file is already online or not
     * @return a status string
     */
    private String updateESQuestion(Question existingQuestion, String updateString) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost updateRequest = new HttpPost(RESOURCE_URL + existingQuestion.getID() + "/_update");
        String status = null;

        try {
            StringEntity stringEntity = new StringEntity("{ \"doc\": {" + updateString + "}}");
            updateRequest.setEntity(stringEntity);
            updateRequest.setHeader("Accept", "application/json");

            HttpResponse response = httpClient.execute(updateRequest);
            status = response.getStatusLine().toString();
            Log.i(TAG, status);

        } catch (Exception e) {
            status = "Exception: " + e.getMessage();
            Log.i(TAG, status);
        }
        return status;
    }

    /**
     * Updates question votes on ES server
     * 
     * @return a status string or null if fails
     */
    private String updateESQuestionVotes(Question existingQuestion, int voteChange) {
        Question onlineQuestion = getESQuestion(existingQuestion);

        if (onlineQuestion == null)
            return null;

        onlineQuestion.setVotes(onlineQuestion.getVotes() + voteChange);
        String updateString = "\"mVotes\":" + Integer.valueOf(onlineQuestion.getVotes()).toString();
        // set the existing question to the refreshed online question
        existingQuestion = onlineQuestion;
        return updateESQuestion(existingQuestion, updateString);
    }

    /**
     * Adds question answer on ES server
     * 
     * @param updatedQuestion the answer that has been cast as a question and updated into the ES
     * @return status string or null if fails
     */
    private String updateESAnswer(Question updatedQuestion) {
        String updateString = "\"mAnswers\": " + gson.toJson(updatedQuestion.getAnswers());
        return updateESQuestion(updatedQuestion, updateString);
    }

    private String updateESReply(Question updatedQuestion) {
        String updateString = "\"mReplies\": " + gson.toJson(updatedQuestion.getReplies());
        return updateESQuestion(updatedQuestion, updateString);
    }

    /**
     * Updates an answers votes on ES server
     * 
     * @param existingQuestion the question of the answer
     * @param answer The answer that is being changed online for the amount of votes the answer has
     * @param voteChange how many votes have changed
     * @return a status string or null if fails
     */
    private String updateESAnswerVotes(Question existingQuestion, Answer answer, int voteChange) {
        Question onlineQuestion = getESQuestion(existingQuestion);

        if (onlineQuestion == null)
            return null;

        Answer onlineAnswer = onlineQuestion.getAnswer(answer.getID());
        onlineAnswer.setVotes(onlineAnswer.getVotes() + voteChange);

        // set the existing question to the refreshed online question
        existingQuestion = onlineQuestion;
        return updateESAnswer(onlineQuestion);
    }

    /**
     * Adds an answers to a question on ES server
     * 
     * @param existingQuestion the question that is the parent of the answer being added
     * @param answer the answer being added
     * @return a status string or null if fails
     */
    private String addESAnswer(Question existingQuestion, Answer answer) {
        Question onlineQuestion = getESQuestion(existingQuestion);

        if (onlineQuestion == null)
            return null;

        onlineQuestion.addAnswer(answer);
        // set the existing question to the refreshed online question
        existingQuestion = onlineQuestion;
        return updateESAnswer(onlineQuestion);
    }

    private String addESQuestionReply(Question existingQuestion, Reply reply) {
        Question onlineQuestion = getESQuestion(existingQuestion);

        if (onlineQuestion == null)
            return null;

        onlineQuestion.addReply(reply);
        // set the existing question to the refreshed online question
        existingQuestion = onlineQuestion;
        return updateESReply(onlineQuestion);
    }

    private String addESAnswerReply(Answer existingAnswer, Reply reply) {
        Question parentQuestion = getQuestion(existingAnswer.getParentID());
        Question onlineQuestion = getESQuestion(parentQuestion);

        if (onlineQuestion == null)
            return null;

        onlineQuestion.getAnswer(existingAnswer.getID()).addReply(reply);
        // set the existing question to the refreshed online question
        existingAnswer = onlineQuestion.getAnswer(existingAnswer.getID());
        return updateESAnswer(onlineQuestion);
    }

    /** HTTP Parsing and Conversion **/

    /** HTTP Parsing Methods **/

    private Hit<Question> parseGetResponse(HttpResponse response) {

        try {
            String json = getHttpResponseContent(response);
            Type hitType = new TypeToken<Hit<Question>>() {
            }.getType();

            Hit<Question> hit = gson.fromJson(json, hitType);
            return hit;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private SearchResponse<Question> parseSearchResponse(HttpResponse response) throws IOException {
        String json;
        json = getHttpResponseContent(response);

        Type parsedResponseType = new TypeToken<SearchResponse<Question>>() {
        }.getType();
        SearchResponse<Question> parsedResponse = gson.fromJson(json, parsedResponseType);

        return parsedResponse;
    }

    // Gets content from an HTTP response
    private String getHttpResponseContent(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
                .getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

    /** Public Methods **/
    /**
     * 1. pushes updates to server 2. get posts from server Static initializer, use this to get the
     * active instance. This insures we only ever have one copy going at once!
     * 
     * @param context current context of the app
     * @return a post manager of the posts online.
     */
    public static OnlinePostManager getInstance(Context context) {
        if (sPostManager != null) {
            return sPostManager;
        }

        // initialize instance
        sPostManager = new OnlinePostManager(context.getApplicationContext());
        sPostManager.mProfileManager = UserProfileManager.getInstance(context);

        // push updates to server
        sPostManager.pushOfflineUpdates();

        // get posts from server
        try {
            sPostManager.loadFromServer();
        } catch (Exception e) {
            sPostManager.mQuestions = new ArrayList<Post>();
        }

        return sPostManager;
    }

    /**
     * Saves individual question
     * 
     * @param The questions that will be added.
     */
    @Override
    public void addQuestion(Question newQuestion) {
        newQuestion.setExistsOnline(true);
        String result = insertEsQuestion(newQuestion);

        if (result.equals("HTTP/1.1 201 Created")) {
            this.mQuestions.add(newQuestion);
        }
        else {
            mCachedPostManager.addedOffline = true;
            newQuestion.setExistsOnline(false);
        }

        mProfileManager.saveNewPostAttributes(newQuestion);
        mCachedPostManager.getQuestions().add(newQuestion);
        mCachedPostManager.save();
    }

    /**
     * Saves individual answer by updating associated question on ES server
     */
    @Override
    public void addAnswer(Question parent, Answer newAnswer) {
        newAnswer.setExistsOnline(true);
        String result = addESAnswer(parent, newAnswer);

        if (result.equals("HTTP/1.1 200 OK")) {
            parent.addAnswer(newAnswer);
        }

        else {
            mCachedPostManager.addedOffline = true;
            newAnswer.setExistsOnline(false);
        }

        mProfileManager.saveNewPostAttributes(newAnswer);
        mCachedPostManager.getQuestion(parent.getID()).addAnswer(newAnswer);
        mCachedPostManager.save();
    }

    @Override
    public void addReply(Post parent, Reply newReply) {
        newReply.setExistsOnline(true);
        String result;

        if (isQuestion(parent)) {
            result = addESQuestionReply((Question) parent, newReply);
        }
        else {
            result = addESAnswerReply((Answer) parent, newReply);
        }
        if (result.equals("HTTP/1.1 200 OK")) {
            parent.addReply(newReply);
            Log.d("DEBUG32", "Reply not added");
        }

        else {
            mCachedPostManager.addedOffline = true;
            newReply.setExistsOnline(false);
        }

        mCachedPostManager.getPost(parent.getID()).addReply(newReply);
        mCachedPostManager.save();
    }

    @Override
    public void toggleUpvote(Post post) {

        int incrementVotes = 1;
        if (mProfileManager.getIsUpvoted(post)) {
            incrementVotes = -1;
            post.decrementVotes();
        }
        else {
            post.incrementVotes();
        }

        // Save user attributes
        mProfileManager.toggleIsUpvoted(post);

        // Update online
        String result = null;
        if (isQuestion(post)) {
            result = updateESQuestionVotes((Question) post, incrementVotes);
        }
        else {
            Question question = (Question) getPost(((Answer) post).getParentID());
            result = updateESAnswerVotes(question, (Answer) post, incrementVotes);
        }

        if (result.equals("HTTP/1.1 200 OK")) {
            post.setUpvotesChangedOffline(0);
        }
        else {
            mCachedPostManager.addedOffline = true;
            post.setUpvotesChangedOffline(incrementVotes);
        }

        mCachedPostManager.getPost(post.getID()).setUpvotesChangedOffline(incrementVotes);
        mCachedPostManager.save();
    }

    @Override
    public void toggleFavorite(Post post) {
        mProfileManager.toggleIsFavorited(post);
    }

    public void toggleReadLater(Post post) {
        // Only questions can be marked as read later
        if (isQuestion(post)) {
            mProfileManager.setIsReadLater(post);
            addToCache((Question) post);
        }
    }

    /**
     * when the device returns to being online from an offline state this method will push the posts
     * the user has made while offline online
     */
    public void pushOfflineUpdates() {
        if (!mCachedPostManager.hasAddedOffline())
            return;

        Log.d("DEBUG32", "pushing online");
        String result = null;
        for (Post post : mCachedPostManager.getQuestions()) {

            // if the question is new then all associated data including questions/replies are new
            // and we just have to insert the new question
            Question question = (Question) post;
            if (!question.getExistsOnline()) {
                question.setUpvotesChangedOffline(0);
                question.setExistsOnline(true);
                result = insertEsQuestion(question);
                Log.d("DEBUG32", "result of pushing question: " + result);

                for (Reply reply : question.getReplies()) {
                    reply.setExistsOnline(true);
                }

                for (Answer answer : question.getAnswers()) {
                    answer.setExistsOnline(true);
                    answer.setUpvotesChangedOffline(0);

                    for (Reply reply : answer.getReplies()) {
                        reply.setExistsOnline(true);
                    }
                }
                continue;
            }

            int votesOffline = question.getUpvotesChangedOffline();
            if (votesOffline != 0) {
                result = updateESQuestionVotes(question, votesOffline);
                question.setUpvotesChangedOffline(0);
                Log.d("DEBUG32", "result of pushing votes: " + result);

            }

            for (Reply reply : question.getReplies()) {
                if (!reply.getExistsOnline()) {
                    result = addESQuestionReply(question, reply);
                    reply.setExistsOnline(true);
                    Log.d("DEBUG32", "result of pushing replies: " + result);
                }
            }

            for (Answer answer : question.getAnswers()) {
                if (!answer.getExistsOnline()) {
                    result = addESAnswer(question, answer);
                    answer.setUpvotesChangedOffline(0);
                    answer.setExistsOnline(true);

                    for (Reply reply : answer.getReplies()) {
                        reply.setExistsOnline(true);
                    }
                    continue;
                }

                for (Reply reply : answer.getReplies()) {
                    if (!reply.getExistsOnline()) {
                        result = addESAnswerReply(answer, reply);
                        reply.setExistsOnline(true);
                    }
                }

                votesOffline = answer.getUpvotesChangedOffline();
                if (votesOffline != 0) {
                    answer.setUpvotesChangedOffline(0);
                    result = updateESAnswerVotes(question, answer, votesOffline);
                }
            }
        }
        mCachedPostManager.addedOffline = false;
    }

    public void refreshAll() {
        loadFromServer();
    }

    /**
     * Get Post with specific ES ID
     * 
     * @param question the post that will give the specific ESID
     */
    public void refreshQuestion(Question question) {
        Question onlineQuestion = getESQuestion(question);

        if (onlineQuestion == null)
            return;

        question = onlineQuestion;
        boolean updated = mCachedPostManager.updateIfExists(question);
        if (updated)
            mCachedPostManager.save();
    }

    public void addToCache(Question question) {
        if (mCachedPostManager.getPost(question.getID()) == null) {
            mCachedPostManager.getQuestions().add(question);
            mCachedPostManager.save();
        }
    }

    public boolean hasAddedOffline() {
        return mCachedPostManager.addedOffline;
    }
}
