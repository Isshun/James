package com.bluebox.james.model;

public class SceneLightModel extends SceneModel {
	private float 	mValue;
	private boolean	mIsVariometer;
	
	public SceneLightModel(String name) {
		super(SceneModel.SCENE_LIGHT, name);
		
		mValue = 1f;
	}
	
	public void increase() {
		mValue += 0.2f;
	}

	public void decrease() {
		mValue -= 0.2f;
	}

}
