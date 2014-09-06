package com.bluebox.james.model.scenario;

import com.bluebox.james.JSONUtils;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.ScenarioOptionModel;

public class ScenarioSwitch extends ScenarioBase {
	private ScenarioOptionModel 	mOn;
	private ScenarioOptionModel 	mOff;
	private DeviceBaseModel			mDevice;

	public ScenarioSwitch(FeatureModel feature, DeviceBaseModel device) {
		super(feature);

		mDevice = device;

		mOn = new ScenarioOptionModel(this, device, 0, "On", -1, -1);
		mOn.setFeature(feature);
		mOn.addDevice(device, 100);
		add(mOn);

		mOff = new ScenarioOptionModel(this, device, 1, "Off", -1, -1);
		mOff.setFeature(feature);
		mOff.addDevice(device, 0);
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
//		DBHelper.getInstance().updateScenario(this);
		JSONUtils.saveRooms();
	}

	public DeviceBaseModel getDevice() {
		return mDevice;
	}

	public void setDevice(DeviceBaseModel device) {
		mDevice = device;
		mOn.getDevices().clear();
		mOn.addDevice(device, 100);
		mOff.getDevices().clear();
		mOff.addDevice(device, 0);
	}

	@Override
	public ScenarioSwitch asSwitch() {
		return (ScenarioSwitch)this;
	}
	

	@Override
	public int getType() {
		return FeatureModel.SCENE_SWITCH;
	}

}
