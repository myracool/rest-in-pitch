package m2gl.rip;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class UserResource {
	@POST
	@Path("/user/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
		if (user.saveToDb()) {
			return Response.ok(user).build();
		}
		else {
			return Response.status(Response.Status.CONFLICT).entity("User already exists").build();
		}
    }
	
	@POST
	@Path("/user/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response checkUser(User user) {
		if (user.login()) {
			return Response.ok(user).build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
		}
    }
}
