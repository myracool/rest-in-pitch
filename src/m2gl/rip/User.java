package m2gl.rip;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class User {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	
	protected static MongoClientURI DB_URI = new MongoClientURI("mongodb://Rest-In-Pitch:22octobre1995@ds013559.mlab.com:13559/rest-in-pitch");
	
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
	
	public boolean saveToDb(){
	    MongoClient mongoClient = new MongoClient(DB_URI);
		try {
		    MongoDatabase db = mongoClient.getDatabase("RIP");
		    MongoCollection<Document> collection = db.getCollection("User");
		    
		    Document search = collection.find(eq("username", getUsername())).first();
		    if (search == null) {		    
		    	ObjectMapper mapper = new MyObjectMapperProvider().getContext(User.class);
		    	String jsonString = mapper.writeValueAsString(this);
		    	Document doc = Document.parse(jsonString);
		    	collection.insertOne(doc);
		    	return true;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return false;
	}
	
	public boolean login(){
	    MongoClient mongoClient = new MongoClient(DB_URI);
		try {
		    MongoDatabase db = mongoClient.getDatabase("RIP");
		    MongoCollection<Document> collection = db.getCollection("User");

		    ObjectMapper mapper = new MyObjectMapperProvider().getContext(User.class);
		    String jsonString = mapper.writeValueAsString(this);
		    Document doc = Document.parse(jsonString);
		    FindIterable<Document> res = collection.find(doc);
		    if (res.iterator().hasNext()) {
		    	return true;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return false;
	}
	
}
