package m2gl.rip;

import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
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
		    MongoDatabase db = mongoClient.getDatabase("RIP");
		    MongoCollection<Document> collection = db.getCollection("User");

		    ObjectMapper mapper = new MyObjectMapperProvider().getContext(User.class);
		    String jsonString = mapper.writeValueAsString(this);
		    Document doc = Document.parse(jsonString);
		    collection.insertOne(doc);
		    return this.getUsername() + " successfully registered.";
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return null;
	}
	
	public String login(){
	    MongoClient mongoClient = new MongoClient();
		try {
		    MongoDatabase db = mongoClient.getDatabase("RIP");
		    MongoCollection<Document> collection = db.getCollection("User");

		    ObjectMapper mapper = new MyObjectMapperProvider().getContext(User.class);
		    String jsonString = mapper.writeValueAsString(this);
		    Document doc = Document.parse(jsonString);
		    FindIterable<Document> res = collection.find(doc);
		    if (res.iterator().hasNext()) {
		    	return this.getUsername() + " successfully logged in.";
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return null;
	}
	
	
}
