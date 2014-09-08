package com.bonitaSoft.tools;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.JSONObject;

public class ReturnObject {
	
	private Tools myTools = new Tools();
	
	private String strErreur;
	
	private HashMap<String, Object> metrologie;
	
	private HashMap<String, Object> datas;
	
	public ReturnObject(){
		strErreur = "";
		
		SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss:SSS");
	    Date resultdate = new Date(System.currentTimeMillis());
	    String myDate = date_format.format(resultdate);
	   
		metrologie = new HashMap<String, Object>();
		metrologie.put("Start", myDate);
		
		datas = new HashMap<String, Object>();
	}
	
	public JSONObject getReturnObjectJson(){
		JSONObject myJSONObject = new JSONObject();
    	try{
			DateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss:SSS");
    		
    		myJSONObject.append("strErreur", strErreur);
    		myJSONObject.append("datas", datas);
    		
    		//get strat date
    		String stratDate = (String) metrologie.get("Start");
    		java.util.Date dateStart = date_format.parse(stratDate);
    		java.sql.Date sqlDateStart = new java.sql.Date(dateStart.getTime());
    		
    		//Calc finish date
    	    Date dateFinish = new Date(System.currentTimeMillis());
    	    String strDateFinish = date_format.format(dateFinish);
    	    
    	    metrologie.put("Finish", strDateFinish);
    	    
    	    //cald duration
    	    long diff = dateFinish.getTime() - sqlDateStart.getTime();
    	    
    	    String strDuration = String.format("%d:%02d:%02d", diff/3600, (diff%3600)/60, (diff%60));
    	   
    		metrologie.put("Duration", strDuration);
    		
    		myJSONObject.append("metrologie", metrologie);
        	
            return myJSONObject;
    	}catch (Exception e){
    		myTools.traceLog("Error : " + e.getMessage());
    		return myJSONObject;
    	}
	}
	
	public void addDatas(String p_key, Object p_obj) {
		datas.put(p_key, p_obj);
	}
	
	//SETTER GETTER

	public void setmetrologie(HashMap<String, Object> metrologie) {
		this.metrologie = metrologie;
	}

	public void setDatas(HashMap<String, Object> datas) {
		this.datas = datas;
	}

	public String getStrErreur() {
		return strErreur;
	}

	public void setStrErreur(String strErreur) {
		this.strErreur = strErreur;
	}

	public HashMap<String, Object> getMetrologie() {
		return metrologie;
	}

	public HashMap<String, Object> getDatas() {
		return datas;
	}
}
