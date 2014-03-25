package com.bluebox.james.model;

public class SceneLightModel extends featureModel {
	private float 	mValue;
	private boolean	mIsVariometer;
	
	public SceneLightModel(String name, int icon) {
		super(featureModel.SCENE_LIGHT, name, icon);
		
		mValue = 1f;
	}
	
	public void increase() {
		mValue += 0.2f;
	}

	public void decrease() {
		mValue -= 0.2f;
	}

}
