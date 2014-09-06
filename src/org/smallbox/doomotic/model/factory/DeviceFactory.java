package org.smallbox.doomotic.model.factory;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.DeviceSwitchModel;

public class DeviceFactory {

	public static DeviceBaseModel fromJSON(JSONObject obj) {
		try {
			if (obj.has("type")) {
				if (obj.getInt("type") == DeviceBaseModel.SWITCH_TYPE_ID) {
					DeviceSwitchModel device = obj.has("id") ? new DeviceSwitchModel(obj.getInt("id")) : new DeviceSwitchModel();

					if (obj.has("device_id")) device.setDeviceId(obj.getInt("device_id"));
					if (obj.has("value")) device.setValue(obj.getDouble("value"));
					if (obj.has("name")) device.setName(obj.getString("name"));

					return device;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static JSONArray toJSON(List<DeviceBaseModel> devices) {
		JSONArray array = new JSONArray();

		for (DeviceBaseModel device: devices) {
			JSONObject obj = toJSON(device);
			if (obj != null) {
				array.put(obj);
			}
		}

		return array;
	}

	private static JSONObject toJSON(DeviceBaseModel device) {
		try {
			JSONObject obj = new JSONObject();

			obj.put("id", device.getId());
			obj.put("device_id", device.getDeviceId());
			obj.put("value", device.getValue());
			obj.put("name", device.getName());
			obj.put("type", device.getType());

			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

}
