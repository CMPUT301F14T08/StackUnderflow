package cs.ualberta.CMPUT301F14T08.stackunderflow.es;


public class MatchAllCommand implements ElasticSearchCommand {

    public MatchAllCommand() {}

    public String getJsonCommand() {
        return "{\"query\" : {\"match_all\" : {}}}";
    }
}