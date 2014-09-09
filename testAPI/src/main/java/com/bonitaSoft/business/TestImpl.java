package com.bonitaSoft.business;

import java.io.IOException;
import java.util.HashMap;

import com.bonitaSoft.tools.ReturnObject;

public class TestImpl {
	
	public TestImpl(){
		
	}

	public void getBusiness(ReturnObject myJSONObject, HashMap<String, Object> inputs) throws IOException {
		myJSONObject.addDatas("truc", 1);
		myJSONObject.addDatas("name", inputs.get("name"));
	}
}
