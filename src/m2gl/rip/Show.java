package m2gl.rip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import m2gl.tvmaze.TVMazeShow;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"_id"})
public class Show {
	@JsonProperty("id")
	private long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("genres")
	private List<String> genres = new ArrayList<String>();
	@JsonProperty("premiere")
	private String premiere;
	@JsonProperty("official site")
	private String offsite;
	@JsonProperty("rating")
	private double rating;
	@JsonProperty("network")
	private String network;
	@JsonProperty("country")
	private String country;
	@JsonProperty("image")
	private String image;
	@JsonProperty("summary")
	private String summary;
	
	public Show() {
	}

	public Show(long id, String name, List<String> genres, String premiere, String offsite, float rating, String network,
			String country, String image, String summary) {
		this.id = id;
		this.name = name;
		this.genres = genres;
		this.premiere = premiere;
		this.offsite = offsite;
		this.rating = rating;
		this.network = network;
		this.country = country;
		this.image = image;
		this.summary = summary;
	}
	
	public Show(TVMazeShow tvMazeShow) {
		
		this.id = tvMazeShow.getId();
		this.name = tvMazeShow.getName();
		this.genres = tvMazeShow.getGenres();
		this.premiere = tvMazeShow.getPremiered();
		this.offsite = tvMazeShow.getOfficialSite();
		this.rating = Optional.ofNullable(tvMazeShow.getRating())
				.map(r -> r.getAverage())
				.orElse(0.0);
		this.network = Optional.ofNullable(tvMazeShow.getNetwork())
				.map(n -> n.getName())
				.orElse("");
		this.country = Optional.ofNullable(tvMazeShow.getNetwork())
				.map(n -> n.getCountry())
				.map(c -> c.getName())
				.orElse("");
		this.image = Optional.ofNullable(tvMazeShow.getImage())
				.map(i -> i.getMedium())
				.orElse("");
		this.summary = tvMazeShow.getSummary();
	}
	
	@JsonProperty("id")
	public long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("genres")
	public List<String> getGenres() {
		return genres;
	}

	@JsonProperty("genres")
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	@JsonProperty("premiere")
	public String getPremiere() {
		return premiere;
	}

	@JsonProperty("premiere")
	public void setPremiere(String premiere) {
		this.premiere = premiere;
	}

	@JsonProperty("official site")
	public String getOffsite() {
		return offsite;
	}

	@JsonProperty("official site")
	public void setOffsite(String offsite) {
		this.offsite = offsite;
	}

	@JsonProperty("rating")
	public double getRating() {
		return rating;
	}

	@JsonProperty("rating")
	public void setRating(double rating) {
		this.rating = rating;
	}

	@JsonProperty("network")
	public String getNetwork() {
		return network;
	}

	@JsonProperty("network")
	public void setNetwork(String network) {
		this.network = network;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	@JsonProperty("image")
	public void setImage(String image) {
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
	
}
