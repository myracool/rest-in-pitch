package m2gl.rip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"_id"})
public class Show {
	private long id;
	private String name;
	private List<String> genres;
	private Date premiere;
	private String offsite;
	private float rating;
	private String network;
	private String country;
	private String image;
	private String summary;
	
	public Show() {
		genres = new ArrayList<String>();
	}

	public Show(long id, String name, List<String> genres, Date premiere, String offsite, float rating, String network,
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public Date getPremiere() {
		return premiere;
	}

	public void setPremiere(Date premiere) {
		this.premiere = premiere;
	}

	public String getOffsite() {
		return offsite;
	}

	public void setOffsite(String offsite) {
		this.offsite = offsite;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
