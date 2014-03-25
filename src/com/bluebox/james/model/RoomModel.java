package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;


public class RoomModel {
	private static long			sCount;
	private String 				mName;
	private List<FeatureModel>	mScenes;
	private long 				mId;
	private int 				mImgBackground;

	public RoomModel(String name, int imgBackground) {
		mId = sCount++;
		mName = name;
		mScenes = new ArrayList<FeatureModel>();
		mImgBackground = imgBackground;
	}

	public void addScene(FeatureModel scene) {
		mScenes.add(scene);
	}
	
	public List<FeatureModel> getScenes() {
		return mScenes;
	}

	public long getId() {
		return mId;
	}

	public FeatureModel getFeature(long id) {
		for (FeatureModel scene: mScenes) {
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
		return mImgBackground;
	}
}
