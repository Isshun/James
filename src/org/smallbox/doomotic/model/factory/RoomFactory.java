package org.smallbox.doomotic.model.factory;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.smallbox.doomotic.model.FeatureModel;
import org.smallbox.doomotic.model.RoomModel;

public class RoomFactory {

	public static JSONArray toJSON(List<RoomModel> rooms) {
		JSONArray array = new JSONArray();

		for (RoomModel room: rooms) {
			JSONObject obj = toJSON(room);
			if (obj != null) {
				array.put(obj);
			}
		}

		return array;
	}

	private static JSONObject toJSON(RoomModel room) {
		try {
			JSONObject obj = new JSONObject();

			obj.put("id", room.getId());
			obj.put("img_background", room.getImgBackground());
			obj.put("name", room.getName());
			
			if (room.getFeatures() != null) {
				JSONArray arrayFeatures = new JSONArray();
				for (FeatureModel feature: room.getFeatures()) {
					JSONObject objFeature = FeatureFactory.toJSON(feature);
					if (objFeature != null) {
						arrayFeatures.put(objFeature);
					}
				}
				obj.put("features", arrayFeatures);
			}

			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static RoomModel fromJSON(JSONObject obj) {
		try {
			RoomModel room = obj.has("id") ? new RoomModel(obj.getInt("id")) : new RoomModel();

			if (obj.has("img_background")) room.setImgBackground(obj.getInt("img_background"));
			if (obj.has("name")) room.setName(obj.getString("name"));
//			if (obj.has("features_id")) {
//				JSONArray featureArray = obj.getJSONArray("features_id");
//				for (int i = 0; i < featureArray.length(); i++) {
//					FeatureModel feature = ApplicationService.getInstance().getFeature(featureArray.getInt(i));
//					if (feature != null) {
//						room.addFeature(feature);
//					}
//				}
//			}
			if (obj.has("features")) {
				JSONArray featureArray = obj.getJSONArray("features");
				for (int i = 0; i < featureArray.length(); i++) {
					FeatureModel feature = FeatureFactory.fromJSON(featureArray.getJSONObject(i));
					if (feature != null) {
						room.addFeature(feature);
					}
				}
			}

//			FeatureModel feature = new FeatureModel();
//			feature.setName("mock");
//			feature.setType(FeatureModel.SCENE_SCENARIO);
//			room.addFeature(feature);
//
//			ScenarioOptionModel opt1 = new ScenarioOptionModel();
//			opt1.setLabel("opt1");
//			feature.addScenarioOption(opt1);
//
//			ScenarioOptionModel opt2 = new ScenarioOptionModel();
//			opt2.setLabel("opt2");
//			feature.addScenarioOption(opt2);

			return room;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
}
