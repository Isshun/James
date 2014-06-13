package com.bluebox.james.model;

import android.annotation.SuppressLint;

public abstract class DeviceBaseModel extends DBModel {
	private String 					mName;
	private long 					mDeviceId;
	
	@SuppressLint("UseSparseArrays")
	public DeviceBaseModel(long id, String name, long deviceId) {
		mDbId = id;
		mName = name;
		mDeviceId = deviceId;
	}
	
	public String getName() {
		return mName;
	}
	
	public String getUrl(int value) {
		String switchCmd = value == 0 ? "Off" : "On";
		return "http://192.168.1.2:8080/json.htm?type=command&param=switchlight&idx=" + mDeviceId + "&switchcmd=" + switchCmd + "&level=0";
	}

	public long getDeviceId() {
		return mDeviceId;
	}

	public double getValue() {
		return 23.5;
	}

}
