package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

public class RoomModel extends DBModel {
	private String 					mName;
	private List<FeatureBaseModel>	mFeatures;
	private int 					mIcon;

	public RoomModel(long id, String name, int icon) {
		mDbId = id;
		mName = name;
		mFeatures = new ArrayList<FeatureBaseModel>();
		mIcon = icon;
	}

	public void addFeature(FeatureBaseModel feature) {
		mFeatures.add(feature);
	}
	
	public List<FeatureBaseModel> getFeatures() {
		return mFeatures;
	}

	public FeatureBaseModel getFeature(long id) {
		for (FeatureBaseModel scene: mFeatures) {
			if (scene.getId() == id) {
				return scene;
			}
		}
		return null;
	}

	public String getName() {
		return mName;
	}

	public int getImgBackground() {
		return mIcon;
	}

}
