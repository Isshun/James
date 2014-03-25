package com.bluebox.james.model;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;

public class EquipmentModel {
	public String mName;
	public Map<Integer, Integer>	mUrls;
	
	@SuppressLint("UseSparseArrays")
	public EquipmentModel(String name) {
		mName = name;
		mUrls = new HashMap<Integer, Integer>();
	}
	
	public String getName() {
		return mName;
	}

	public void setUrl(int value, int id) {
		mUrls.put(value, id);
	}
	
	public String getUrl(int value) {
		if (mUrls.containsKey(value)) {
			String switchCmd = value == 0 ? "Off" : "On";
			return "http://192.168.1.2:8080/json.htm?type=command&param=switchlight&idx=" + mUrls.get(0) + "&switchcmd=" + switchCmd + "&level=0";
		}
		return null;
	}

	public int getId() {
		return mUrls.get(0);
	}
}
