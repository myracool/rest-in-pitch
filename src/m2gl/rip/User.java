package m2gl.rip;

import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class User {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String saveToDb(){
	    MongoClient mongoClient = new MongoClient();
		try {
		    MongoDatabase db = mongoClient.getDatabase("rip");
		    MongoCollection<Document> collection = db.getCollection("user");

		    ObjectMapper mapper = new MyObjectMapperProvider().getContext(User.class);
		    String jsonString = mapper.writeValueAsString(this);
		    Document doc = Document.parse(jsonString);
		    collection.insertOne(doc);
		    return "User " + this.getUsername() + " " + " added successfully.";
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return null;
	}
	
	
	
	
}
