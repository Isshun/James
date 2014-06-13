package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

public class RoomModel extends DBModel {
	private String 					mName;
	private List<FeatureModel>	mFeatures;
	private int 					mIcon;

	public RoomModel(long id, String name, int icon) {
		mDbId = id;
		mName = name;
		mFeatures = new ArrayList<FeatureModel>();
		mIcon = icon;
	}

	public void addFeature(FeatureModel feature) {
		mFeatures.add(feature);
	}
	
	public List<FeatureModel> getFeatures() {
		return mFeatures;
	}

	public FeatureModel getFeature(long id) {
		for (FeatureModel scene: mFeatures) {
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
