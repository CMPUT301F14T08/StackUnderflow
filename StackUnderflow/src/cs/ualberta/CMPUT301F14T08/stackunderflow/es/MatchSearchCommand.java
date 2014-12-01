package cs.ualberta.CMPUT301F14T08.stackunderflow.es;

import java.util.Arrays;
import java.util.List;

import cs.ualberta.CMPUT301F14T08.stackunderflow.model.SearchObject;
/**
 * Taken from https://github.com/dfserrano/AndroidElasticSearch/ used to add and remove information from elastic search online.
 * 
 * These are recourse files used for implementing elastic search. 
 */
public class MatchSearchCommand implements ElasticSearchCommand {
    private int searchType;
    private boolean searchPics;
    private String searchTerms;
    private boolean searchLoc;

    public MatchSearchCommand(int type, boolean pics, String terms, boolean location) {
        searchType = type;
        searchPics = pics;		
        searchTerms = parseKeywords(terms);
        searchLoc = location;
    }

    public String getJsonCommand() {
        /*StringBuffer query = new StringBuffer("{\"query\" : {\"query_string\" : {\"query\" : \"" 
                + searchTerms + "\", \"fields\": [");
        if (searchType == SearchObject.SEARCH_QUESTIONS) query.append("\"mTitle\", \"mText\"");
        if (searchType == SearchObject.SEARCH_ANSWERS) query.append("\"mAnswers.mText\"");
        if (searchType == SearchObject.SEARCH_BOTH) query.append("\"mTitle\", \"mText\", \"mAnswers.mText\"");
        query.append("]}}}");*/
        StringBuffer query;
        //if (searchLoc) {
        // = new StringBuffer();
        //}
        //else {
            query = new StringBuffer("{\"query\": {\"filtered\": {\"query\":" +
                    " {\"query_string\": {\"query\": \"" + searchTerms + "\",\"fields\": [");
            if (searchType == SearchObject.SEARCH_QUESTIONS) query.append("\"mTitle\", \"mText\"");
            if (searchType == SearchObject.SEARCH_ANSWERS) query.append("\"mAnswers.mText\"");
            if (searchType == SearchObject.SEARCH_BOTH) query.append("\"mTitle\", \"mText\", \"mAnswers.mText\"");
            query.append("]}}");
            if (searchPics) {
                query.append(",\"filter\": {\"or\": [{ \"exists\": { \"field\" : \"mPicture\" }}," +
                        "{ \"exists\": { \"field\" : \"mAnswers.mPicture\"}}]}");
            }
            query.append("}}}");
        //}

        return query.toString();
    }

    private String parseKeywords(String searchTerms){
        String keywords = "";

        if (searchTerms == null) {
            return keywords;
        }

        List<String> terms = Arrays.asList(searchTerms.split("\\s+"));

        if (terms.size() > 0) {
            keywords += terms.get(0);
            for (int i = 1; i < terms.size(); i++) {
                keywords += " AND " + terms.get(i);
            }
        }

        return keywords;
    }

}
