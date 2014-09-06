package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

public class RoomModel extends DBModel {
	private String 					mName;
	private List<FeatureModel>		mFeatures;
	private int 					mIcon;

	public RoomModel(int id) {
		super(id);
		mFeatures = new ArrayList<FeatureModel>();
	}
	
	public RoomModel(int id, String name, int icon) {
		super(id);
		mName = name;
		mFeatures = new ArrayList<FeatureModel>();
		mIcon = icon;
	}

	public RoomModel() {
		super();
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

	public void setImgBackground(int imgBackground) {
		mIcon = imgBackground;
	}

	public void setName(String name) {
		mName = name;
	}

}
