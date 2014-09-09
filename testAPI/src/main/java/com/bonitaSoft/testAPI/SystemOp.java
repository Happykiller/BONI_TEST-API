package com.bonitaSoft.testAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import com.bonitaSoft.business.SystemOpImpl;
import com.bonitaSoft.tools.ReturnObject;
import com.bonitaSoft.tools.Tools;

/**
 * Root resource (exposed at "test" path)
 * testAPI/webapi/systemop/login.php?log=walter.bates&pass=bpm
 */
@Path("/systemop")
public class SystemOp {
	Tools myTools = new Tools();
	SystemOpImpl mySystemOpImpl = new SystemOpImpl();
	
	@Context ServletContext context;
	
	Boolean debug = true;
	
	Boolean publicI = true;
	
    @GET
    @Path("/login.php")
    @Produces(MediaType.TEXT_PLAIN)
    public String testGet(@Context UriInfo uriInfo) {
        return execTest(uriInfo);
    }

	@POST
	@Path("/login.php")
    @Produces(MediaType.TEXT_PLAIN)
    public String testPost(@Context UriInfo uriInfo) {
    	return execTest(uriInfo);
    }
    
    public String execTest(UriInfo uriInfo) {
    	JSONObject returnJSONObject = new JSONObject();
    	try{
    		//def
    		List<Object> inputsDef = new ArrayList<Object>();
	    	ReturnObject myJSONObject = new ReturnObject();
    	    
	    	//input 'log'
	    	Map<String, Object> log = new HashMap<String, Object>();
	    	log.put("label", "log");
	    	log.put("type", String.class);
	    	log.put("default", null);
	    	
	    	inputsDef.add(log);
	    	
	    	//input 'pass'
	    	Map<String, Object> pass = new HashMap<String, Object>();
	    	pass.put("label", "pass");
	    	pass.put("type", String.class);
	    	pass.put("default", null);
	    	
	    	inputsDef.add(pass);
    		
	    	//check input
	    	HashMap<String, Object> inputs = myTools.checkInput(uriInfo, inputsDef, publicI, debug);
	    	
	    	if(inputs.get("crtInputs") == null) {
	    		//------------------------------------------------
	    		//custom part
	    		mySystemOpImpl.getBusiness(myJSONObject, inputs, context);
	    		//------------------------------------------------
	    	}else{
	    		myJSONObject.setStrErreur((String) inputs.get("crtInputs"));
	    	}
    		
    		returnJSONObject = myJSONObject.getReturnObjectJson();
            return returnJSONObject.toString();
    	}catch (Exception e){
    		myTools.traceLog("Error : " + e.getMessage());
    		return returnJSONObject.toString();
    	}
    }
}
