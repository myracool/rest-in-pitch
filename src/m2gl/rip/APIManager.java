package m2gl.rip;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/rest")
public class APIManager extends ResourceConfig {

    public APIManager() {
        // Register resources and providers using package-scanning.
        packages("m2gl.rip");

        register(new CORSFilter());
        register(MyObjectMapperProvider.class);

        // Enable Tracing support.
        property(ServerProperties.TRACING, "ALL");
   }
    
}

