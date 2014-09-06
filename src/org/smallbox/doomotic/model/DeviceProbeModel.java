package org.smallbox.doomotic.model;


public class DeviceProbeModel extends DeviceBaseModel {

	public DeviceProbeModel(int id, String name, long deviceId) {
		super(id, name, deviceId);
	}

	@Override
	public boolean isProbe() {
		return true;
	}

	@Override
	public int getType() {
		return PROBE_TYPE_ID;
	}

}
