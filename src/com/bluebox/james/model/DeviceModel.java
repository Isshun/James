package com.bluebox.james.model;

import android.annotation.SuppressLint;

public class DeviceModel {
	public String 					mName;
	private long 					mId;
	
	@SuppressLint("UseSparseArrays")
	public DeviceModel(String name, long id) {
		mName = name;
		mId = id;
	}
	
	public String getName() {
		return mName;
	}
	
	public String getUrl(int value) {
		String switchCmd = value == 0 ? "Off" : "On";
		return "http://192.168.1.2:8080/json.htm?type=command&param=switchlight&idx=" + mId + "&switchcmd=" + switchCmd + "&level=0";
	}

	public long getId() {
		return mId;
	}
}
