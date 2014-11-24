package cs.ualberta.CMPUT301F14T08.stackunderflow.es;

import java.util.Arrays;
import java.util.List;

import cs.ualberta.CMPUT301F14T08.stackunderflow.model.SearchObject;

public class MatchSearchCommand implements ElasticSearchCommand {
    private int searchType;
    private boolean searchPics;
    private String searchTerms;

    public MatchSearchCommand(int type, boolean pics, String terms) {
        searchType = type;
        searchPics = pics;		
        searchTerms = parseKeywords(terms);
    }

    public String getJsonCommand() {
        StringBuffer query = new StringBuffer("{\"query\" : {\"query_string\" : {\"query\" : \"" 
                + searchTerms + "\", \"fields\": [");
        if (searchType == SearchObject.SEARCH_QUESTIONS) query.append("\"mTitle\", \"mText\"");
        if (searchType == SearchObject.SEARCH_ANSWERS) query.append("\"mAnswers.mText\"");
        if (searchType == SearchObject.SEARCH_BOTH) query.append("\"mTitle\", \"mText\", \"mAnswers.mText\"");
        query.append("]}}}");
        //String query = "{\"query\" : {\"match_all\" : {}}}";
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
