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

import com.bonitaSoft.business.TestImpl;
import com.bonitaSoft.tools.LocalStorage;
import com.bonitaSoft.tools.ReturnObject;
import com.bonitaSoft.tools.Tools;


/**
 * Root resource (exposed at "test" path)
 */
@Path("/test")
public class Test {
	Tools myTools = new Tools();
	TestImpl myTestImpl = new TestImpl();
	
	@Context ServletContext context;
	
	Boolean debug = true;
	
	Boolean publicI = false;
	
    @GET
    @Path("/unTest.php")
    @Produces(MediaType.TEXT_PLAIN)
    public String testGet(@Context UriInfo uriInfo) {
        return execTest(uriInfo);
    }

	@POST
	@Path("/unTest.php")
    @Produces(MediaType.TEXT_PLAIN)
    public String testPost(@Context UriInfo uriInfo) {
    	return execTest(uriInfo);
    }
    
    public String execTest(UriInfo uriInfo) {
    	JSONObject returnJSONObject = new JSONObject();
    	try{
    		LocalStorage localStorage = (LocalStorage) context.getAttribute("localStorage");
    		myTools.traceLog("list : " + localStorage.getStorage("hello"));
    		
    		//def
    		List<Object> inputsDef = new ArrayList<Object>();
	    	ReturnObject myJSONObject = new ReturnObject();
    	    
	    	//input 'name'
	    	Map<String, Object> name = new HashMap<String, Object>();
	    	name.put("label", "name");
	    	name.put("type", String.class);
	    	name.put("default", null);
	    	
	    	inputsDef.add(name);
	    	
	    	//input 'lab'
	    	Map<String, Object> lab = new HashMap<String, Object>();
	    	lab.put("label", "lab");
	    	lab.put("type", Integer.class);
	    	lab.put("default", 1);
	    	
	    	inputsDef.add(lab);
    		
	    	//check input
	    	HashMap<String, Object> inputs = myTools.checkInput(uriInfo, inputsDef, publicI, debug);
	    	
	    	if(inputs.get("crtInputs") == null) {
	    		//------------------------------------------------
	    		//custom part
	    		myTestImpl.getBusiness(myJSONObject, inputs);
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
