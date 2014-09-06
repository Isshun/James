package org.smallbox.doomotic.model;

import android.annotation.SuppressLint;

public abstract class DeviceBaseModel extends DBModel {
	public static final int SWITCH_TYPE_ID = 1;
	public static final int PROBE_TYPE_ID = 2;
	
	protected String 				mName;
	protected long 					mDeviceId;
	private double 					mValue;
	
	@SuppressLint("UseSparseArrays")
	public DeviceBaseModel(int id, String name, long deviceId) {
		super(id);
		mName = name;
		mDeviceId = deviceId;
	}
	
	public DeviceBaseModel() {
		super();
	}
	
	public DeviceBaseModel(int id) {
		super(id);
	}
	
	public String getName() {
		return mName;
	}
	
	public String getUrl(int value) {
		String switchCmd = value == 0 ? "Off" : "On";
		return "http://192.168.1.2:8080/json.htm?type=command&param=switchlight&idx=" + mDeviceId + "&switchcmd=" + switchCmd + "&level=0";
	}

	public void setDeviceId(int deviceId) {
		mDeviceId = deviceId;
	}

	public long getDeviceId() {
		return mDeviceId;
	}

	public double getValue() {
		return mValue;
	}

	public boolean isProbe() {
		return false;
	}

	public boolean isSwitch() {
		return false;
	}

	public DeviceSwitchModel getDeviceSwitch() {
		return (DeviceSwitchModel)this;
	}

	public abstract int getType();
	
	public void setName(String name) {
		mName = name;
	}

	public void setValue(double value) {
		mValue = value;
	}


}
