
package m2gl.tvmaze;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "url",
    "name",
    "type",
    "language",
    "genres",
    "status",
    "runtime",
    "premiered",
    "officialSite",
    "schedule",
    "rating",
    "weight",
    "network",
    "webChannel",
    "externals",
    "image",
    "summary",
    "updated",
    "_links"
})
public class TVMazeShow {

    @JsonProperty("id")
    private long id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("language")
    private String language;
    @JsonProperty("genres")
    private List<String> genres = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("runtime")
    private long runtime;
    @JsonProperty("premiered")
    private String premiered;
    @JsonProperty("officialSite")
    private String officialSite;
    @JsonProperty("schedule")
    private Schedule schedule;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("weight")
    private long weight;
    @JsonProperty("network")
    private Network network;
    @JsonProperty("webChannel")
    private Object webChannel;
    @JsonProperty("externals")
    private Externals externals;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("updated")
    private long updated;
    @JsonProperty("_links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TVMazeShow() {
    }

    /**
     * 
     * @param summary
     * @param weight
     * @param officialSite
     * @param genres
     * @param status
     * @param image
     * @param runtime
     * @param links
     * @param externals
     * @param type
     * @param network
     * @param url
     * @param id
     * @param schedule
     * @param updated
     * @param name
     * @param premiered
     * @param language
     * @param rating
     * @param webChannel
     */
    public TVMazeShow(long id, String url, String name, String type, String language, List<String> genres, String status, long runtime, String premiered, String officialSite, Schedule schedule, Rating rating, long weight, Network network, Object webChannel, Externals externals, Image image, String summary, long updated, Links links) {
        super();
        this.id = id;
        this.url = url;
        this.name = name;
        this.type = type;
        this.language = language;
        this.genres = genres;
        this.status = status;
        this.runtime = runtime;
        this.premiered = premiered;
        this.officialSite = officialSite;
        this.schedule = schedule;
        this.rating = rating;
        this.weight = weight;
        this.network = network;
        this.webChannel = webChannel;
        this.externals = externals;
        this.image = image;
        this.summary = summary;
        this.updated = updated;
        this.links = links;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }

    @JsonProperty("genres")
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("runtime")
    public long getRuntime() {
        return runtime;
    }

    @JsonProperty("runtime")
    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    @JsonProperty("premiered")
    public String getPremiered() {
        return premiered;
    }

    @JsonProperty("premiered")
    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    @JsonProperty("officialSite")
    public String getOfficialSite() {
        return officialSite;
    }

    @JsonProperty("officialSite")
    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    @JsonProperty("schedule")
    public Schedule getSchedule() {
        return schedule;
    }

    @JsonProperty("schedule")
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @JsonProperty("rating")
    public Rating getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @JsonProperty("weight")
    public long getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(long weight) {
        this.weight = weight;
    }

    @JsonProperty("network")
    public Network getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(Network network) {
        this.network = network;
    }

    @JsonProperty("webChannel")
    public Object getWebChannel() {
        return webChannel;
    }

    @JsonProperty("webChannel")
    public void setWebChannel(Object webChannel) {
        this.webChannel = webChannel;
    }

    @JsonProperty("externals")
    public Externals getExternals() {
        return externals;
    }

    @JsonProperty("externals")
    public void setExternals(Externals externals) {
        this.externals = externals;
    }

    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("updated")
    public long getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(long updated) {
        this.updated = updated;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
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
