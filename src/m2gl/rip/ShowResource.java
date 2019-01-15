package m2gl.rip;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import m2gl.tvmaze.Properties;
import m2gl.tvmaze.TVMazeShow;

@Path("")
public class ShowResource {
	
	private boolean checkGenre(Show sh, String genre) {
		List<String> genres = sh.getGenres();
		Iterator<String> it = genres.iterator();
		boolean res = false;
		while (it.hasNext()) {
			String cur = it.next();
			if (cur.equals(genre)) {
				res = true;
			}
		}
		return res;
	}
	
	private boolean checkGenres(Show sh, List<String> genres) {
		boolean res = true;
		Iterator<String> it = genres.iterator();
		while (it.hasNext()) {
			String cur = it.next();
			res = (res && checkGenre(sh, cur));
		}
		return res;
	}
	
	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Show getShow(@PathParam("id") long id) {
    	ObjectMapper mapper = new MyObjectMapperProvider().getContext(Properties.class);
    	try {
			return new Show(mapper.readValue(new URL("http://api.tvmaze.com/shows/"+id), TVMazeShow.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	throw new NotFoundException("Not found");
    }
	
	@GET
	@Path("/shows/page/{pageid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Show> getPage(@QueryParam("pageid") int pageid) {
		ObjectMapper mapper = new MyObjectMapperProvider().getContext(Properties.class);
		if (pageid >= 0 && pageid <= 160) {
			try {
				List<Show> showList = new ArrayList<Show>();
				Iterator<JsonNode> it = StreamSupport.stream(mapper.readTree(new URL("http://api.tvmaze.com/shows?page=" + pageid)).spliterator(), false)
				        .iterator();
				while (it.hasNext()) {
					JsonNode j = it.next();
					Show sh = new Show(mapper.treeToValue(j, TVMazeShow.class));
					showList.add(sh);
				}
				return showList;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		throw new InvalidParameterException("Invalid page provided");
	}
	
	@GET
	@Path("shows/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Show> search(@QueryParam("name") String name, @QueryParam("genre") List<String> genre) {
		ObjectMapper mapper = new MyObjectMapperProvider().getContext(Properties.class);
		try {
			genre = (genre != null && !genre.isEmpty()) ? genre : null;
			List<Show> showList = new ArrayList<Show>();
			Iterator<JsonNode> it = StreamSupport.stream(mapper.readTree(new URL("http://api.tvmaze.com/search/shows/?q=" + name)).spliterator(), false)
			        .iterator();
			while (it.hasNext()) {
				JsonNode j = it.next();
				Show sh = new Show(mapper.treeToValue(j.path("show"), TVMazeShow.class));
				if (genre == null || checkGenres(sh, genre)) {
					showList.add(sh);
				}
			}
			return showList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new InvalidParameterException("Invalid name provided");
	}

	@GET
	@Path("shows/random/{n}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public List<Show> getRand(@PathParam("n") int n, List<Integer> arr) {
    	try {
    		arr = (arr != null && !arr.isEmpty()) ? arr : new ArrayList<Integer>();
    		List<Show> showPage = new ArrayList<Show>();
    		List<Show> showList = new ArrayList<Show>();
    	    Random random = new Random();
    	    showPage = getPage(random.nextInt(160));
    	    for (int i = 0 ; i < n ; ++i) {
    	    	int id = random.nextInt(showPage.size());
    	    	Show sh = showPage.remove(id);
    	    	showList.add(sh);
    	    }	  
    	    return showList;
    	    
		} catch (Exception e) {
			e.printStackTrace();
		}
    	throw new NotFoundException("Not found");
    }
	
	private boolean userExists(String username) {
		MongoClient mongoClient = new MongoClient(User.DB_URI);
		try {
		    MongoDatabase db = mongoClient.getDatabase("RIP");
		    MongoCollection<Document> collection = db.getCollection("User");
		    Document search = collection.find(eq("username", username)).first();
		    return (search != null);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return false;
	}
	
	private boolean isInWatchlist(String username, int id) {
	    MongoClient mongoClient = new MongoClient(User.DB_URI);
		try {
		    MongoDatabase db = mongoClient.getDatabase("RIP");
		    MongoCollection<Document> collection = db.getCollection("Watchlist");
		    Document search = collection.find(and(eq("username", username), eq("showid", id))).first();
		    if (search != null) {
		    	return true;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return false;
	}
	
	@POST
	@Path("/watchlist/check/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response isShowInWatchlist(@PathParam("id") int id, User user) {
		boolean res = isInWatchlist(user.getUsername(), id);
		return Response.ok(res).status(201).build();
    }
	
	private boolean saveToWatchlist(String username, int id) {
	    MongoClient mongoClient = new MongoClient(User.DB_URI);
		try {
			if (!isInWatchlist(username, id) && userExists(username)) {
			    MongoDatabase db = mongoClient.getDatabase("RIP");
			    MongoCollection<Document> collection = db.getCollection("Watchlist");
			    String jsonString = "{\"username\":\"" + username + "\",\"showid\":" + id + "}";
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
	
	@POST
	@Path("/watchlist/add/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response addShowToWatchlist(@PathParam("id") int id, User user) {
		if (saveToWatchlist(user.getUsername(), id)) {
			return Response.ok(user).status(201).build();
		}
		else {
			return Response.status(Response.Status.CONFLICT).entity("Show already in Watchlist").build();
		}
    }
	
	private boolean removeFromWatchlist(String username, int id) {
	    MongoClient mongoClient = new MongoClient(User.DB_URI);
		try {
			if (isInWatchlist(username, id) && userExists(username)) {
			    MongoDatabase db = mongoClient.getDatabase("RIP");
			    MongoCollection<Document> collection = db.getCollection("Watchlist");
			    Document search = collection.findOneAndDelete(and(eq("username", username), eq("showid", id)));
			    if (search != null) {
			    	return true;
			    }
			}
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return false;
	}
	
	@DELETE
	@Path("/watchlist/remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response removeShowFromWatchlist(@PathParam("id") int id, User user) {
		if (removeFromWatchlist(user.getUsername(), id)) {
			return Response.ok(user).status(201).build();
		}
		else {
			return Response.status(Response.Status.CONFLICT).entity("Show not found in Watchlist").build();
		}
    }
	
	private ArrayList<Integer> getWatchlistIds(String username) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
	    MongoClient mongoClient = new MongoClient();
		try {
		    MongoDatabase db = mongoClient.getDatabase("RIP");
		    MongoCollection<Document> collection = db.getCollection("Watchlist");
		    Iterable<Document> search = collection.find(eq("username", username));
		    for (Document doc : search) {
		    	Integer id = doc.getInteger("showid");
		    	if (id != null) {
		    		ids.add(id);
		    	}
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    mongoClient.close();
		}
		return ids;
	}
	
	@POST
	@Path("/watchlist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Show> getWatchlist(User user) {
		ArrayList<Show> shows = new ArrayList<Show>();
		ArrayList<Integer> ids = getWatchlistIds(user.getUsername());
		for (Integer i : ids) {
			Show s = getShow(i);
			shows.add(s);
		}
		return shows;
	}

}
