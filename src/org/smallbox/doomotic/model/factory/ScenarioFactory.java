package org.smallbox.doomotic.model.factory;

import java.util.List;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.smallbox.doomotic.ApplicationService;
import org.smallbox.doomotic.model.DeviceBaseModel;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.ScenarioOptionModel;
import org.smallbox.doomotic.model.scenario.ScenarioBase;
import org.smallbox.doomotic.model.scenario.ScenarioCustom;

public class ScenarioFactory {

	public static JSONArray toJSON(List<ScenarioBase> scenarios) {
		JSONArray array = new JSONArray();

		//		for (ScenarioOptionModel scenario: scenarios) {
		//			JSONObject obj = toJSON(scenario);
		//			if (obj != null) {
		//				array.put(obj);
		//			}
		//		}

		return array;
	}

	public static JSONObject toJSON(ScenarioBase scenario) {
		try {
			JSONObject obj = new JSONObject();

			obj.put("id", scenario.getId());

			JSONArray arrayOption = new JSONArray();
			for (ScenarioOptionModel option: scenario.getOptions()) {
				JSONObject objOption = new JSONObject();
				objOption.put("color", option.getColor());
				objOption.put("icon", option.getIcon());
				objOption.put("label", option.getLabel());

				if (option.getDevices().size() > 0) {
					JSONArray arrayDevice = new JSONArray();
					for (Entry<DeviceBaseModel, Integer> entry: option.getDevices().entrySet()) {
						JSONObject objDevice = new JSONObject();
						objDevice.put("device_id", entry.getKey().getId());
						objDevice.put("value", entry.getValue());
						arrayDevice.put(objDevice);
					}
					objOption.put("devices", arrayDevice);
				}

				arrayOption.put(objOption);
			}

			obj.put("options", arrayOption);

			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ScenarioBase fromJSON(FeatureModel feature, JSONObject obj) {
		if (obj != null) {
			try {
				ScenarioCustom scenario = new ScenarioCustom(feature);

				if (obj.has("id")) scenario.setId(obj.getInt("id"));

				if (obj.has("options")) {
					JSONArray arrayOption  = obj.getJSONArray("options");
					for (int i = 0; i < arrayOption.length(); i++) {
						ScenarioOptionModel option = new ScenarioOptionModel();
						JSONObject objOption = arrayOption.getJSONObject(i);
						if (objOption.has("color")) option.setColor(objOption.getInt("color"));
						if (objOption.has("icon")) option.setIcon(objOption.getInt("icon"));
						if (objOption.has("label")) option.setLabel(objOption.getString("label"));

						if (objOption.has("devices")) {
							try {
								JSONArray arrayDevices = objOption.getJSONArray("devices");
								for (int j = 0; j < arrayDevices.length(); j++) {
									JSONObject objDevice  = arrayDevices.getJSONObject(j);
									DeviceBaseModel device = ApplicationService.getInstance().getDevice(objDevice.getInt("device_id"));
									if (device != null) {
										option.addDevice(device, objDevice.getInt("value"));
									}
								}	
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}

						//						feature.addScenarioOption(option);
						scenario.add(option);
					}
				}

				return scenario;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
