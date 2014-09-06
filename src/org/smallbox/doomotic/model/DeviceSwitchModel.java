package org.smallbox.doomotic.model;


public class DeviceSwitchModel extends DeviceBaseModel {
	
	public DeviceSwitchModel(int  id, String name, long deviceId) {
		super(id, name, deviceId);
	}

	public DeviceSwitchModel(int id) {
		super(id);
	}

	public DeviceSwitchModel() {
		super();
	}

	@Override
	public boolean isSwitch() {
		return true;
	}

	@Override
	public int getType() {
		return SWITCH_TYPE_ID;
	}

}
