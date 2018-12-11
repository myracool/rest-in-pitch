
package m2gl.tvmaze;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
    "self",
    "previousepisode",
    "nextepisode"
})
public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("previousepisode")
    private Previousepisode previousepisode;
    @JsonProperty("nextepisode")
    private Nextepisode nextepisode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Links() {
    }

    /**
     * 
     * @param previousepisode
     * @param self
     * @param nextepisode
     */
    public Links(Self self, Previousepisode previousepisode, Nextepisode nextepisode) {
        super();
        this.self = self;
        this.previousepisode = previousepisode;
        this.nextepisode = nextepisode;
    }

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("previousepisode")
    public Previousepisode getPreviousepisode() {
        return previousepisode;
    }

    @JsonProperty("previousepisode")
    public void setPreviousepisode(Previousepisode previousepisode) {
        this.previousepisode = previousepisode;
    }

    @JsonProperty("nextepisode")
    public Nextepisode getNextepisode() {
        return nextepisode;
    }

    @JsonProperty("nextepisode")
    public void setNextepisode(Nextepisode nextepisode) {
        this.nextepisode = nextepisode;
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
