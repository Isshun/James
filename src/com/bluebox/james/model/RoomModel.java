package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;


public class RoomModel {
	private static long			sCount;
	private String 				mName;
	private List<featureModel>	mScenes;
	private long 				mId;
	private int 				mImgBackground;

	public RoomModel(String name, int imgBackground) {
		mId = sCount++;
		mName = name;
		mScenes = new ArrayList<featureModel>();
		mImgBackground = imgBackground;
	}

	public void addScene(featureModel scene) {
		mScenes.add(scene);
	}
	
	public List<featureModel> getScenes() {
		return mScenes;
	}

	public long getId() {
		return mId;
	}

	public featureModel getScene(long id) {
		for (featureModel scene: mScenes) {
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
