
package m2gl.tvmaze;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tvrage",
    "thetvdb",
    "imdb"
})
public class Externals {

    @JsonProperty("tvrage")
    private long tvrage;
    @JsonProperty("thetvdb")
    private long thetvdb;
    @JsonProperty("imdb")
    private String imdb;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Externals() {
    }

    /**
     * 
     * @param thetvdb
     * @param imdb
     * @param tvrage
     */
    public Externals(long tvrage, long thetvdb, String imdb) {
        super();
        this.tvrage = tvrage;
        this.thetvdb = thetvdb;
        this.imdb = imdb;
    }

    @JsonProperty("tvrage")
    public long getTvrage() {
        return tvrage;
    }

    @JsonProperty("tvrage")
    public void setTvrage(long tvrage) {
        this.tvrage = tvrage;
    }

    @JsonProperty("thetvdb")
    public long getThetvdb() {
        return thetvdb;
    }

    @JsonProperty("thetvdb")
    public void setThetvdb(long thetvdb) {
        this.thetvdb = thetvdb;
    }

    @JsonProperty("imdb")
    public String getImdb() {
        return imdb;
    }

    @JsonProperty("imdb")
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
