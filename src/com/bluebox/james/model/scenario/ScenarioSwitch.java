package com.bluebox.james.model.scenario;

import com.bluebox.james.DBHelper;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioModel;

public class ScenarioSwitch extends ScenarioBase {
	private ScenarioModel 	mOn;
	private ScenarioModel 	mOff;
	private DeviceBaseModel mDevice;

	public ScenarioSwitch(FeatureModel feature, DeviceBaseModel device) {
		super(feature);
		
		if (device == null) {
			Log.error("Device cannot be null");
			return;
		}
		
		mDevice = device;
		
		mOn = new ScenarioModel(device, 0, "On", -1, -1);
		mOn.setFeature(feature);
		mOn.addDevice(device, 100);
		add(mOn);

		mOff = new ScenarioModel(device, 1, "Off", -1, -1);
		mOff.setFeature(feature);
		mOn.addDevice(device, 0);
		add(mOff);
	}

	public ScenarioModel getOn() {
		return mOn;
	}

	public ScenarioModel getOff() {
		return mOff;
	}

	@Override
	public void commit() {
		DBHelper.getInstance().updateScenario(this);
	}
}
