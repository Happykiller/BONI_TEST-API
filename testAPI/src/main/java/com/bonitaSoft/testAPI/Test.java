package com.bonitaSoft.testAPI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.bonitaSoft.tools.ReturnObject;
import com.bonitaSoft.tools.Tools;


/**
 * Root resource (exposed at "test" path)
 */
@Path("test")
public class Test {
	Tools myTools = new Tools();
	ReturnObject myJSONObject = new ReturnObject();
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testGet(@QueryParam("name") String name) {
        return implTest(name);
    }

	@POST
    @Produces(MediaType.TEXT_PLAIN)
    public String testPost(@QueryParam("name") String name) {
    	return implTest(name);
    }
    
    public String implTest(String name) {
    	JSONObject returnJSONObject = new JSONObject();
    	try{
    		myJSONObject.addDatas("truc", 1);
    		myJSONObject.addDatas("name", name);
    		
    		returnJSONObject = myJSONObject.getReturnObjectJson();
            return returnJSONObject.toString();
    	}catch (Exception e){
    		myTools.traceLog("Error : " + e.getMessage());
    		return returnJSONObject.toString();
    	}
    }
}
