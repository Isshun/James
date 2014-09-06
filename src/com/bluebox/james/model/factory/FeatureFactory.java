package com.bluebox.james.model.factory;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bluebox.james.model.FeatureModel;
import com.bluebox.james.model.scenario.ScenarioBase;

public class FeatureFactory {

	public static JSONObject toJSON(FeatureModel feature) {
		try {
			JSONObject obj = new JSONObject();

			obj.put("id", feature.getId());
			obj.put("color", feature.getColor());
			obj.put("icon", feature.getIcon());
			obj.put("type", feature.getType());
			obj.put("name", feature.getName());
			obj.put("scenario", ScenarioFactory.toJSON(feature.getScenario()));
			
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static FeatureModel fromJSON(JSONObject obj) {
		if (obj != null) {
			try {
				FeatureModel feature = new FeatureModel(obj.has("id") ? obj.getInt("id") : 0);

				if (obj.has("color")) feature.setColor(obj.getInt("color"));
				if (obj.has("icon")) feature.setIcon(obj.getInt("icon"));
				if (obj.has("type")) feature.setType(obj.getInt("type"));
				if (obj.has("name")) feature.setName(obj.getString("name"));

				if (obj.has("scenario")) {
					JSONObject scenarioObj = obj.getJSONObject("scenario");
					ScenarioBase scenario = ScenarioFactory.fromJSON(feature, scenarioObj);
					if (scenario != null) {
						feature.setScenario(scenario);
					}
				}
				
				return feature;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public static JSONArray toJSON(List<FeatureModel> features) {
		JSONArray array = new JSONArray();

		for (FeatureModel feature: features) {
			JSONObject obj = toJSON(feature);
			if (obj != null) {
				array.put(obj);
			}
		}

		return array;
	}

}
