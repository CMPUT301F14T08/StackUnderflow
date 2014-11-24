
package cs.ualberta.CMPUT301F14T08.stackunderflow.es;

import java.util.List;

/**
 * Taken from https://github.com/dfserrano/AndroidElasticSearch/ used to add and remove information from elastic search online.
 */
public class SearchHits<T> {
    private int total;
    private float max_score;
    private List<Hit<T>> hits;

    public SearchHits() {}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getMax_score() {
        return max_score;
    }

    public void setMax_score(float max_score) {
        this.max_score = max_score;
    }

    public List<Hit<T>> getHits() {
        return hits;
    }

    public void setHits(List<Hit<T>> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "Hits [total=" + total + ", max_score=" + max_score + ", hits="
                + hits + "]";
    }
}