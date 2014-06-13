package com.bluebox.james.model.scenario;

import com.bluebox.james.DBHelper;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioOptionModel;

public class ScenarioSwitch extends ScenarioBase {
	private ScenarioOptionModel 	mOn;
	private ScenarioOptionModel 	mOff;
	private DeviceBaseModel			mDevice;

	public ScenarioSwitch(FeatureModel feature, DeviceBaseModel device) {
		super(feature);
		
		if (device == null) {
			Log.error("Device cannot be null");
			return;
		}
		
		mDevice = device;
		
		mOn = new ScenarioOptionModel(this, device, 0, "On", -1, -1);
		mOn.setFeature(feature);
		mOn.addDevice(device, 100);
		add(mOn);

		mOff = new ScenarioOptionModel(this, device, 1, "Off", -1, -1);
		mOff.setFeature(feature);
		mOn.addDevice(device, 0);
		add(mOff);
	}

	public ScenarioOptionModel getOn() {
		return mOn;
	}

	public ScenarioOptionModel getOff() {
		return mOff;
	}

	@Override
	public void commit() {
		DBHelper.getInstance().updateScenario(this);
	}
	
}
