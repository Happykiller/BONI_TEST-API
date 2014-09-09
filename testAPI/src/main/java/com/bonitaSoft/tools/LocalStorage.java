package com.bonitaSoft.tools;

import java.util.HashMap;

public class LocalStorage {
	private HashMap<String, Object> storage = new HashMap<String, Object>();

	public Object getStorage(String key) {
		return storage.get(key);
	}

	public void setStorage(String key, Object obj) {
		this.storage.put(key, obj);
	}
}
