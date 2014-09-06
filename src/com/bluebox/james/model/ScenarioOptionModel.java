package com.bluebox.james.model;

import java.util.HashMap;
import java.util.Map;

import android.view.View.OnClickListener;

import com.bluebox.james.model.scenario.Log;
import com.bluebox.james.model.scenario.ScenarioBase;
import com.bluebox.james.service.OnExecuteListener;

public class ScenarioOptionModel extends DBModel {
	private static int						sCount;
	private Map<DeviceBaseModel, Integer>	mDevices;
	private String 							mLabel;
	public int 								mIcon;
	private int 							mColor;
	private FeatureModel 					mFeature;
	private OnClickListener 				mOnClickListener;
	private DeviceBaseModel 				mDevice;
	private ScenarioBase 					mScenario;
	
	public ScenarioOptionModel(ScenarioBase scenario, DeviceBaseModel device, int  id, String label, int icon, int color) {
		super(id);
		mLabel = label;
		mIcon = icon;
		mDevice = device;
		mColor = color;//Color.rgb(64, 138, 191);
		mDevices = new HashMap<DeviceBaseModel, Integer>();
	}

	public ScenarioOptionModel(int id) {
		super(id);
		mDevices = new HashMap<DeviceBaseModel, Integer>();

	}

	public ScenarioOptionModel() {
		super();
		mDevices = new HashMap<DeviceBaseModel, Integer>();
	}

	public void execute(OnExecuteListener executeListener) {
		for (DeviceBaseModel equipment: mDevices.keySet()) {
			int value = mDevices.get(equipment);
			String url = equipment.getUrl(value);
			executeListener.execute(url, value);
		}
	}

	public void addDevice(DeviceBaseModel device, int value) {
		if (device == null) {
			Log.error("Cannot add null device to scenario option");
			return;
		}
		mDevices.put(device, value);
	}
	
	public int getIcon() {
		return mIcon;
	}

	public Map<DeviceBaseModel, Integer> getDevices() {
		return mDevices;
	}

	public String getLabel() {
		return mLabel;
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

	public void setFeature(FeatureModel feature) {
		mFeature = feature;
	}

	public OnClickListener getOnClickListener() {
		return mOnClickListener;
	}

	public void setOnClickListener(OnClickListener onClickListener) {
		mOnClickListener = onClickListener;		
	}

	public DeviceBaseModel getDevice() {
		return mDevice;
	}

	public void setLabel(String label) {
		mLabel = label;
	}

	public void setScenario(ScenarioBase scenario) {
		mScenario = scenario;
	}
}
