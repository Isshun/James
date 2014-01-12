package com.bluebox.james.model;

import java.util.ArrayList;
import java.util.List;

public class RoomModel {
	public String name;
	public List<SceneModel>	mScenes;

	public RoomModel(String name) {
		this.name = name;
		this.mScenes = new ArrayList<SceneModel>();
	}

	public void addScene(SceneModel scene) {
		mScenes.add(scene);
	}
	
	public List<SceneModel> getScenes() {
		return mScenes;
	}
}
