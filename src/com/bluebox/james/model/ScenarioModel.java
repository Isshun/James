package com.bluebox.james.model;

import java.util.HashMap;
import java.util.Map;

import android.view.View.OnClickListener;

import com.bluebox.james.DBHelper;

public class ScenarioModel extends DBModel {
	private Map<DeviceBaseModel, Integer>	mDevices;
	private String 							mName;
	public int 								mIcon;
	private int 							mColor = -1;
	private FeatureBaseModel 				mFeature;
	private OnClickListener 				mOnClickListener;
	
	public ScenarioModel(long id, String name, int icon, int color) {
		mDbId = id;
		mName = name;
		mIcon = icon;
		mColor = color;//Color.rgb(64, 138, 191);
		mDevices = new HashMap<DeviceBaseModel, Integer>();
	}

	public void execute() {
		for (DeviceBaseModel equipment: mDevices.keySet()) {
			int value = mDevices.get(equipment);
		}
	}

	public void addDevice(DeviceBaseModel device, int value) {
		mDevices.put(device, value);
	}
	
	public int getIcon() {
		return mIcon;
	}

	public Map<DeviceBaseModel, Integer> getDevices() {
		return mDevices;
	}

	public String getName() {
		return mName;
	}

	public int getColor() {
		return mColor != -1 ? mColor : mFeature.getColor();
	}

	public void setColor(int color) {
		mColor = color;
	}

	public void setIcon(int icon) {
		mIcon = icon;
	}
	
	public void commit() {
		DBHelper.getInstance().updateScenario(this);
	}

	public void setFeature(FeatureBaseModel feature) {
		mFeature = feature;
	}

	public OnClickListener getOnClickListener() {
		return mOnClickListener;
	}

	public void setOnClickListener(OnClickListener onClickListener) {
		mOnClickListener = onClickListener;		
	}
}
