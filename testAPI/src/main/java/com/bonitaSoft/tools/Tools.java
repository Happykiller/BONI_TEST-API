package com.bonitaSoft.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class Tools {

	private final static Logger LOGGER = Logger.getLogger(Tools.class.getName()); 
	
	public void traceLog(String p_msg){
		SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	    Date resultdate = new Date(System.currentTimeMillis());
	    String myDate = date_format.format(resultdate);
	    
	    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();

        String callMethode = stElements[3].getMethodName();
        String callClass = stElements[3].getClassName();
		String outMsg = "[Class : " + callClass + "][Methode : " + callMethode + "][Date : " + myDate + "]" + p_msg;
		
		System.out.println(outMsg);
        LOGGER.severe(outMsg);
	}
	
	public HashMap<String, Object> checkInput(UriInfo uriInfo, List<Object> inputsDef, Boolean publicI, Boolean debug) throws Exception{
		
		String strError = "";
		
		
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters(); 
		HashMap<String, Object> returnInput = new HashMap<String, Object>();
		
		if(!publicI){
			String key = this.getPropertie("config.properties", "keyExamlple");
			
			String inputKey = queryParams.getFirst("keyI");
			
			if(!key.equals(inputKey)){
				strError = "KeyI invalid.";
				returnInput.put("crtInputs", strError);
				return returnInput;
			}
		}
		
		strError = "Error missing intput";
		
		for (Iterator<Object> i = inputsDef.iterator(); i.hasNext();){
			@SuppressWarnings("unchecked")
			Map<String, Object> item = (Map<String, Object>) i.next();
			String label = (String) item.get("label");
			Class<?> type = (Class<?>) item.get("type");
			Object defaultValue = type.cast(item.get("default"));
			
			if(defaultValue == null){
				Object value = type.cast(queryParams.getFirst(label));
				if(value == null){
					String strMsg = "," + label;
					if(debug)
						this.traceLog(strMsg);
					strError += strMsg;
				}else{
					returnInput.put(label, value);
					if(debug)
						this.traceLog("required value-"+label+" : " + value.toString());
				}
			}else{
				Object value = type.cast(queryParams.getFirst(label));
				if(value == null){
					value = defaultValue;
				}
				returnInput.put(label, value);
				if(debug)
					this.traceLog("option value-"+label+" : " + value.toString());
			}
			
			if(strError != "Error missing intput")
				returnInput.put("crtInputs", strError);
		}
		
		return returnInput;
	}
	
	public String getPropertie(String file, String propertie) throws IOException {
		String returnPropertie = null;
		
        Properties prop = new Properties();
        
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        prop.load(inputStream);
        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + file + "' not found in the classpath");
        }
 
        // get the property value and print it out
        returnPropertie = prop.getProperty(propertie);
		
		return returnPropertie;
	}
	
	public String generateToken(){
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(15);
	}

}
