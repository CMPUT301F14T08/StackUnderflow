
package cs.ualberta.CMPUT301F14T08.stackunderflow.es;
/**
 * Taken from https://github.com/dfserrano/AndroidElasticSearch/ used to add and remove information from elastic search online.
 * 
 * These are recourse files used for implementing elastic search. 
 * Simply returns match all command for 
 */
public class MatchAllCommand implements ElasticSearchCommand {

    public MatchAllCommand() {}

    public String getJsonCommand() {
        return "{\"query\" : {\"match_all\" : {}}}";
    }
}