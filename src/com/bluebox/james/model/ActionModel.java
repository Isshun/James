package com.bluebox.james.model;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Color;

public class ActionModel {
	private static long						sCount;
	private Map<DeviceModel, Integer>	mEquipments;
	private String 							mName;
	public int 								mIcon;
	private int 							mColor = -1;
	private long 							mId;
	
	public ActionModel(String name, int icon) {
		mId = sCount++;
		mName = name;
		mIcon = icon;
		mColor = Color.rgb(64, 138, 191);
		mEquipments = new HashMap<DeviceModel, Integer>();
	}

	public void execute() {
		for (DeviceModel equipment: mEquipments.keySet()) {
			int value = mEquipments.get(equipment);
		}
	}

	public void addEquipment(DeviceModel equipment, int value) {
		mEquipments.put(equipment, value);
	}
	
	public int getIcon() {
		return mIcon;
	}

	public Map<DeviceModel, Integer> getEquipments() {
		return mEquipments;
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
