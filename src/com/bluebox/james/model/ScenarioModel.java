package com.bluebox.james.model;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Color;

public class ScenarioModel {
	private static long						sCount;
	private Map<DeviceModel, Integer>		mDevice;
	private String 							mName;
	public int 								mIcon;
	private int 							mColor = -1;
	private long 							mId;
	
	public ScenarioModel(String name, int icon) {
		mId = sCount++;
		mName = name;
		mIcon = icon;
		mColor = Color.rgb(64, 138, 191);
		mDevice = new HashMap<DeviceModel, Integer>();
	}

	public void execute() {
		for (DeviceModel equipment: mDevice.keySet()) {
			int value = mDevice.get(equipment);
		}
	}

	public void addEquipment(DeviceModel equipment, int value) {
		mDevice.put(equipment, value);
	}
	
	public int getIcon() {
		return mIcon;
	}

	public Map<DeviceModel, Integer> getEquipments() {
		return mDevice;
	}

	public String getName() {
		return mName;
	}

	public int getColor() {
		return mColor;
	}

	public long getId() {
		return mId;
	}

	public void setColor(int color) {
		mColor = color;
	}
}
