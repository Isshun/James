package org.smallbox.doomotic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.smallbox.doomotic.datasource.DataSourceListener;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;
import org.smallbox.doomotic.model.factory.DeviceFactory;
import org.smallbox.doomotic.model.factory.RoomFactory;
import org.smallbox.lib.JSONUtilsBase;

public class JSONUtils extends JSONUtilsBase {
	private static final String ROOMS_FILENAME = "rooms.json";
	private static final String DEVICES_FILENAME = "devices.json";
	private static final String CONFIG_FILENAME = "config.json";

	public static void saveConfig(JSONObject obj) {
		save(CONFIG_FILENAME, obj);
	}

	public static void saveRooms() {
		JSONUtils.save(JSONUtils.ROOMS_FILENAME, RoomFactory.toJSON(ApplicationService.getInstance().getRooms()));
	}

	public static void saveDevices() {
		JSONUtils.save(JSONUtils.DEVICES_FILENAME, DeviceFactory.toJSON(ApplicationService.getInstance().getDevices()));
	}

	public static void loadDevices(DataSourceListener listener) {
		JSONArray array = JSONUtils.loadArray(JSONUtils.DEVICES_FILENAME);
		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				try {
					JSONObject obj = array.getJSONObject(i);
					DeviceBaseModel device = DeviceFactory.fromJSON(obj);
					if (device != null) {
						listener.addDevice(device);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void loadRooms(DataSourceListener listener) {
		JSONArray array = JSONUtils.loadArray(JSONUtils.ROOMS_FILENAME);
		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				try {
					JSONObject obj = array.getJSONObject(i);
					RoomModel room = RoomFactory.fromJSON(obj);
					if (room != null) {
						listener.addRoom(room);
						
						if (room.getFeatures() != null) {
							for (FeatureModel feature: room.getFeatures()) {
								listener.addFeature(feature);
							}
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
