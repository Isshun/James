package com.bluebox.james;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.smallbox.lib.JSONUtilsBase;

import android.util.Log;

import com.bluebox.james.datasource.DataSourceListener;
import com.bluebox.james.model.DeviceBaseModel;
import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.RoomModel;
import com.bluebox.james.model.ScenarioOptionModel;
import com.bluebox.james.model.factory.DeviceFactory;
import com.bluebox.james.model.factory.FeatureFactory;
import com.bluebox.james.model.factory.RoomFactory;
import com.bluebox.james.model.factory.ScenarioFactory;
import com.bluebox.james.model.scenario.ScenarioBase;

public class JSONUtils extends JSONUtilsBase {
	private static final String ROOMS_FILENAME = "rooms.json";
	private static final String DEVICES_FILENAME = "devices.json";
	private static final String CONFIG_FILENAME = "config.json";
	private static final String SCENARIOS_FILENAME = "scenarios.json";
	private static final String FEATURES_FILENAME = "features.json";

	public static void saveConfig(JSONObject obj) {
		save(CONFIG_FILENAME, obj);
	}

	public static void saveRooms() {
		JSONUtils.save(JSONUtils.ROOMS_FILENAME, RoomFactory.toJSON(ApplicationService.getInstance().getRooms()));
	}

	public static void saveDevices() {
		JSONUtils.save(JSONUtils.DEVICES_FILENAME, DeviceFactory.toJSON(ApplicationService.getInstance().getDevices()));
	}

//	public static void saveScenarios() {
//		JSONUtils.save(JSONUtils.SCENARIOS_FILENAME, ScenarioFactory.toJSON(ApplicationService.getInstance().getScenarios()));
//	}
//
//	public static void saveFeatures() {
//		JSONUtils.save(JSONUtils.FEATURES_FILENAME, FeatureFactory.toJSON(ApplicationService.getInstance().getFeatures()));
//	}

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

//	public static void loadScenarios(DataSourceListener listener) {
//		JSONArray array = JSONUtils.loadArray(JSONUtils.SCENARIOS_FILENAME);
//		if (array != null) {
//			for (int i = 0; i < array.length(); i++) {
//				try {
//					JSONObject obj = array.getJSONObject(i);
//					ScenarioBase scenario = ScenarioFactory.fromJSON(obj);
//					if (scenario != null) {
//						listener.addScenario(scenario);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	public static void loadFeatures(DataSourceListener listener) {
//		JSONArray array = JSONUtils.loadArray(JSONUtils.FEATURES_FILENAME);
//		if (array != null) {
//			for (int i = 0; i < array.length(); i++) {
//				try {
//					JSONObject obj = array.getJSONObject(i);
//					FeatureModel feature = FeatureFactory.fromJSON(obj);
//					if (feature != null) {
//						Log.d("JSONDataSource", "[JSONDataSource] load feature #" + feature.getId());
//						listener.addFeature(feature);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
