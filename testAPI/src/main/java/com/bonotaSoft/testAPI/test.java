package com.bonotaSoft.testAPI;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "test" path)
 */
@Path("test")
public class test {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject testGet(@QueryParam("name") String name) {
        return implTest(name);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject testPost(@QueryParam("name") String name) {
    	return implTest(name);
    }
    
    public JsonObject implTest(String name) {
    	JsonObject myObject = Json.createObjectBuilder()
    	        .add("name", name)
    	        .add("age", 32)
    	        .build();
    	
        return myObject;
    }
}
