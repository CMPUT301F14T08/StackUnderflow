
package cs.ualberta.CMPUT301F14T08.stackunderflow.es;
/**
 * Taken from https://github.com/dfserrano/AndroidElasticSearch/ used to add and remove information from elastic search online.
 */
public class MatchAllCommand implements ElasticSearchCommand {

    public MatchAllCommand() {}

    public String getJsonCommand() {
        return "{\"query\" : {\"match_all\" : {}}}";
    }
}