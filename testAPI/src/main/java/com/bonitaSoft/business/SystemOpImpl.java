package com.bonitaSoft.business;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;

import com.bonitaSoft.tools.LocalStorage;
import com.bonitaSoft.tools.LoginInfo;
import com.bonitaSoft.tools.ReturnObject;
import com.bonitaSoft.tools.Tools;

public class SystemOpImpl {
	
	Tools myTools = new Tools();

	public void getBusiness(ReturnObject myJSONObject, HashMap<String, Object> inputs, ServletContext context) throws IOException {
		String log = (String) inputs.get("log");
		String pass = (String) inputs.get("pass");
		
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("server.url", "http://localhost:8080"); 
		map.put("application.name", "bonita"); 
		APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, map); 
		// get the LoginAPI using the TenantAPIAccessor 
		try {
			final LoginAPI loginAPI = TenantAPIAccessor.getLoginAPI();
			
			final APISession apiSession = loginAPI.login(log, pass); 
			
			String token = myTools.generateToken();
			
			Date resultdate = new Date(System.currentTimeMillis());
			
			LoginInfo loginInfo = new LoginInfo(log,pass,apiSession,resultdate,token);
			
			LocalStorage localStorage = (LocalStorage) context.getAttribute("localStorage");

			localStorage.setStorage(token, loginInfo);
			
			myJSONObject.addDatas("token", token);
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
