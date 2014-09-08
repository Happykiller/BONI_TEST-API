package com.bonitaSoft.tools;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import com.bonitaSoft.testAPI.Test;

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

}
